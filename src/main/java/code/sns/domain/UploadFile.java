package code.sns.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    //    public static UploadFile CreateUploadFile(String uploadFileName, String storeFileName) {
//
//       UploadFile file = new UploadFile();
//       file.uploadFileName = uploadFileName;
//       file.storeFileName = storeFileName;
//        return file;
//    }
}
