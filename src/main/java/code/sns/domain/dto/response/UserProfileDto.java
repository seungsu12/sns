package code.sns.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserProfileDto {

    private Long userId;
    private String username;
    private String nickname;
    private String profile;
    private String profile_img;
    private LocalDate createdAt;
    private Integer followerCount;
    private Integer followingCount;

    @QueryProjection
    public UserProfileDto(Long userId, String username, String nickname, String profile, String profile_img, LocalDateTime createdAt, Integer followerCount, Integer followingCount) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.profile = profile;
        this.profile_img = profile_img;
        this.createdAt = createdAt.toLocalDate ();
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}
