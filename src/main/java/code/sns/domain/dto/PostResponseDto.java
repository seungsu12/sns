package code.sns.domain.dto;

import code.sns.domain.Comment;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {

    private Long user_id;
    private Long post_id;
    private String username;
    private String context;
    private String img_path;
    private List<Comment> comments;

}
