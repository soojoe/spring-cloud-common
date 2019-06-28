package com.soojoe.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;

/**
 * 访问拒绝异常
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class AccessDeniedException extends AbstractCommonException {

  public AccessDeniedException() {
    this(ResponseActionConstants.ALERT);
  }

  public AccessDeniedException(String message) {
    this(ResponseActionConstants.ALERT, message);
  }

  public AccessDeniedException(int action) {
    code = ResponseCodeConstants.ACCESS_DENIED;
    this.action = action;
  }

  public AccessDeniedException(int action, String message) {
    super(message);
    code = ResponseCodeConstants.ACCESS_DENIED;
    this.action = action;
    this.message = message;
  }
}
