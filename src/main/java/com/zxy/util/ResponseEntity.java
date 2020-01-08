package com.zxy.util;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseEntity<T>
 *
 * @author black
 */
@Getter
@Setter
public class ResponseEntity<T> {
    private int code;
    private String msg;
    private T data;


    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param <T>
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<T>(200, "成功");
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<T>(200, "成功", data);
    }

    /**
     * 失败调用
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> error(int code, String msg) {
        return new ResponseEntity<T>(code, msg);
    }
}
