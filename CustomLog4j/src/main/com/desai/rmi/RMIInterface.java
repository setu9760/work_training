package com.desai.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RMIInterface extends Remote{
	public Date getDate() throws RemoteException;
}
