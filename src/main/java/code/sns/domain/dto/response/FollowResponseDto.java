package code.sns.domain.dto.response;


import code.sns.domain.enums.Job;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;


@Data
public class FollowResponseDto {
    private Long user_id;
    private String username;
    private String profile_img;
    private Job job;

    @QueryProjection
    public FollowResponseDto(Long user_id,String username, String profile_img,Job job) {
        this.user_id =user_id;
        this.username = username;
        this.profile_img = profile_img;
        this.job =job;
    }

}
