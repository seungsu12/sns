package code.sns.domain.dto.request;


import lombok.Data;

@Data
public class CommentRequestDto {

    private Long userId;
    private Long postId;
    private String context;
}
