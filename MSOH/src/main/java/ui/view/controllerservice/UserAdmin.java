package ui.view.controllerservice;

import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/12.
 */
public interface UserAdmin {

    public List<ClerkVO> findClerkByName(String name) throws RemoteException;

    public ClerkVO findClerkByID(String ID) throws RemoteException;

    public List<MarketerVO> findMarketerByName(String name) throws RemoteException ;

    public MarketerVO findMarketerByID(String ID) throws RemoteException;

    public List<CustomerVO> findAllCustomer() throws RemoteException;

    public List<CustomerVO> findCustomerByName(String name) throws RemoteException;

    public CustomerVO findCustomerByID(String ID) throws RemoteException;

    public ManagerVO findManagerByID(String ID) throws RemoteException ;

    public ResultMessage addClerk(ClerkVO vo)throws RemoteException;

    public ResultMessage addMarketer(MarketerVO vo)throws RemoteException;

    public MemberVO findMemberByID(String ID) throws RemoteException;
}
