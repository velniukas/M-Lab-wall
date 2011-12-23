package com.messina.mlab.wall;

import java.net.InetAddress;
import java.net.UnknownHostException;

import mpe.client.TCPClient;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import processing.core.PApplet;

public class mlabwall extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1923192449867487359L;

	ConfigReader config;
	public TCPClient cl;
	String ip;
	String id;
	String configname;
	Logo logo;

	@Override
	public void setup() {
		// get the unique display argument - known to exist from the main()
		// below
		id = "0";
		try {
			id = this.getParameter("id");
			if (id == null) {
				id = "0";
			}
		} catch (Exception e) {
			System.err.println("Missing parameter: id ");
			// e.printStackTrace();
		}
		println("args:" + this.args.length);
		for (int i = 0; i < this.args.length; i++) {
			println("arg " + i + ": " + args[i]);
		}
		System.out.println("Selecting display settings for id [" + id + "]");

		System.out.println("Current directory: " + System.getProperty("user.dir"));

		// read in the json config file
		config = new ConfigReader("./config/setup.json");
		// get the ip address
		try {
			InetAddress local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			println(ip);
		} catch (UnknownHostException e) {
			System.err.println("Unknown Host Exception.");
			e.printStackTrace();
			exit();
		}

		// HACK: create a temporary ini file (until we change the server to
		// speak json)
		try {
			configname = config.createTempClientConfig(ip, id, "./config/tmp/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error while creating temp client config.");
			e.printStackTrace();
			exit();
		}

		println(configname);

		// make a new Client using an INI file
		// sketchPath() is used so that the INI file is local to the sketch
		cl = new TCPClient(sketchPath(configname), this);

		// the size is determined by the client's local width and height
		// size(cl.getLWidth(), cl.getLHeight(), OPENGL);
		// hint(ENABLE_OPENGL_4X_SMOOTH);
		size(cl.getLWidth(), cl.getLHeight());

		background(255);

		smooth();
		noStroke();

		logo = new Logo(this, cl.getMWidth() / 2 + 180, cl.getMHeight() / 2);

		// Important, must start the client!
		cl.start();
	}

	// --------------------------------------
	// Triggered by the client whenever a new frame should be rendered.
	// All synchronized drawing should be done here when in auto mode.
	public void frameEvent(TCPClient c) {
		background(255);
		renderDisplayNumber();
		renderText();
		logo.calc();
		logo.display();
	}

	@Override
	public void draw() {
	}

	void renderDisplayNumber() {
		pushMatrix();
		translate(cl.getLWidth() / 2, cl.getLHeight() / 2);
		fill(150, 150, 150, 150);
		textSize(200);
		text(id, 0, 0);
		popMatrix();
	}

	void renderText() {
		pushMatrix();
		translate(0, cl.getMHeight() / 2);
		fill(0, 0, 0);
		textSize(144);
		text("M-Lab", 15, 50);
		popMatrix();
	}

	public static void main(String args[]) {
		// Set the appropriate variables based on supplied options
		// boolean verbose = false;
		String id = "0";
		try {
			// Create a Parser
			CommandLineParser parser = new BasicParser();
			Options options = new Options();
			@SuppressWarnings("static-access")
			Option displayOpt = OptionBuilder.hasArg(true)
					.withDescription("set the unique display id number; default 0").create("id");
			options.addOption(displayOpt);
			// options.addOption("v", "verbose", false,
			// "Print out VERBOSE information" );
			// options.addOption("h", "help",
			// false,"Prints this usage information");

			// Parse the program arguments
			CommandLine commandLine = null;
			try {
				commandLine = parser.parse(options, args);

				// if( commandLine.hasOption('h') ) {
				// System.out.println( "Help Message");
				// System.exit(0);
				// }
				// if( commandLine.hasOption('v') ) {
				// verbose = true;
				// }

				// Debugging arguments
				Option[] opts = commandLine.getOptions();
				System.out.println("Command Line Arguments..." + commandLine.getOptions().length);
				for (int i = 0; i < commandLine.getOptions().length; i++) {
					System.out.println(opts[i].getOpt() + "=" + opts[i].getValue());
				}
				System.out.println("___________________________________");

				if (commandLine.hasOption("id")) {
					id = commandLine.getOptionValue("id");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println("Error while parsing cmd line parameters.");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		} finally {
			PApplet.main(new String[] { "--present", "--id=" + id, "com.messina.mlab.wall.mlabwall" });
		}

	}
}