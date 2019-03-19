package util;

import java.io.UnsupportedEncodingException;

public class EnUtil {
	private static String key = "cN";

	public static String en(String s) throws UnsupportedEncodingException,
			Exception {
		String encrytStr;

		byte[] byteRe = AES.enCrypt(s, key);

		// 加密过的二进制数组转化成16进制的字符串
		encrytStr = AES.parseByte2HexStr(byteRe);
		return encrytStr;
	}

	public static String de(String s) throws UnsupportedEncodingException,
			Exception {
		byte[] encrytByte;
		// 加密过的16进制的字符串转化成二进制数组
		encrytByte = AES.parseHexStr2Byte(s);
		return AES.deCrypt(encrytByte, key);
	}
}
