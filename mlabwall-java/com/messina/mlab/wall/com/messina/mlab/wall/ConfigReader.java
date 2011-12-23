package com.messina.mlab.wall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

import com.google.gson.Gson;

public class ConfigReader {
	private final String filename;
	private ConfigData data;

	public ConfigReader(String _filename) {
		filename = _filename;
		try {
			readConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error while initializing json config: " + filename);
			e.printStackTrace();
		}
	}

	public void setData(ConfigData data) {
		this.data = data;
	}

	public ConfigData getData() {
		return this.data;
	}

	// read the config file into the config data structure
	private void readConfig() throws Exception {
		String json;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error while opening json config: " + filename);
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}

			json = sb.toString();
			data = new Gson().fromJson(json, ConfigData.class);
			if (data == null || data.getServer() == "" || data.getPort() == "" || data.getMasterDimensions() == ""
					|| data.getClients() == null || data.getClients().size() == 0) {
				System.out.println("Empty config: " + filename);
				throw new Exception("The configuration data is invalid or missing some data.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error while reading json config: " + filename);
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error while closing json config.");
				e.printStackTrace();
			}
		}
	}

	// Allows a client to create an .ini file to run it's session
	/*
	 * server=192.168.8.2; port=9002; masterDimensions=2048,768; id=1;
	 * localLocation=1024,0; localScreenSize=1024,768;
	 */
	public String createTempClientConfig(String ipAddress, String id, String Path) throws Exception {

		System.out.println("Creating temp ini file with:");
		System.out.println("\tipAddress: " + ipAddress);
		System.out.println("\tDisplay: " + id);
		System.out.println("\tPath: " + Path);

		StringBuilder ini = new StringBuilder();
		if (ipAddress.equals("") || id.equals("")) {
			throw new Exception("IP Address or Display cannot be blank");
		}
		ini.append("server=" + data.getServer() + ";\n");
		ini.append("port=" + data.getPort() + ";\n");
		ini.append("masterDimensions=" + data.getMasterDimensions() + ";\n");

		// find the client data and merge to the master data
		ClientConfigData ccd = null;
		ListIterator<ClientConfigData> i = data.getClients().listIterator();
		while (i.hasNext()) {
			int idx = i.nextIndex();
			ccd = i.next();
			if (ccd.getIpaddress().equals(ipAddress) && ccd.getId().equalsIgnoreCase(id)) {
				ini.append("id=" + ccd.getId() + ";\n");
				ini.append("localLocation=" + ccd.getLocalLocation() + ";\n");
				ini.append("localScreenSize=" + ccd.getLocalScreenSize() + ";\n");
				break;
			}
		}
		if (ccd == null) {
			throw new Exception("The specified ipAddress[" + ipAddress + "] and id [" + id
					+ "] is not listed in the configuration file");
		}

		// Write the ini file out
		String fullPath;
		if (!Path.endsWith("\"") && !Path.endsWith("/")) {
			fullPath = Path + "/" + ipAddress + "-" + id + ".ini";
		} else {
			fullPath = Path + ipAddress + "-" + id + ".ini";
		}
		PrintWriter out = new PrintWriter(new FileWriter(fullPath));
		out.append(ini);
		out.close();

		System.out.println("Created client config: " + fullPath);
		System.out.println(ini.toString());

		return fullPath;
	}
}
