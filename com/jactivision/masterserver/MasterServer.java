package com.jactivision.masterserver;


import com.jactivision.masterserver.udp.UDPServer;

/**
 * 
 * Landing
 * 
 * @author Parnassian
 * @version 0.1
 *
 */
public class MasterServer {
	
	public static void main(String[] args) {
		UDPServer udpServer = new UDPServer(20510);
		new Thread(udpServer).start();
	}

}
