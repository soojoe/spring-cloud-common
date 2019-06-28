package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * Access Token异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class AccessTokenErrorException extends AbstractCommonException {

    public AccessTokenErrorException() {
        this(ResponseActionConstants.ALERT);
    }

    public AccessTokenErrorException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public AccessTokenErrorException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public AccessTokenErrorException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.ACCESS_TOKEN_ERROR;
        this.action = action;
        this.message = message;
    }
}
