package code.sns.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@ToString
public class UserBirthDto implements Serializable {

    private Long userId;

    private String username;

    private String profile_img;

    private LocalDate birth;

    private Integer dDay;

    @QueryProjection
    public UserBirthDto(Long userId, String username, String profile_img,LocalDate birth) {
        this.userId = userId;
        this.username = username;
        this.profile_img = profile_img;
        this.birth = birth;


        this.dDay =Period.between(changeBirth(birth),LocalDate.now()).getDays();
    }

    private LocalDate changeBirth(LocalDate birth) {
        LocalDate now = LocalDate.now();
        LocalDate change = LocalDate.of(now.getYear(),birth.getMonth(),birth.getDayOfMonth());
        return change;
    }
}
