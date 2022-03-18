package code.sns.domain.dto.request;


import lombok.Data;

@Data
public class CommentRequestDto {

    private Long user_id;
    private Long post_id;
    private String context;
}
