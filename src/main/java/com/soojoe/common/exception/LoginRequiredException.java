package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 登录请求异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class LoginRequiredException extends AbstractCommonException {

    public LoginRequiredException() {
        this(ResponseActionConstants.ALERT);
    }

    public LoginRequiredException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public LoginRequiredException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public LoginRequiredException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.LOGIN_REQUIRED;
        this.action = action;
        this.message = message;
    }
}
