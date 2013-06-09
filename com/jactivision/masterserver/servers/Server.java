package com.jactivision.masterserver.servers;

import com.jactivision.masterserver.network.IPAddress;
import com.jactivision.masterserver.network.Port;

/**
 * 
 * @author Parnassian
 *
 */
public class Server {
	private String ip = null;
	private int port = 0;
	
	public Server(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public String getIPAddress() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public String toString() {
		return ip + ":" + port;
	}
	
	/**
	 * Puts the server IP and port into 6 bytes
	 * @param server
	 * @param port
	 * @return server packet byte[]
	 */
	public byte[] getServerPacket() {
		byte[] rawIPBytes = IPAddress.asBytes(ip);
		byte[] portBytes = Port.getPortAsBytes((short) port);
		return new byte[] { rawIPBytes[0], rawIPBytes[1], rawIPBytes[2], rawIPBytes[3], portBytes[0], portBytes[1] };
	}

}
