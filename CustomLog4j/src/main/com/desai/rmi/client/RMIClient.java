package com.desai.rmi.client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Date;

import com.desai.rmi.RMIInterface;

public class RMIClient {
	public static void main(String[] argv) {
		{
			System.setSecurityManager(new RMISecurityManager());
			if (argv.length != 1) {
				System.out
						.println("usage: java myRMIClient <IP address of host running RMI server>");
				System.exit(0);
			}
			String serverName = argv[0];
			try {
				// bind server object to object in client
				RMIInterface myServerObject = (RMIInterface) Naming
						.lookup("rmi://" + serverName + "/myRMIImplInstance");

				// invoke method on server object
				Date d = myServerObject.getDate();
				System.out.println("Date on server is " + d);
			} catch (Exception e) {
				System.out.println("Exception occured: " + e);
				System.exit(0);
			}
			System.out.println("RMI connection successful");
		}
	}
}
