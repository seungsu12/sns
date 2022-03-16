package code.sns.repository.post;

import code.sns.domain.dto.PostResponseDto;

import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> findByIdDto(Long id);
    List<PostResponseDto> getPosts();
}
