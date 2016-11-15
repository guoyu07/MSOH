package businesslogic.login_bl;

import businesslogicservice.login_blservice.Login_BLService;
import rmi.RemoteHelper;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by pxr on 2016/11/13.
 */
public class Login implements Login_BLService {
    public ResultMessage login(String ID, String password) {
        try {
            if (RemoteHelper.getInstance().getClerkDataService().find(ID).getPassword().equals(password) ||
                    RemoteHelper.getInstance().getCustomerDataService().find(ID).getPassword().equals(password) ||
                    RemoteHelper.getInstance().getMarketerDataService().find(ID).getPassword().equals(password) ||
                    RemoteHelper.getInstance().getManagerDataService().find(ID).getPassword().equals(password)) {
                return ResultMessage.Login_Success;
            } else if (RemoteHelper.getInstance().getClerkDataService().find(ID) == null ||
                    RemoteHelper.getInstance().getCustomerDataService().find(ID).getPassword() == null ||
                    RemoteHelper.getInstance().getMarketerDataService().find(ID).getPassword() == null ||
                    RemoteHelper.getInstance().getManagerDataService().find(ID).getPassword() == null) {
                return ResultMessage.Login_NoUser;
            }else{
                return ResultMessage.Login_WrongPassword;
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return null;
    }
}
