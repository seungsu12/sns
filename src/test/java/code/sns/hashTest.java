package code.sns;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class hashTest {

    @Test
    void hashExtract() {
        String context ="여기 #우리집 강아지 너무 예뻐요 #강아지 #귀요미 #이쁨";

        String[] split = context.split ("#([0-9a-zA-Z가-힣]*)");

        for (String s : split) {
            System.out.println ("s = " + s);
        }

    }
}
