package code.sns.config.stomp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@ToString
public class NoticeMessage implements Serializable {


    private static final long serialVersionUID =1234125L;

//    private static String basicMsg ="%s님이 %s 하였습니다.";

    public enum Type{
        like,follow,comment,scrap;
    }
    
    private String username;
    
    private String profile_img;
    
    private String message;

    public static NoticeMessage createMessage(String username, String profile_img, Type type) {
        NoticeMessage message = new NoticeMessage();
        message.username = username;
        message.profile_img = profile_img;
        switch (type){
            case comment:
                message.message = String.format("%s님이 댓글을 남겼습니다.",username);
                break;
            case like:
                message.message = String.format("%s님이 좋아요를 눌렀습니다.",username);
                break;
            case follow:
                message.message =String.format("%s님이 팔로우 하셨습니다",username);
                break;
            case scrap:
                message.message =String.format("%s님이 스크랩 하셨습니다",username);
                break;
        }
        return message;
    }
}
