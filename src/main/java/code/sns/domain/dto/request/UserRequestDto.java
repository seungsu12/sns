package code.sns.domain.dto.request;


import code.sns.domain.enums.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
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

    private Gender gender;

}
