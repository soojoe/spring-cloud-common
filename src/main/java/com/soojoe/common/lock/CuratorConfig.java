package com.soojoe.common.lock;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Curator配置
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
@ConfigurationProperties(prefix = "zookeeper")
public class CuratorConfig {

  /**
   * zk连接地址
   */
  private String address;

  /**
   * 重试等待试卷
   */
  private Integer retryWaitMillis;

  /**
   * 最大重试次数
   */
  private Integer maxTryCount;

  /**
   * 会话超时试卷
   */
  private Integer sessionTimeout;

  /**
   * 链接超时时间
   */
  private Integer connectionTimeout;

  /**
   * 基础路径
   */
  private String basePath;


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getRetryWaitMillis() {
    return retryWaitMillis;
  }

  public void setRetryWaitMillis(Integer retryWaitMillis) {
    this.retryWaitMillis = retryWaitMillis;
  }

  public Integer getMaxTryCount() {
    return maxTryCount;
  }

  public void setMaxTryCount(Integer maxTryCount) {
    this.maxTryCount = maxTryCount;
  }

  public Integer getSessionTimeout() {
    return sessionTimeout;
  }

  public void setSessionTimeout(Integer sessionTimeout) {
    this.sessionTimeout = sessionTimeout;
  }

  public Integer getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(Integer connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }

  public String getBasePath() {
    return basePath;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }
}
