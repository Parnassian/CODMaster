package com.jactivision.masterserver.servers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * 
 * @author Parnassian
 *
 */
public class Servers {
	private static ArrayList<Server> servers = new ArrayList<Server>();
	private static HashMap<Server, Long> heartbeatServers = new HashMap<Server, Long>(); // server, timestamp
	private static boolean debug = true;
	
	static {
		if(debug) {
		
			// here we add servers that will always be in the list, even if it does not send a heartbeat. For debugging purposes only.
			
			// play servers
			servers.add(new Server("37.59.214.143", 28960));
			servers.add(new Server("37.59.214.143", 28961));
			servers.add(new Server("37.59.214.143", 28962));
			servers.add(new Server("37.59.214.143", 28963));
			
			// wy6
			servers.add(new Server("31.7.176.76", 28960));
			servers.add(new Server("31.7.176.77", 28960));
			servers.add(new Server("31.7.176.76", 28961));
			servers.add(new Server("31.7.176.77", 28961));
			servers.add(new Server("31.7.176.76", 28962));
			servers.add(new Server("31.7.176.77", 28962));
			servers.add(new Server("31.7.176.76", 28965));
			servers.add(new Server("31.7.176.77", 28965));
			servers.add(new Server("31.7.176.78", 28960));
		
			// think
			servers.add(new Server("31.170.106.216", 28960));
			servers.add(new Server("31.170.106.216", 28961));
			servers.add(new Server("31.170.106.216", 28965));
			servers.add(new Server("31.170.106.216", 28967));
			
			// k7
			servers.add(new Server("193.107.145.77", 28960));
			
			// sn
			servers.add(new Server("74.91.125.195", 28000));
			servers.add(new Server("74.91.125.195", 28001));
			servers.add(new Server("74.91.125.195", 29090));
			servers.add(new Server("74.91.125.195", 29060));
			servers.add(new Server("74.91.125.195", 29070));
			servers.add(new Server("74.91.125.195", 28004));
			servers.add(new Server("74.91.125.195", 28002));
			
			// 2k3
			servers.add(new Server("85.17.88.229", 19000));
			servers.add(new Server("85.17.88.229", 19040));
			servers.add(new Server("85.17.88.229", 19010));
			
			// other servers
			servers.add(new Server("85.17.88.229", 19020));
			servers.add(new Server("74.91.125.195", 29010));
			servers.add(new Server("84.54.160.177", 28962));
			servers.add(new Server("84.54.160.177", 28962));
			servers.add(new Server("74.91.125.195", 29030));
			servers.add(new Server("74.91.125.195", 29040));
			servers.add(new Server("85.131.243.137", 28960));
			servers.add(new Server("176.9.154.48", 28544));
			servers.add(new Server("95.156.232.45", 24031));
			servers.add(new Server("31.7.176.78", 28965));
			servers.add(new Server("195.42.112.12", 29012));
			servers.add(new Server("95.156.232.45", 24020));
			servers.add(new Server("84.54.160.177", 28960));
			servers.add(new Server("84.54.160.177", 28961));
			servers.add(new Server("74.91.125.195", 29020));
			servers.add(new Server("74.91.125.195", 28970));
			
			
		}
	}
	
	/**
	 * Gets all online servers
	 * @return online servers
	 */
	public static Server[] get() {
		ArrayList<Server> activeServers = new ArrayList<Server>();
		activeServers.addAll(servers);
		for(Entry<Server, Long> entry : heartbeatServers.entrySet()) {
			int seconds = (int) ((System.currentTimeMillis() - entry.getValue()) / 1000);
			// more than 300 seconds means server is not alive, we'll use 320 seconds for sure
			if(seconds < 320) {
				// alive
				activeServers.add(entry.getKey());
			}
		}
		return activeServers.toArray(new Server[activeServers.size()]);
	}
	
	public static HashMap<Server, Long> heartBeatServerMap() {
		return heartbeatServers;
	}
	
	public static boolean containsServer(Server server) {
		for(Server s : heartbeatServers.keySet()) {
			if(s.toString().equals(server.toString())) {
				return true;
			}
		}
		return false;
	}
	
	public static void removeServer(Server server) {
		for(Server s : heartbeatServers.keySet()) {
			if(s.toString().equals(server.toString())) {
				heartbeatServers.remove(s);
			}
		}
	}

}
