package code.sns;


import org.aspectj.weaver.bcel.BcelAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class queryTest {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Test
    void bc() {

        System.out.println(encoder.encode("123"));
    }
}
