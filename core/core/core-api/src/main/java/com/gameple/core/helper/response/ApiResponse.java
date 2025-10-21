package com.gameple.core.helper.response;


import com.gameple.core.helper.error.ErrorMessage;
import com.gameple.core.helper.error.ErrorType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final ResultType result;

    @Nullable
    private final T data;

    @Nullable
    private final ErrorMessage error;

    public static ApiResponse<Object> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static <S> ApiResponse<S> error(ErrorType error) {
        return error(error, null);
    }

    public static <S> ApiResponse<S> error(ErrorType error, @Nullable Object errorData) {
        return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error, errorData));
    }
}
