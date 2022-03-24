package code.sns.repository.post;

import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.PostResponseLoginDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PostCustomRepository {

    Optional<PostResponseDto> findByIdDto(Long postId);
    List<PostResponseDto> getPosts();
    Page<PostResponseLoginDto> getPostsByUserId(Long userId, Pageable pageable);

    Page<PostResponseLoginDto> getPostsLogin(Long userId, Pageable pageable);

    List<PostResponseLoginDto> getPostsByFollow(Long userId, Pageable pageable);
}
