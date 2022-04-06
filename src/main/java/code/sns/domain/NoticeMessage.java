package code.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@ToString
public class NoticeMessage implements Serializable {


    private static final long serialVersionUID =1234125L;

    private static String basicMsg ="%s님이 %s 하였습니다.";

    @JsonProperty("username")
    private String username;

    @JsonProperty("message")
    private String message;

    public NoticeMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
