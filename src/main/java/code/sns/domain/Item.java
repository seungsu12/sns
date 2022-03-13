package code.sns.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Item {

    private String name;
    private MultipartFile file;
}
