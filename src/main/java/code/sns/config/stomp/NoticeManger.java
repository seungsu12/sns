package code.sns.config.stomp;

import code.sns.config.auth.AuthUtil;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class NoticeManger {

    private final SimpMessageSendingOperations sendingOperations;

    public void sendNotice(Long toUserId ,NoticeMessage noticeMessage) {
        String url = "/topic/notice/"+toUserId;
        Long loginId = AuthUtil.getAuthenticationUserId();
        if (loginId.equals(toUserId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        try {
            sendingOperations.convertAndSend(url, noticeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
