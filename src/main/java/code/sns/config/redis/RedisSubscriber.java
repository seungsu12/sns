package code.sns.config.redis;

import code.sns.domain.NoticeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.x.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {
            String body = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            log.info("구독자 메시지 수신  {}",body);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
