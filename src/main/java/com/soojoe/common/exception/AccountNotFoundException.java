package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 账户不存在异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class AccountNotFoundException extends AbstractCommonException {

    public AccountNotFoundException() {
        this(ResponseActionConstants.ALERT);
    }

    public AccountNotFoundException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public AccountNotFoundException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public AccountNotFoundException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.ACCOUNT_NOT_FOUND;
        this.action = action;
        this.message = message;
    }
}
