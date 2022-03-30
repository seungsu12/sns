package code.sns.domain.dto.response;


import code.sns.domain.enums.Job;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;


@Data
public class FollowResponseDto {
    private Long userId;
    private String nickname;
    private String username;
    private String profile_img;
    private Job job;

    @QueryProjection
    public FollowResponseDto(Long userId, String nickname, String username, String profile_img, Job job) {
        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.profile_img = profile_img;
        this.job =job;
    }

}
