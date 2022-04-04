package code.sns.repository.post;

import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.PostResponseLoginDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> findByIdDto(Long postId);

    List<PostResponseDto> getPosts();

    Page<PostResponseDto> getPostsByUserId(Long userId, Pageable pageable);

    Page<PostResponseDto> getPostsLogin(Long userId, Pageable pageable);

    Page<PostResponseDto> getPostsByFollow(Long userId, Pageable pageable);

    Page<PostResponseDto> getPostsLiked(Long userId, Pageable pageable);

    Page<PostResponseDto> getScraps(Long userId, Pageable pageable);

    List<PostResponseLoginDto> getPostsLogins(Long userId, Pageable pageable);
}
