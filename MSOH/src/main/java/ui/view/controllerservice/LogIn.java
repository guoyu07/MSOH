package ui.view.controllerservice;

import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/13.
 */
public interface LogIn {
    public ResultMessage login(String ID,String password) throws RemoteException;
}
