package com.soojoe.common.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * 锁切面
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
@Aspect
@Order(0)
public class LockAspect {

  private final static Logger log = LoggerFactory.getLogger(CuratorLockContext.class);

  /**
   * 锁上下文
   */
  private CuratorLockContext lockContext;

  public LockAspect(CuratorLockContext lockContext) {
    this.lockContext = lockContext;
  }

  /**
   * Spring EL表达式解析器
   */
  final private ExpressionParser parser = new SpelExpressionParser();

  final private LocalVariableTableParameterNameDiscoverer discoverer =
      new LocalVariableTableParameterNameDiscoverer();

  @Around("@annotation(com.netease.edu100.common.util.lock.LockAction)")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    LockAction lockAction = method.getAnnotation(LockAction.class);
    String spel = lockAction.value();
    Object[] args = pjp.getArgs();
    String key = parse(spel, method, args);

    CLock lock = getLock(lockAction, key);
    if (lockAction.lockType() == LockType.LEADER_LOCK) {
      if (lock.isHeldByProcess()) {
        return pjp.proceed();
      } else {
        if (log.isInfoEnabled()) {
          log.info("is not leader, key is {}", key);
        }
         throw new RuntimeException("fail to acquire lock, key is " + key);
      }
    }

    if (!lock.lock(lockAction.waitTime(), lockAction.unit())) {
      if (log.isInfoEnabled()) {
        log.info("acquire lock unsuccessfully, key is {}", key);
      }
       throw new RuntimeException("acquire lock unsuccessfully, key is " + key);
    }
    if (log.isInfoEnabled()) {
      log.info("acquire lock successfully, key is {}", key);
    }
    try {
      return pjp.proceed();
    } finally {
      if (lock.isHeldByThread()) {
        lock.unlock();
        if (log.isInfoEnabled()) {
          log.info("release lock successfully, key is {}", key);
        }
      }
    }
  }

  private String parse(String spel, Method method, Object[] args) {
    String[] params = discoverer.getParameterNames(method);
    EvaluationContext context = new StandardEvaluationContext();
    for (int i = 0; i < params.length; i++) {
      context.setVariable(params[i], args[i]);
    }
    return parser.parseExpression(spel).getValue(context, String.class);
  }

  private CLock getLock(LockAction lockAction, String key) {
    switch (lockAction.lockType()) {
      case REENTRANT_LOCK:
        return lockContext.getReentrantLock(key);
      case LEADER_LOCK:
        return lockContext.getLeaderLock(key);
      default:
        throw new RuntimeException("lock type " + lockAction.lockType().name() + " not unsupported");
    }
  }


}
