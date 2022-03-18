package code.sns.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    BAD_REQUEST_POST(HttpStatus.BAD_REQUEST, "글 입력값을 다시 확인하세요."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "권한이 없는 요청입니다"),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "해당 글을 찾을 수 없습니다"),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다");


    private final HttpStatus httpStatus;
    private final String detail;

}
