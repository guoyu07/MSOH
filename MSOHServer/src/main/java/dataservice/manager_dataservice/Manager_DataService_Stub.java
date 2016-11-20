package dataservice.manager_dataservice;

import po.ManagerPO;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 * 
 * @author 钱柯宇
 *
 */
public class Manager_DataService_Stub implements Manager_DataService{
	public ResultMessage modify (ManagerPO managerPO){
		return null;
	}

	@Override
	public boolean modifyManager(ManagerPO po) throws RemoteException {
		return false;
	}

	@Override
	public ManagerPO findManager(String ID) throws RemoteException {
		if(ID=="320200000000000000")
			return new ManagerPO("pxr", ID, "12345678910", "123456",  new ImageIcon().getImage(), WorkerPosition.Manager);
		else
			return null;
	}
}
