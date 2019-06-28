package com.soojoe.common.filter;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * dubbo调用追踪
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER}, order = -9999)
public class DubboTraceFilter implements Filter {

	private static final String REQ_ID="req_id";
	private static final Logger LOGGER = LoggerFactory.getLogger(DubboTraceFilter.class);
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		boolean consumerSide = RpcContext.getContext().isConsumerSide();
		boolean providerSide = RpcContext.getContext().isProviderSide();
		String traceId;
		if (consumerSide) {
			// 从ThreadLocal里获取traceId并保存到RPContext
			traceId = TraceIdUtils.getTraceId();
			RpcContext.getContext().setAttachment(REQ_ID, traceId);
			LOGGER.info("[TraceIdFilter-consumerSide] set TraceId to RpcContext,TraceId={}", traceId);
			return invoker.invoke(invocation);
		}
		if (providerSide) {
			// 从RpcContext里获取traceId并设置到ThreadLocal
			traceId = RpcContext.getContext().getAttachment(REQ_ID);
			TraceIdUtils.setTraceId(traceId);
			MDC.put(REQ_ID, traceId);
			LOGGER.info("[TraceIdFilter-providerSide] get TraceId from RpcContext and set it to traceUtils,TraceId={}", traceId);
			try {
				return invoker.invoke(invocation);
			} finally {
				TraceIdUtils.clear();
			}
		}
		return null;
	}
}
