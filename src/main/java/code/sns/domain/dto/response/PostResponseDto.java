package code.sns.domain.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RedisHash
public class PostResponseDto implements Serializable {
    private Long user_id;
    private Long post_id;
    private String profile_img;
    private String username;
    private String nickname;
    private String context;
    private String storeFilename;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdAt;
    private Integer postLikeCount;
    private Integer commentsCount;
    private Integer scrapCount;
    private Boolean isLike;
    private Boolean isScrap;
    private String hashes;


    @QueryProjection
    public PostResponseDto(Long user_id, Long post_id, String profile_img, String username, String nickname, String context, String storeFilename, LocalDateTime createdAt, Integer postLikeCount, Integer commentsCount,Integer scrapCount){
        this.user_id = user_id;
        this.post_id = post_id;
        this.profile_img = profile_img;
        this.username = username;
        this.nickname = nickname;
        this.context = context;
        this.storeFilename = storeFilename;
        this.createdAt = createdAt.toLocalDate ();
        this.postLikeCount = postLikeCount;
        this.commentsCount = commentsCount;
        this.scrapCount = scrapCount;

    }
}
