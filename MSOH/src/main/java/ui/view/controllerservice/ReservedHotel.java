package ui.view.controllerservice;

import vo.CustomerVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public interface ReservedHotel {
    public List<HotelVO> getHistoryHotel(String customerID) throws RemoteException;
}
