package com.messina.mlab.wall;

public class ClientConfigData {
	private String ipaddress;
	private String id;
	private String localLocation;
	private String localScreenSize;

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setLocalLocation(String localLocation) {
		this.localLocation = localLocation;
	}

	public String getLocalLocation() {
		return localLocation;
	}

	public void setLocalScreenSize(String localScreenSize) {
		this.localScreenSize = localScreenSize;
	}

	public String getLocalScreenSize() {
		return localScreenSize;
	}

}
