package code.sns.domain.dto.request;


import code.sns.domain.enums.Gender;
import code.sns.domain.enums.Job;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@ToString
public class UserRequestDto {

    @Email(message = "이메일 형식이 아닙니다")
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;


    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Job job;


    @Enumerated(EnumType.STRING)
    private Gender gender;

}
