package com.desai.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
//The Connection class -- this is where HTTP requests are serviced

class ClientConnection extends Thread {
	private static final Logger _log = Logger.getLogger(ClientConnection.class);
	
	protected Socket client;
	protected BufferedReader input;
	protected DataOutputStream out;
	String httpRootDir;

	ClientConnection(Socket client_socket, String httpRoot) {
		// set instance variables from args
		httpRootDir = httpRoot;
		client = client_socket;

		// create input and output streams for conversation with client

		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new DataOutputStream(client.getOutputStream());
		} 
		catch (IOException e) {
			_log.error(e.getMessage(), e);
			try {
				client.close();
			} catch (IOException e2) {
				//ignore
			}
			return;
		}
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {

		try {
			String requestedFile = parseFilename();
			
			// create File object
			File f = new File(httpRootDir + requestedFile);
			_log.debug("Serving file:" + f.getCanonicalPath());
			
			// check to see if file exists
			if (!f.canRead()) {
				sendResponseHeader("text/plain");
				sendString("404: not found: " + requestedFile);
				return;
			}

			sendResponse(requestedFile, f);

		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				//ignore
			}
		}

	}

	private void sendResponse(String requestedFile, File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] bytes = StreamUtils.readBytes(fis);
		out.write(bytes, 0, bytes.length);
		fis.close();

	}

	private String parseFilename() throws IOException {
		// read HTTP request -- the request comes in
		// on the first line, and is of the form:
		// GET <filename> HTTP/1.x

		String req = input.readLine(); // first line of request

		// parse request -- get filename
		StringTokenizer st = new StringTokenizer(req);
		// discard first token ("GET")
		st.nextToken();
		String requestedFile = st.nextToken();
		
		_log.debug("Parsed filename:" + requestedFile);
		return requestedFile;
	}

	// send a HTTP header to the client
	// The first line is a status message from the server to the client.
	// The second line holds the mime type of the document
	void sendResponseHeader(String type) throws IOException {
		out.writeBytes("HTTP/1.0 200 OK");
		out.writeBytes("Content-type: " + type + "\n\n");
	}

	// write a string to the client.
	void sendString(String str) throws IOException {
		out.writeBytes(str);
	}
}
