package me.hireny.haven.core.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: HttpError
 * @Author: hireny
 * @Date: Create in 2019/11/18 14:54
 * @Description: TODO
 */
@Slf4j
@Getter
@AllArgsConstructor
public class HttpError extends Error {
    private int status;
    private String message;

    private HttpError() {}

    public static HttpError badRequest() {
        return new HttpError(400, "Bad Request");
    }
}
