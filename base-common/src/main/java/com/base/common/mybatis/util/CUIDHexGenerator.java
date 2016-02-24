/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2004 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *
 * $ com.boco.transnms.client.graphkit.ui.TMStyle $
 */
/**
 *Revision Information:
 *
 *@version 1.0 2004-7-28 15:48:48 Initial release(zhangxu)
 */

package com.base.common.mybatis.util;

public class CUIDHexGenerator extends AbstractCUIDGenerator {
	private static CUIDHexGenerator instance = new CUIDHexGenerator();
	public static final String CUID_PREFIX_SEPRATOR = "-";

	private String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public String generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(
				format(getJVM())).append(sep).append(format(getHiTime()))
				.append(sep).append(format(getLoTime())).append(sep).append(
						format(getCount())).toString();
	}

	public String generate(String prefix) {
		return prefix + "-" + generate();
	}

	public static CUIDHexGenerator getInstance() {
		return instance;
	}

	public static String compose(String className, String post) {
		return className + CUID_PREFIX_SEPRATOR + post;
	}

	public static void main(String[] args) {
		System.out.println(CUIDHexGenerator.getInstance().generate());
	}
}
