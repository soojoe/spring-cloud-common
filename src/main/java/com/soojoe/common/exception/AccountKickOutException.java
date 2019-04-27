package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 账户被踢出异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class AccountKickOutException extends AbstactCommonException {

    public AccountKickOutException() {
        this(ResponseActionConstants.ALERT);
    }

    public AccountKickOutException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public AccountKickOutException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public AccountKickOutException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.ACCOUNT_KICK_OUT;
        this.action = action;
        this.message = message;
    }
}
