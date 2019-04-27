package com.soojoe.common.constants;

/**
 * 返回码说明
 *
 * <p>code说明：</p> <p> 0表示成功。其他值表示功能上异常，功能异常分为系统异常和业务异常。<br/> 1）系统异常<br/>
 * 不是某个具体业务功能点引发的异常，任何接口都可能返回。例如参数错误、无权限、未登录、系统错误（空指针等，一般统一专为系统错误异常）。<br/> 系统异常码范围 ： code&lt;0。<br/>
 * 返回系统异常时，同时会返回action，表示发生该系统功能异常时，希望前端的展示行为。<br/> 前端也有可能对具体某个接口和功能模块做对应code的功能层面处理。此时和展示样式可能冲突，因此优先级为：<br/>
 * 客户端本地配置code优先级&gt;action优先级&gt;默认处理code优先级<br/> 大部分情况下都返回系统异常即可，业务异常只有在有特定业务个性交互需求时才用到<br/>
 * 2）业务异常<br/> 业务异常范围：code&gt;0,具体值由具体业务确定<br/> 返回业务异常时，action统一返回0，具体的交互由业务功能配置决定。特定的业务接口才会返回特定的业务异常。<br/>
 * <strong>注意：本类只定义系统异常码，业务异常码不要定义在本类中。</strong> </p>
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */

public class ResponseCodeConstants {

  //成功
  public static final int SUCCESS = 0;

  //服务器错误，空指针、数组越界等非业务代码抛出异常
  public static final int SERVER_ERROR = -1;
  //非法请求，参数异常、参数格式错误等业务抛出的对调用接口的请求非法性抛出的通用错误
  public static final int PARAM_INVALID = -2;
  //无权限
  public static final int ACCESS_DENIED = -3;
  //用户未登录，且该接口需要登录
  public static final int LOGIN_REQUIRED = -4;

  //帐号不存在
  public static final int ACCOUNT_NOT_FOUND = -100;
  //验证码错误
  public static final int VERIFY_CODE_ERROR = -101;
  //access-token无效
  public static final int ACCESS_TOKEN_ERROR = -102;
  //refresh-token无效
  public static final int REFRESH_TOKEN_ERROR = -103;
  //多设备登录被剔除
  public static final int ACCOUNT_KICK_OUT = -104;

  //帐号被禁用
  public static final int ACCOUNT_FORBID = -10000;
}
