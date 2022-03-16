package code.sns.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostResponseDto {

    private Long user_id;
    private Long post_id;
    private String profile_img;
    private String username;
    private String nickname;
    private String context;
    private String storeFilename;


    @QueryProjection
    public PostResponseDto(Long user_id, Long post_id, String profile_img,String username, String nickname, String context, String storeFilename) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.profile_img = profile_img;
        this.username = username;
        this.nickname = nickname;
        this.context = context;
        this.storeFilename = storeFilename;
    }
}
