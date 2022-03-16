package code.sns.service;

import code.sns.domain.Post;
import code.sns.domain.UploadFile;
import code.sns.domain.User;
import code.sns.domain.dto.PostRequestDto;
import code.sns.domain.dto.PostResponseDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.PostJpaRepository;
import code.sns.repository.UserJpaRepository;
import code.sns.upload.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final FileStore fileStore;

    public List<PostResponseDto> getPosts() {

        return postJpaRepository.getPosts();
    }

    public void createPost(PostRequestDto requestDto) throws IOException {

        User user = userJpaRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new NotFoundObjectException(String.format("해당 [%s] 아이디는 존재하지않습니다.",requestDto.getUser_id())));

        UploadFile uploadFile = fileStore.storeFile(requestDto.getFile());

        Post post = Post.createPost(requestDto.getContext(),
                uploadFile,user);

        postJpaRepository.save(post);

    }

    public PostResponseDto getPostById(Long id) {
        return postJpaRepository.findById(id).orElseThrow(()-> new NotFoundObjectException(String.format("해당 post[%s]가 존재하지 않습니다.",id)));
    }
}
