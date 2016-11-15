package dataservice.manager_dataservice;

import po.ManagerPO;
import po.MarketerPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author zqh
 */
public interface Manager_DataService extends Remote {
    public ResultMessage modify(ManagerPO po) throws RemoteException;

    public MarketerPO find(String ID) throws RemoteException;
}
