package code.sns.config.auth;

import code.sns.auth.PrincipalDetail;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthUtil {

    public static Long getAuthenticationUserId() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//            if (authentication.isAuthenticated()) {
//                throw new CustomException(ErrorCode.FORBIDDEN_USER, "권한이 없습니다.");
//            }
//            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
//            return principal.getId();

                        if (!authentication.isAuthenticated()) {
                throw new CustomException(ErrorCode.FORBIDDEN_USER, "권한이 없습니다.");
            }
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            return principal.getId();

        } catch (ClassCastException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.FORBIDDEN_AUTHORITY);

        }
    }
}
