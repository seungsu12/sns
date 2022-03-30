package code.sns.repository.comment;

import code.sns.domain.dto.response.CommentResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentCustomRepository {

    List<CommentResponseDto> getCommentByIdDto(Long id, Pageable pageable);

    CommentResponseDto getCommentById(Long id);
}
