package com.desai.java;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.log4j.Logger;

public class WebServer extends Thread {

private static final Logger _log = Logger.getLogger(WebServer.class);
	
	private ServerSocket listen_socket;
	private String httpRootDir;

	/**
	 * Constructor
	 * 
	 * @param servPort
	 * @param httpRoot
	 */
	public WebServer(int servPort, String httpRoot) {
		try {
			// set instance variables from constructor args
			httpRootDir = httpRoot;

			// create new ServerSocket
			listen_socket = new ServerSocket(servPort);

		} catch (IOException e) {
			System.err.println(e);
		}

		// Start running Server thread
		this.start();
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		_log.debug("listening on port " + listen_socket.getLocalPort() + " for requests to dir " + httpRootDir);
		try {
			while (true) {
				// listen for a request. When a request comes in, accept it, then create a Connection object to
				// service the request and go back to listening on the port.
				Socket client_socket = listen_socket.accept();
				_log.debug("connection request received");
				ClientConnection c = new ClientConnection(client_socket, httpRootDir);
				c.start();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * @param argv
	 */
	public static void main(String[] argv) {
		if (argv.length < 2) {
			System.out.println("usage: java WebServer <port> <http root directory>");
			System.exit(0);
			return;
		}
		new WebServer(Integer.valueOf(argv[0]).intValue(), argv[1]);
	}
}

