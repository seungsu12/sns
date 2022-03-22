package code.sns.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {

    private String username;
    private String nickname;
    private String profile;
    private String profile_img;
    private LocalDate createdAt;
    private Integer followerCount;
    private Integer followingCount;

    @QueryProjection
    public UserProfileDto(String username, String nickname, String profile, String profile_img, LocalDate createdAt, Integer followerCount, Integer followingCount) {
        this.username = username;
        this.nickname = nickname;
        this.profile = profile;
        this.profile_img = profile_img;
        this.createdAt = createdAt;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}