package com.jactivision.masterserver.network;

import java.nio.ByteBuffer;

/**
 * 
 * @author Parnassian
 * 
 */
public class Port {

	/**
	 * Converts a port into raw bytes
	 * 
	 * @param port
	 * @return byte[]
	 */
	public static byte[] getPortAsBytes(short port) {
		byte[] baValue = new byte[2];
		ByteBuffer buf = ByteBuffer.wrap(baValue);
		return buf.putShort(port).array();

	}

}
