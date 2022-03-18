package code.sns.domain.dto;

import code.sns.domain.Comment;
import code.sns.domain.Post;
import code.sns.domain.PostLike;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostResponseDto {

    private Long user_id;
    private Long post_id;
    private String profile_img;
    private String username;
    private String nickname;
    private String context;
    private String storeFilename;
    private LocalDate createdAt;
    private Integer postLikes;
    private Integer comments;


    @QueryProjection
    public PostResponseDto(Long user_id, Long post_id, String profile_img, String username, String nickname,
                           String context, String storeFilename, LocalDate createdAt, Integer postLikes,
                           Integer comments) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.profile_img = profile_img;
        this.username = username;
        this.nickname = nickname;
        this.context = context;
        this.storeFilename = storeFilename;
        this.createdAt = createdAt;
        this.postLikes =postLikes;
        this.comments = comments;
    }
}
