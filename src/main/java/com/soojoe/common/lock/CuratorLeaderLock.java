package com.soojoe.common.lock;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Curator主从锁
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class CuratorLeaderLock implements CLock {

  private final static Logger log = LoggerFactory.getLogger(CuratorLockContext.class);

  /**
   * 锁对象
   */
  private LeaderLatch leaderLatch;

  /**
   * 锁路径
   */
  private String lockPath;

  CuratorLeaderLock(LeaderLatch leaderLatch, String lockPath) {
    this.leaderLatch = leaderLatch;
    this.lockPath = lockPath;
  }

  @Override
  public boolean isHeldByProcess() {
    if (log.isInfoEnabled()) {
      log.info("is{}leader", leaderLatch.hasLeadership() ? " " : " not ");
    }
    return leaderLatch.hasLeadership();
  }
}
