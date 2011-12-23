package com.messina.mlab.wall;

import java.util.List;

/*
 {
 "server": "192.168.8.2",
 "port": "9002",
 "masterDimensions": "2048,1536",
 "clients": [
 {
 "ipaddress", "192.168.2.2"
 "localLocation": "0,0",
 "localScreenSize": "1024,768"
 },
 {
 "ipaddress", "192.168.2.2"
 "localLocation": "1024,0",
 "localScreenSize": "1024,768"
 },
 {
 "ipaddress", "192.168.2.4"
 "localLocation": "0,768",
 "localScreenSize": "1024,768"
 },
 {
 "ipaddress", "192.168.2.4"
 "localLocation": "1024,768",
 "localScreenSize": "1024,768"
 }
 ]
 }

 */

public class ConfigData {
	private String server;
	private String port;
	private String masterDimensions;
	private List<ClientConfigData> clients;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPort() {
		return port;
	}

	public void setMasterDimensions(String masterDimensions) {
		this.masterDimensions = masterDimensions;
	}

	public String getMasterDimensions() {
		return masterDimensions;
	}

	public void setClients(List<ClientConfigData> clients) {
		this.clients = clients;
	}

	public List<ClientConfigData> getClients() {
		return clients;
	}

}