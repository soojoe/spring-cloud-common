package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 通用业务异常基类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public abstract class AbstractCommonException extends RuntimeException {

  protected int code = ResponseCodeConstants.SERVER_ERROR;
  protected int action = ResponseActionConstants.TOAST;
  protected String message;

  protected AbstractCommonException() {
  }

  protected AbstractCommonException(String message) {
    super(message);
  }

  public int getCode() {
    return code;
  }

  public int getAction() {
    return action;
  }

}
