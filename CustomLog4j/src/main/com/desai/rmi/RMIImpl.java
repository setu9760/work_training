package com.desai.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class RMIImpl extends UnicastRemoteObject implements RMIInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159733179432606962L;

	public RMIImpl(String name) throws RemoteException {
		super();
		try {
			Naming.rebind(name, this);
		} catch (Exception e) {
			System.out.print("Exception: ");
			e.printStackTrace();
		}
	}

	@Override
	public Date getDate() throws RemoteException {
		return new Date();
	}

}
