package code.sns.config;

import code.sns.service.HashTagService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class HashTagConfig {

    public static List<String> extractionHash(String text) {
        List<String> result = new ArrayList<> ();
        String code ="#([0-9a-zA-Z가-힣]*)";
        Pattern p = Pattern.compile (code);
        Matcher m = p.matcher (text);
        while (m.find ()) {
            result.add (m.group ());
        }
        return result;
    }


}
