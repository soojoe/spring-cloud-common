package com.soojoe.common.lock;

import java.util.concurrent.TimeUnit;

/**
 * Curator锁
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public interface CLock {

  /**
   * 加锁
   *
   * @param timeout 超时时间
   * @param timeUnit 时间单位
   * @return 是否加锁成功
   */
  default boolean lock(long timeout, TimeUnit timeUnit) {
    throw new RuntimeException("unimplemented function");
  }

  /**
   * 释放锁
   */
  default void unlock() {
    throw new RuntimeException("unimplemented function");
  }

  /**
   * 线程是否拥有锁
   *
   * @return 是否拥有锁
   */
  default boolean isHeldByThread() {
    throw new RuntimeException("unimplemented function");
  }

  /**
   * 进程是否拥有锁
   *
   * @return 是否拥有锁
   */
  default boolean isHeldByProcess() {
    throw new RuntimeException("unimplemented function");
  }

}
