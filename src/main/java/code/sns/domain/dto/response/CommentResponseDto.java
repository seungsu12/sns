package code.sns.domain.dto.response;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CommentResponseDto {

    private Long comment_id;
    private Long user_id;
    private String username;
    private String context;
    private String profile_img;

    @QueryProjection
    public CommentResponseDto(Long comment_id, Long user_id, String username, String context, String profile_img) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.username = username;
        this.context = context;
        this.profile_img = profile_img;
    }
}
