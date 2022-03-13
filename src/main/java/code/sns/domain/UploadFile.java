package code.sns.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

   public static UploadFile CreateUploadFile(String uploadFileName, String storeFileName) {

       UploadFile file = new UploadFile();
       file.uploadFileName = uploadFileName;
       file.storeFileName = storeFileName;
        return file;
    }
}
