package code.sns.service;

import code.sns.config.HashTagConfig;
import code.sns.domain.*;
import code.sns.domain.dto.request.PostRequestDto;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.PostResponseLoginDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.follow.FollowRepository;
import code.sns.repository.like.PostLikeRepository;
import code.sns.repository.post.PostRepository;
import code.sns.repository.scrap.ScrapRepository;
import code.sns.repository.user.UserRepository;
import code.sns.upload.FileStore;
import code.sns.upload.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostLikeRepository postLikeRepository;
    private final ScrapRepository scrapRepository;
    private final HashTagService hashTagService;
    private final PostHashService postHashService;
    private final FileStore fileStore;
    private final S3Uploader s3Uploader;

//    @Cacheable(cacheNames = "post",key = "1L")
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> posts = postRepository.getPosts();

        Iterator<String> hashTagList = postHashService.getHashTagList(posts.stream().
                map(p -> p.getPost_id()).collect(Collectors.toList()));

        posts.stream().forEach( p -> p.setHashes(hashTagList.next()));

        return posts;
    }

    public void createPost(PostRequestDto requestDto) throws IOException {

        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST,String.format("해당 [%s] 아이디는 없습니다.",requestDto.getUser_id())));

//        UploadFile uploadFile = fileStore.storeFile(requestDto.getFile());
        // file image 저장
        UploadFile loadFileDto;
        if(requestDto.getFile() !=null) {
            String url = s3Uploader.uploadFile(requestDto.getFile(), "sns");
             loadFileDto = new UploadFile(requestDto.getFile().getOriginalFilename(), url);
        }else{
             loadFileDto =new UploadFile(null,null);
        }

        // context의 해쉬태그만 추출
        List<String> hashes = HashTagConfig.extractionHash(requestDto.getContext());

        // 해쉬태그들중 없는 태그들은 등록하고, 해시태그 리스트 반환
        List<HashTag> hashTagList = hashTagService.IsExistHash(hashes);

        // post 객체 create
        Post post = Post.createPost(requestDto.getContext(),
                loadFileDto ,user);

        // 한번 저장
        Post createdPost = postRepository.save(post);

        //생선된 post를 넣어서 Posthash List 생성
       List<PostHash> postHashes=
               hashTagList.stream().map(h -> new PostHash(h,createdPost)
        ).collect(Collectors.toList());

       // 반환받은 post 객체에 postHash 배열 set
       createdPost.addPostHashes(postHashes);

       postRepository.save(createdPost);

    }

    public PostResponseDto getPostById(Long postId) {
        return postRepository.findByIdDto(postId).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_POST,String.format("해당 [%s] 아이디는 없습니다.",postId)));
    }


    public List<PostResponseDto> getPostsById(Long userId, Pageable pageable) {
        return  postRepository.getPostsByUserId(userId,pageable).toList();
    }

    public void deleteById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ErrorCode.BAD_REQUEST_POST));
        postRepository.delete(post);

    }

    public List<PostResponseDto> getPostsByUserId(Long userId, Pageable pageable) {

        List<PostResponseDto> pageResult = postRepository.getPostsByUserId(userId, pageable).toList();

        return addScrapAndLike(pageResult,userId);
    }


    public List<PostResponseDto> getFollowPost(Long userId, Pageable pageable) {
        List<PostResponseDto> pageResult = postRepository.getPostsByFollow(userId,pageable).toList();

//        return addScrapAndLike(pageResult,userId);

        Iterator<String> hashTagList = postHashService.getHashTagList(pageResult.stream().
                map(p -> p.getPost_id()).collect(Collectors.toList()));

        pageResult.stream().forEach( p -> p.setHashes(hashTagList.next()));

        return addScrapAndLike(pageResult,userId);
    }

    public List<PostResponseDto> getPostsLiked(Long userId, Pageable pageable) {
        List<PostResponseDto> pageResult = postRepository.getPostsLiked(userId, pageable).toList();

        return addScrapAndLike(pageResult,userId);
    }



    public List<PostResponseDto> getScraps(Long userId, Pageable pageable) {
        List<PostResponseDto> pageResult = postRepository.getScraps(userId, pageable).toList();
        return addScrapAndLike(pageResult,userId);
    }

    public List<PostResponseLoginDto> getPostsLogins(Long userId, Pageable pageable) {
        return postRepository.getPostsLogins (userId, pageable);

    }
    private List<PostResponseDto> addScrapAndLike(List<PostResponseDto> pageResult, Long userId) {
        List<PostResponseDto> result =new ArrayList<>();

        for (PostResponseDto dto : pageResult) {

            dto.setIsLike(postLikeRepository.IsFollowList(userId ,dto.getPost_id()));
            User user = userRepository.findById(userId).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_USER));
            Post post = postRepository.findById(dto.getPost_id()).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_POST));
            dto.setIsScrap(scrapRepository.existsByUserAndPost(user,post));

            result.add(dto);
        }
        return result;
    }

    public List<FollowResponseDto> getUnFollowList(Long userId, PageRequest pageRequest) {
        return followRepository.getUnFollowList(userId,pageRequest);
    }

    public List<PostResponseDto> getTrendList(PageRequest pageRequest) {
        return postRepository.getTrendList(pageRequest).toList();
    }
}
