package com.soojoe.common.dto;

import com.soojoe.common.constants.ResponseCodeConstants;

import java.io.Serializable;

/**
 * 给前端的统一返回对象
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class ResponseDTO<T> implements Serializable {

  private int code = ResponseCodeConstants.SUCCESS;
  private int action;
  private String message;
  private T result;
  private String uuid;

  public ResponseDTO(T result) {
    this.result = result;
  }

  public ResponseDTO(int code, int action, String message, String uuid) {
    this.code = code;
    this.action = action;
    this.message = message;
    this.uuid = uuid;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getAction() {
    return action;
  }

  public void setAction(int action) {
    this.action = action;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
