package code.sns.domain.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequestDto {


    @NotBlank
    private Long user_id;
    private String context;
    private MultipartFile file;



}
