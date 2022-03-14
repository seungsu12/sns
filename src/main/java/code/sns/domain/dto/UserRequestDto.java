package code.sns.domain.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UserRequestDto {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @NotBlank
    private LocalDate birth;

    @NotBlank
    private String userLink;

}
