package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 验证码异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class VerifyCodeErrorException extends AbstactCommonException {

    public VerifyCodeErrorException() {
        this(ResponseActionConstants.ALERT);
    }

    public VerifyCodeErrorException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public VerifyCodeErrorException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public VerifyCodeErrorException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.VERIFY_CODE_ERROR;
        this.action = action;
        this.message = message;
    }
}
