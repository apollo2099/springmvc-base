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

import java.net.InetAddress;

public abstract class AbstractCUIDGenerator {
	private static final int IP;
	static {
		int ipadd;
		try {

			int result = 0;
			for (int i = 0; i < 4; i++) {
				result = (result << 8) - Byte.MIN_VALUE
						+ InetAddress.getLocalHost().getAddress()[i];
			}
			ipadd = result;
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public AbstractCUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (AbstractCUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

}
