package com.desai.rmi.server;

import java.rmi.RMISecurityManager;

import com.desai.rmi.RMIImpl;

public class RMIServer {
	public static void main(String[] argv) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			RMIImpl implementation = new RMIImpl("myRMIImplInstance");
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e);
		}
	}
}
