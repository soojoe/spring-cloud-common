package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 重新刷新Token异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class RefreshTokenErrorException extends AbstactCommonException {

    public RefreshTokenErrorException() {
        this(ResponseActionConstants.ALERT);
    }

    public RefreshTokenErrorException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public RefreshTokenErrorException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public RefreshTokenErrorException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.REFRESH_TOKEN_ERROR;
        this.action = action;
        this.message = message;
    }
}
