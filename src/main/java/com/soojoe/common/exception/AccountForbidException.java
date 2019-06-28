package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 账户禁止异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class AccountForbidException extends AbstractCommonException {

    public AccountForbidException() {
        this(ResponseActionConstants.ALERT);
    }

    public AccountForbidException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public AccountForbidException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public AccountForbidException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.ACCOUNT_FORBID;
        this.action = action;
        this.message = message;
    }
}
