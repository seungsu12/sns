package code.sns.config.redis;

import code.sns.config.stomp.NoticeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String,Object> redisTemplate;



    public void publish(ChannelTopic topic, NoticeMessage noticeMessage) {
        redisTemplate.convertAndSend(topic.getTopic(), noticeMessage);
    }


}
