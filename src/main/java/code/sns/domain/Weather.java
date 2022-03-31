package code.sns.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class Weather {

    private LocalDateTime time;

    private String tmp;

    private String windSpeed;

    //강수량
    private String pcp;

    private String sky;

}
