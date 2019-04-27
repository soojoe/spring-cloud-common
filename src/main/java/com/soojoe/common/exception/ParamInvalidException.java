package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 参数错误异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class ParamInvalidException extends AbstactCommonException {

    public ParamInvalidException() {
        this(ResponseActionConstants.ALERT);
    }

    public ParamInvalidException(String message) {
        this(ResponseActionConstants.ALERT, message);
    }

    public ParamInvalidException(int action) {
        code = ResponseCodeConstants.ACCESS_DENIED;
        this.action = action;
    }

    public ParamInvalidException(int action, String message) {
        super(message);
        code = ResponseCodeConstants.PARAM_INVALID;
        this.action = action;
        this.message = message;
    }
}
