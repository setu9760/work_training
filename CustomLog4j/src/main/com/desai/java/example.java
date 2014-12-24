package com.desai.java;

import java.io.*;
import java.net.*;
import java.util.*;

public class example extends Thread {

	ServerSocket listen_socket;
	String httpRootDir;

	public example(String port, String httpRoot) {
		try {
			// set instance variables from constructor args
			int servPort = Integer.parseInt(port);
			httpRootDir = httpRoot;

			// create new ServerSocket
			listen_socket = new ServerSocket(servPort);

		} catch (IOException e) {
			System.err.println(e);
		}

		// Start running Server thread
		this.start();
	}

	public void run() {
		try {
			while (true) {
				// listen for a request. When a request comes in,
				// accept it, then create a Connection object to
				// service the request and go back to listening on
				// the port.

				Socket client_socket = listen_socket.accept();
				System.out.println("connection request received");
				Connection c = new Connection(client_socket, httpRootDir);
				c.start();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

