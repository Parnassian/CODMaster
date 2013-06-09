package com.jactivision.masterserver.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * UDP Server
 * 
 * @author Parnassian
 *
 */
public class UDPServer implements Runnable {
	DatagramSocket socket = null;
	byte[] receiveData = new byte[1024];

	public UDPServer(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			DatagramPacket packet = new DatagramPacket(receiveData,
					receiveData.length);
			try {
				socket.receive(packet);
				new Thread(new Responder(socket, packet)).start();
			} catch (IOException e) {
				// optional thrown, we must keep alive anyway
				e.printStackTrace();
			}
		}
	}
}
