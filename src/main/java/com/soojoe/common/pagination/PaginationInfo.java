package com.soojoe.common.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class PaginationInfo<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final int  DEFAULT_PAGE_SIZE        = 10;
  private static final int  DEFAULT_PAGE_INDEX       = 1;
  private static final int  DEFAULT_TOTAL_PAGE_COUNT = 1;
  private static final long DEFAULT_TOTAL_COUNT      = 0L;
  private static final long DEFAULT_OFFSET           = 0L;

  //页索引
  private int pageIndex = 1;
  //每个页面大小
  private int pageSize = 10;
  //当前结果集记录数量
  private int currentPageSize = 0;
  //总页面数量
  private int totalPageCount = 1;
  //满足条件的记录数量
  private long totalCount = 0L;

  //是否有前一页
  private boolean hasPrevPage = false;
  //是否有下一页
  private boolean hasNextPage = false;

  //当前页偏移量
  private long offset = 0L;
  //当前页预计数量
  private int limit = 10;
  //排序条件
  private String orderCondition;

  //结果集
  private List<T> list = new ArrayList<>(0);

  public PaginationInfo() {
  }

  public PaginationInfo(int pageIndex, int pageSize) {
    this.setPageIndex(pageIndex);
    this.setPageSize(pageSize);
    this.setOffset((long) ((this.pageIndex - 1) * this.pageSize));
    this.setLimit(pageSize);
  }

  public PaginationInfo(int pageIndex, int pageSize, long totalCount) {
    this(pageIndex, pageSize);
    this.setTotalCount(totalCount);
  }

  public PaginationInfo(int pageIndex, int pageSize, long totalCount, List<T> list) {
    this(pageIndex, pageSize, totalCount);
    this.setList(list);
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    if (pageSize <= 0) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    this.pageSize = pageSize;
    this.setLimit(pageSize);
    this.setOffset((long) ((this.pageIndex - 1) * this.pageSize));
  }

  public int getPageIndex() {
    return this.pageIndex;
  }

  public void setPageIndex(int pageIndex) {
    if (pageIndex <= 0) {
      pageIndex = DEFAULT_PAGE_INDEX;
    }

    this.pageIndex = pageIndex;
    this.setOffset((long) ((this.pageIndex - 1) * this.pageSize));
  }

  public int getCurrentPageSize() {
    return currentPageSize;
  }

  public void setCurrentPageSize(int currentPageSize) {
    this.currentPageSize = currentPageSize;
  }

  public long getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(long totalCount) {
    if (totalCount < 0L) {
      totalCount = DEFAULT_TOTAL_COUNT;
    }

    if (totalCount == 0L) {
      this.totalPageCount = DEFAULT_TOTAL_PAGE_COUNT;
    } else {
      this.totalPageCount = (int) ((totalCount - 1L) / (long) this.pageSize + 1L);
    }

    if (this.pageIndex <= 0) {
      this.setPageIndex(DEFAULT_PAGE_INDEX);
    }

    if (this.pageIndex > this.totalPageCount) {
      this.setPageIndex(this.totalPageCount + 1);
    }

    this.hasPrevPage = this.pageIndex > DEFAULT_PAGE_INDEX;
    this.hasNextPage = this.pageIndex < totalPageCount;

    this.totalCount = totalCount;
  }

  public boolean isHasPrevPage() {
    return hasPrevPage;
  }

  public void setHasPrevPage(boolean hasPrevPage) {
    this.hasPrevPage = hasPrevPage;
  }

  public boolean isHasNextPage() {
    return hasNextPage;
  }

  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  public long getOffset() {
    return this.offset;
  }

  public void setOffset(long offset) {
    if (offset < 0L) {
      offset = DEFAULT_OFFSET;
    }

    this.offset = offset;
  }

  public int getLimit() {
    return this.limit;
  }

  public int getTotalPageCount() {
    return this.totalPageCount;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getOrderCondition() {
    return orderCondition;
  }

  public void setOrderCondition(String orderCondition) {
    this.orderCondition = orderCondition;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    if (list == null) {
      list = new ArrayList<>(0);
    }
    this.list = list;
    this.currentPageSize = list.size();
  }
}
