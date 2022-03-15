package code.sns.domain.dto;


import code.sns.domain.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private LocalDate birth;

    @NotBlank
    private String userLink;

    private Gender gender;

}
