package com.soojoe.common.filter;


/**
 * traceId工具类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
public class TraceIdUtils {

	private static final ThreadLocal<String> traceIdCache
			= ThreadLocal.withInitial(String::new);

	public static String getTraceId() {
		return traceIdCache.get();
	}

	public static void setTraceId(String traceId) {
		traceIdCache.set(traceId);
	}

	public static void clear() {
		traceIdCache.remove();
	}

}
