package com.jactivision.masterserver.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.jactivision.masterserver.packets.PacketBuilder;
import com.jactivision.masterserver.servers.Server;
import com.jactivision.masterserver.servers.Servers;
import com.jactivision.masterserver.utils.Time;

/**
 * 
 * @author Parnassian
 *
 */
public class Responder implements Runnable {

    DatagramSocket socket = null;
    DatagramPacket packet = null;

    public Responder(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    public void run() {
    	byte[] receivedBytes = packet.getData();
    	if(receivedBytes == null || packet.getLength() == 0) {
    		return;
    	}
    	String data = new String(packet.getData(), 0, packet.getLength());
    	if(data.length() < 5) {
    		return;
    	}
    	Server server = new Server(packet.getAddress().getHostAddress(), packet.getPort());
    	if(data.substring(4, data.length()).startsWith("getservers")) {
    		// get servers packet, sent by client
    		PacketBuilder packetBuilder = new PacketBuilder();
    		packetBuilder.addHeader();
    		packetBuilder.typeServersResponse();
    		for(final Server codServer : Servers.get()) {
    			packetBuilder.addServer(codServer.getServerPacket());
    		}
    		packetBuilder.end();
    		byte[] reply = packetBuilder.getBytes();
    		DatagramPacket response = getResponse(reply, reply.length);
    		sendResponse(response);
    		StringBuilder str = new StringBuilder("[");
			str.append(Time.get()).append("] ").append(server.toString()).append(" requested server list.");
			System.out.println(str.toString());
    	}
    	if(data.substring(4, data.length()).startsWith("heartbeat")) {
    		// hearbeat, now check if server connected or disconnected
    		if(data.toLowerCase().contains("cod")) {
    			// connect
    			if(Servers.containsServer(server)) {
    				Servers.removeServer(server);
    			}
    			Servers.heartBeatServerMap().put(server, System.currentTimeMillis());
    			StringBuilder str = new StringBuilder("[");
    			str.append(Time.get()).append("] ").append(server.toString()).append(" sent a heartbeat.");
    			System.out.println(str.toString());
    		}
    		if(data.toLowerCase().contains("flatline")) {
    			// disconnect
    			if(Servers.containsServer(server)) {
    				Servers.removeServer(server);
    			}
    			StringBuilder str = new StringBuilder("[");
    			str.append(Time.get()).append("] ").append(server.toString()).append(" disconnected.");
    			System.out.println(str.toString());
    		}
    	}
    }
    
    private void sendResponse(DatagramPacket response) {
    	try {
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private DatagramPacket getResponse(byte[] data, int length) {
    	return new DatagramPacket(data, data.length,
                packet.getAddress(), packet.getPort());
    }
}
