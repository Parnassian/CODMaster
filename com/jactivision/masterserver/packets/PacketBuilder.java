package com.jactivision.masterserver.packets;

import java.util.ArrayList;

/**
 * 
 * @author Parnassian
 * 
 * Builds a packet to send to the server
 * 
 */
public class PacketBuilder {
	private ArrayList<Byte> bytes = new ArrayList<Byte>();
	
	public void addHeader() {
		for(int x = 0; x < 4; x++) {
			bytes.add((byte) 0xff);
		}
	}
	
	public void typeServersResponse() {
		for(byte aByte : "getserversResponse".getBytes()) {
			bytes.add(aByte);
		}
		bytes.add( (byte) 0x5c);
	}
	
	public void addServer(byte[] serverBytes) {
		for(byte b : serverBytes) {
			bytes.add(b);
		}
		bytes.add( (byte) 0x5c);
	}
	
	public void end() {
		for(byte aByte : "EOT".getBytes()) {
			bytes.add(aByte);
		}
	}
	
	public byte[] getBytes() {
		byte[] byteArr = new byte[bytes.size()];
		for(int x = 0; x < bytes.size(); x++) {
			byteArr[x] = bytes.get(x);
		}
		return byteArr;
	}

}
