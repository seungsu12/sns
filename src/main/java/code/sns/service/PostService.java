package code.sns.service;

import code.sns.domain.Post;
import code.sns.domain.UploadFile;
import code.sns.domain.User;
import code.sns.domain.dto.request.PostRequestDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.PostResponseLoginDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.follow.FollowRepository;
import code.sns.repository.like.PostLikeRepository;
import code.sns.repository.post.PostRepository;
import code.sns.repository.user.UserRepository;
import code.sns.upload.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final FileStore fileStore;

    public List<PostResponseDto> getPosts() {
        return postRepository.getPosts();
    }

    public void createPost(PostRequestDto requestDto) throws IOException {

        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST,String.format("해당 [%s] 아이디는 없습니다.",requestDto.getUser_id())));

        UploadFile uploadFile = fileStore.storeFile(requestDto.getFile());

        Post post = Post.createPost(requestDto.getContext(),
                uploadFile,user);

        postRepository.save(post);

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

    public List<PostResponseDto> getPostsLogin(Long userId, Pageable pageable) {

        return  postRepository.getPostsByUserId(userId,pageable).toList();
    }


    public List<PostResponseDto> getFollowPost(Long userId, Pageable pageable) {
        Page<PostResponseDto> pageResult = postRepository.getPostsByFollow(userId,pageable);
        List<PostResponseDto> result =new ArrayList<>();

        for (PostResponseDto dto : pageResult) {

            dto.setIsFollow(postLikeRepository.IsFollowList(dto.getUser_id(), dto.getPost_id()));
            result.add(dto);
        }

        return  result;
    }

    public List<PostResponseDto> getPostsLiked(Long userId, Pageable pageable) {
        return  postRepository.getPostsLiked(userId,pageable);
    }

    public List<PostResponseDto> getScraps(Long userId, Pageable pageable) {
        return postRepository.getScraps(userId,pageable);
    }

    public List<PostResponseLoginDto> getPostsLogins(Long userId, Pageable pageable) {
        return postRepository.getPostsLogins (userId, pageable);

    }
}
