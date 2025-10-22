package com.gameple.core.helper.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E400, "요청이 올바르지 않습니다.", LogLevel.INFO),
    NOT_FOUND_DATA(HttpStatus.BAD_REQUEST, ErrorCode.E401, "해당 데이터를 찾을 수 없습니다.", LogLevel.ERROR),
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unknown error has occurred. Please try again later.", LogLevel.ERROR),

    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, ErrorCode.E1000, "이미 존재하는 이메일입니다.", LogLevel.INFO),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, ErrorCode.E1001, "이미 존재하는 닉네임입니다.", LogLevel.INFO),
    USER_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, ErrorCode.E1002, "비밀번호가 일치하지 않습니다.", LogLevel.INFO);

    private final HttpStatus status;
    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;

    ErrorType(HttpStatus status, ErrorCode code, String message, LogLevel logLevel) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }
}
