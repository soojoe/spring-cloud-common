package com.soojoe.common.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Curator可重入锁
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class CuratorReentrantLock implements CLock {

  private final static Logger log = LoggerFactory.getLogger(CuratorLockContext.class);

  /**
   * 锁对象
   */
  private InterProcessMutex ipm;

  /**
   * 锁路径
   */
  private String lockPath;

  CuratorReentrantLock(InterProcessMutex ipm, String lockPath) {
    this.ipm = ipm;
    this.lockPath = lockPath;
  }

  /**
   * 获取锁
   *
   * @param timeout 超时时间
   * @param timeUnit 时间单位
   * @return 是否获取到锁
   */
  @Override
  public boolean lock(long timeout, TimeUnit timeUnit) {
    try {
      boolean locked = ipm.acquire(timeout, timeUnit);
      if (log.isInfoEnabled()) {
        log.info("acquire {} lock {}", lockPath, locked ? "successfully" : "unsuccessfully");
      }
      return locked;
    } catch (Exception e) {
      log.error("can not acquire {} lock, unknown error happened", lockPath, e);
      throw new RuntimeException("this should not happened for zookeeper", e);
    }
  }

  /**
   * 释放锁
   */
  @Override
  public void unlock() {
    try {
      ipm.release();
      if (log.isInfoEnabled()) {
        log.info("release {} lock successfully", lockPath);
      }
    } catch (Exception e) {
      log.error("can not release {} lock, unknown error happened", lockPath, e);
    }
  }

  /**
   * 是否拥有锁
   *
   * @return 是否拥有锁
   */
  @Override
  public boolean isHeldByThread() {
    return ipm.isOwnedByCurrentThread();
  }

}
