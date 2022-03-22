package code.sns.repository.post;

import code.sns.domain.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> findByIdDto(Long id);
    List<PostResponseDto> getPosts();
    Page<PostResponseDto> getPostsByUserId(Long id, Pageable pageable);

}
