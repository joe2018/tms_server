package org.myfun.tms_server.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Admin
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}
