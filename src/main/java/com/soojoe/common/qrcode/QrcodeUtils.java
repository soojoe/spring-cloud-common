package com.soojoe.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.netease.edu100.common.exception.ParamInvalidException;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 二维码生成工具类
 *
 * @author soojoe
 * @version 1.0.0
 * @date 2019/06/28 14:58
 */
public class QrcodeUtils {


	// 设定输出的类型
	private static final String CONTENT_TYPE_PNG = "image/png;charset=utf-8";
	private static final String PNG_FORMAT = "png";

	private static int COMMON_BUFFER_SIZE = 10 * 1024;                // 10K

	public static void writeQrCode(String url, HttpServletResponse response) {
		InputStream in = null;
		OutputStream out = null;
		try {
			String format = PNG_FORMAT;
			BufferedImage image = generateQrCode(url);

			response.setContentType(CONTENT_TYPE_PNG);

			ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
			ImageIO.write(image, format, imageOut);
			in = new ByteArrayInputStream(imageOut.toByteArray());

			BufferedInputStream bin = new BufferedInputStream(in, COMMON_BUFFER_SIZE);
			out = response.getOutputStream();
			BufferedOutputStream bout = new BufferedOutputStream(out, COMMON_BUFFER_SIZE);

			int b = -1;
			while ((b = bin.read()) != -1) {
				bout.write(b);
			}
			bout.flush();
		} catch (Exception e) {
			throw new ParamInvalidException("二维码生成失败");
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	public static BufferedImage generateQrCode(String url) throws WriterException {
		int width = 512;
		int height = 512;

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//去margin
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;

		bitMatrix = new MultiFormatWriter()
				.encode(url, BarcodeFormat.QR_CODE, width, height, hints);
		// 生成矩阵
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y,
						bitMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
			}
		}

		return image;
	}

	public static void main(String[] args) throws Exception {
		String url = "https://www.baidu.com";
		int width = 256;
		int height = 256;
		String format = PNG_FORMAT;

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix = new MultiFormatWriter()
				.encode(url, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y,
						bitMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
			}
		}

		ImageIO.write(image, format, new File("C:/test.png"));
	}

}
