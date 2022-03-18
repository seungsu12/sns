package code.sns.repository.comment;

import code.sns.domain.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentCustomRepository {

    List<CommentResponseDto> getCommentByIdDto(Long id);
}
