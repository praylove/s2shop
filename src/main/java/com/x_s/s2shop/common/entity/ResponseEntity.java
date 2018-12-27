package com.x_s.s2shop.common.entity;

import com.x_s.s2shop.common.constant.HttpConstant;

/**
 * 响应的参数
 */
public class ResponseEntity<T> {

    private int code;

    private String message;

    private boolean success;

    private T data;

    public static<S> ResponseEntity<S> ok(){
        return ok(null);
    }

    public static<S> ResponseEntity<S> ok(S data){
        ResponseEntity<S> entity = new ResponseEntity<>();
        entity.setCode(HttpConstant.SUCCESS_CODE);
        entity.setSuccess(true);
        entity.setData(data);
        return entity;
    }

    public static<S> ResponseEntity<S> fail(){
        return fail(null);
    }

    public static<S> ResponseEntity<S> fail(String message){
        ResponseEntity<S> entity = new ResponseEntity<>();
        entity.setCode(HttpConstant.SUCCESS_CODE);
        entity.setSuccess(false);
        entity.setMessage(message);
        return entity;
    }

    public static<S> ResponseEntity<S> error(){
        return error(null);
    }

    public static<S> ResponseEntity<S> error(String message){
        ResponseEntity<S> entity = new ResponseEntity<>();
        entity.setCode(HttpConstant.ERROR_CODE);
        entity.setSuccess(false);
        entity.setMessage(message);
        return entity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        if (message != null)    return message;
        return success ? "操作成功" : "操作失败";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
