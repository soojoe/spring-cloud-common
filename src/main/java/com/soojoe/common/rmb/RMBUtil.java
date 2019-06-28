package com.soojoe.common.rmb;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 人民币金额计算、转换工具
 *
 * @author soojoe
 * @version 1.0.0
 * @date 2019/06/28 14:58
 */
public class RMBUtil {

	private static final BigDecimal ONE_HUNDRED = BigDecimal.TEN.pow(2);

	/**
	 * 元 --> 分
	 *
	 * @param yuan RMB金额（单位：元）
	 * @return RMB金额（单位：分）
	 */
	public static int yuan2cent(String yuan) {
		yuan = StringUtils.trimToNull(yuan);
		if (yuan == null || !NumberUtils.isCreatable(yuan) || StringUtils.startsWith(yuan, "-")) {
			throw new IllegalArgumentException("RMB yuan amount is illegal");
		}
		BigDecimal yuanBig = new BigDecimal(yuan).setScale(2, RoundingMode.HALF_EVEN);
		return yuanBig.multiply(ONE_HUNDRED).intValue();
	}


	/**
	 * 分 --> 元
	 *
	 * @param cent RMB金额（单位：分）
	 * @return RMB金额（单位：元）
	 */
	public static BigDecimal cent2yuan(Integer cent) {
		if (cent == null || cent < 0) {
			throw new IllegalArgumentException("RMB cent amount is illegal");
		}
		return new BigDecimal(cent).divide(ONE_HUNDRED, 2, RoundingMode.HALF_EVEN);
	}

	/**
	 * 分 --> 元字符串
	 *
	 * @param cent RMB金额（单位：分）
	 * @return RMB金额字符串（单位：元）
	 */
	public static String cent2yuanStr(Integer cent) {
		return cent2yuan(cent).toString();
	}

	/**
	 * 计算订单金额（单位：元）
	 *
	 * @param priceInCent 价格（单位：分）
	 * @param buyNum 购买数量
	 * @return 订单金额（单位：元）
	 */
	public static BigDecimal calculateOrderAmount(Integer priceInCent, int buyNum) {
		BigDecimal priceInYuan = cent2yuan(priceInCent);
		return priceInYuan.multiply(BigDecimal.valueOf(buyNum));
	}

	/**
	 * 计算订单金额（单位：分）
	 *
	 * @param priceInCent 价格（单位：分）
	 * @param buyNum 购买数量
	 * @return 订单金额（单位：分）
	 */
	public static BigDecimal calculateOrderFenAmount(Integer priceInCent, int buyNum) {
		return BigDecimal.valueOf(priceInCent).multiply(BigDecimal.valueOf(buyNum));
	}
}
