package org.shiro.maven.common.exception;

public class CustomException extends Exception {

    //异常消息
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
