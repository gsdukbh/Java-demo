package top.werls.springsecurity.jwtdemo2.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * @author leejiawei
 */
@Data
public class BasicResult<T> implements Serializable {

    public enum ResultCode {
        /**
         * 成功标记
         */
        SUCCESS(200, "操作成功"),
        /**
         * 失败
         */
        FAILED(500, "操作失败"),
        /**
         * 检验失败
         */
        VALIDATE_FAILED(404, "参数检验失败"),
        /**
         * 过期
         */
        UNAUTHORIZED(401, "暂未登录或token已经过期"),
        /**
         * 无权
         */
        FORBIDDEN(403, "没有相关权限");
        private final Integer code;
        private final String message;

        private ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public static final long ID = 1L;

    private String message;

    private Integer code;

    private Long timeStamp;

    private T data;

    public BasicResult() {
    }

    public BasicResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.timeStamp = System.currentTimeMillis();
    }

    public static <T> BasicResult<T> success(T data) {
        return new BasicResult<T>(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), data);
    }

    public static <T> BasicResult<T> success(T data, String message) {
        return new BasicResult<T>(message, ResultCode.SUCCESS.getCode(), data);
    }

    public static <T> BasicResult<T> failed(T data) {
        return new BasicResult<T>(ResultCode.FAILED.getMessage(), ResultCode.FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> failed(T data, String message) {
        return new BasicResult<T>(message, ResultCode.FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> validateFailed(T data, String message) {
        return new BasicResult<T>(message, ResultCode.VALIDATE_FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> validateFailed(T data) {
        return new BasicResult<T>(ResultCode.VALIDATE_FAILED.getMessage(), ResultCode.VALIDATE_FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> unauthorized(T data, String message) {
        return new BasicResult<T>(message, ResultCode.UNAUTHORIZED.getCode(), data);
    }

    public static <T> BasicResult<T> unauthorized(T data) {
        return new BasicResult<T>(ResultCode.UNAUTHORIZED.getMessage(), ResultCode.FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> forbidden(T data) {
        return new BasicResult<T>(ResultCode.UNAUTHORIZED.getMessage(), ResultCode.FAILED.getCode(), data);
    }

    public static <T> BasicResult<T> forbidden(T data, String message) {
        return new BasicResult<T>(message, ResultCode.FORBIDDEN.getCode(), data);
    }

}
