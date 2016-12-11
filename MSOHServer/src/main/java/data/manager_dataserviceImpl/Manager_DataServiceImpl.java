package data.manager_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.managerDataHelper.ManagerDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.manager_dataservice.Manager_DataService;
import po.ManagerPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
public class Manager_DataServiceImpl implements Manager_DataService {
    private ManagerDataHelper managerDataHelper;

    private DataFactory dataFactory;

    private static Manager_DataServiceImpl manager_dataServiceImpl;


    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return managerDataService的实例
     */
    public static Manager_DataServiceImpl getInstance() {
        if (null == manager_dataServiceImpl) {
            manager_dataServiceImpl = new Manager_DataServiceImpl();
        }
        return manager_dataServiceImpl;
    }

    private Manager_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        managerDataHelper = dataFactory.getManagerDataHelper();
    }

    /**
     * 更新网站管理人员信息
     *
     * @param managerPO
     * @throws RemoteException
     */
    public boolean modifyManager(ManagerPO managerPO) throws RemoteException {
        // 数据库中主键必须存在，否则更新不成功
        if(managerPO.getID()==null){
            return false;
        }
        return managerDataHelper.modifyManager(managerPO);
    }

    /**
     * 根据ID查找网站管理人员
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ManagerPO findManagerByID(String ID) throws RemoteException {
        ManagerPO managerPO = managerDataHelper.findManagerByID(ID);

        if (null == managerPO) {
            return null;
        }

        return (ManagerPO) managerPO.clone();
    }

    /**
     * 获取所有网站管理人员信息
     *
     * @return 所有网站管理人员信息
     * @throws RemoteException
     */
    public List<ManagerPO> findAllManagers() throws IOException, ClassNotFoundException {
        List<ManagerPO> managersList = managerDataHelper.findAllManagers();
        if (null == managersList || managersList.isEmpty()) {
            return managersList;
        }

        List<ManagerPO> returnManagersList = CopyUtil.deepCopy(managersList);

        return returnManagersList;
    }

    /**
     * 根据姓名查找网站管理人员信息
     *
     * @param name
     * @return 与姓名相匹配的网站管理人员列表
     * @throws RemoteException
     */
    public List<ManagerPO> findManagerByName(String name) throws IOException, ClassNotFoundException {
        List<ManagerPO> list = managerDataHelper.findManagerByName(name);

        if (null == list || list.isEmpty()) {
            return list;
        }

        List<ManagerPO> returnManagersList = CopyUtil.deepCopy(list);

        return returnManagersList;
    }
}