package io.github.gefangshuai.wfinal.security.exception;

/**
 * Created by gefangshuai on 2015/7/21.
 */
public class NoCallLoginMethodException extends RuntimeException{
    public NoCallLoginMethodException() {
        super("please call SecurityKit.login method to login subject first!");
    }
}
