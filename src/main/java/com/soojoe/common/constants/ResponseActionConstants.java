package com.soojoe.common.constants;

/**
 * action说明
 * <p>
 *     展示异常码，code&lt;0的时候才可能返回，表示任何接口都可能出现的错误时，系统后端推荐的错误展示方式。
 *     action可作为系统级别异常的兜底展示方式。
 * </p>
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class ResponseActionConstants {

    //业务异常时统一返回0
    public static final int NO_ACTION = 0;

    //忽略异常
    public static final int IGNORE = -1;

    //弹框
    public static final int ALERT = -2;

    //toast
    public static final int TOAST = -3;

    //弹框，点击确定后刷新页面
    public static final int ALERT_REFRESH = -4;
}
