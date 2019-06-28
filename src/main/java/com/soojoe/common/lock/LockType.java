package com.soojoe.common.lock;

/**
 * 锁类型
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public enum LockType {

  /**
   * 可重入锁
   */
  REENTRANT_LOCK,

  /**
   * 主从锁
   */
  LEADER_LOCK,

}
