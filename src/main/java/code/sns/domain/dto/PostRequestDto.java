package code.sns.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequestDto {


    @NotBlank
    private String context;




    private MultipartFile file;





}
