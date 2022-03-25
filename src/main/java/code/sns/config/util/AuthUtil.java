package code.sns.config.util;

import code.sns.auth.PrincipalDetail;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public static Long getAuthenticationUserId() {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication == null) {
            throw new CustomException (ErrorCode.FORBIDDEN_USER,"권한이 없습니다.");
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return  principal.getId();
    }
}
