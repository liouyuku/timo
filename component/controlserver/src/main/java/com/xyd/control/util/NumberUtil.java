package com.xyd.control.util;

public class NumberUtil {

	public static byte[] toBytes(String hexString) {
		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() >> 1];
		int index = 0;
		for (int i = 0; i < hexString.length(); i++) {
			if (index > hexString.length() - 1)
				return byteArray;
			byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
			byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
			byteArray[i] = (byte) (highDit << 4 | lowDit);
			index += 2;
		}
		return byteArray;
	}

	public static String bytesToHex(byte[] byteArray) {
		final StringBuilder hexString = new StringBuilder("");
		if (byteArray == null || byteArray.length <= 0)
			return null;
		for (int i = 0; i < byteArray.length; i++) {
			int v = byteArray[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				hexString.append(0);
			}
			hexString.append(hv);
		}
		return hexString.toString().toLowerCase();
	}

	public static void main(String[] args) {
		/*StringBuilder sb = new StringBuilder();
		// 00 为对讲呼电梯
		// 01 为室内机招梯
		// 02 为门禁开锁联动电梯
		String linkage = "00";
		sb.append("FE").append(linkage).append("00").append(13 + "").append("04" + "")
				.append("90300304000000");
		byte[] d = NumberUtil.toBytes(sb.toString());
		System.out.println(sb.toString().trim());
		System.out.println(d);
		System.out.println(NumberUtil.bytesToHex(d));*/

		String s = "1204";
		System.out.println(s.substring(0,2));
		System.out.println(s.substring(2,4));
	}

	/**
	 * 将整型转换为字节数组~
	 * 
	 * @param integer
	 * @return
	 */
	public static byte[] int2bytes(int integer) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (integer & 0xff); // 最低位
		bytes[1] = (byte) ((integer >> 8) & 0xff); // 次低位
		bytes[2] = (byte) ((integer >> 16) & 0xff); // 次高位
		bytes[3] = (byte) (integer >>> 24); // 最高位，无符号右移。
		return bytes;
	}

	/**
	 * 将字节数组转换为整型~
	 * 
	 * @param bytes
	 * @return
	 */
	public static int bytes2int(byte[] bytes) {
		// 一个 byte 数据左移 24 位变成 0x??000000，再右移 8 位变成 0x00??0000（| 表示按位或）
		int integer = (bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00) | ((bytes[2] << 24) >>> 8) | (bytes[3] << 24);
		return integer;
	}
}
