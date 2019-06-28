package com.soojoe.common.lock;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁行为
 *
 * 注意：
 *    1.因注解使用了AOP，注意AOP生效条件；
 *    2.注意需要增加zk配置才会生成相应bean；
 *    3.获取锁超时或者不为主节点时，均会抛出异常；
 *    4.不使用注解的方式参照AOP实现。
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LockAction {

  /**
   * 锁资源，支持Spring EL表达式
   */
  @AliasFor("key")
  String value() default "'default'";

  /**
   * 锁资源，支持Spring EL表达式
   */
  @AliasFor("value")
  String key() default "'default'";

  /**
   * 锁类型，默认可重入锁
   */
  LockType lockType() default LockType.REENTRANT_LOCK;

  /**
   * 获取锁的等待时间，默认10秒
   */
  long waitTime() default 10000L;

  /**
   * 时间单位，默认毫秒
   */
  TimeUnit unit() default TimeUnit.MILLISECONDS;

}
