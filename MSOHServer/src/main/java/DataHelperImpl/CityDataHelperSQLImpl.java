package DataHelperImpl;

import DataHelper.CityDataHelper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.CityArea;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/12/3.
 */
public class CityDataHelperSQLImpl implements CityDataHelper {
    /**
     * 获取所有商圈
     *
     * @return
     */
    public List<CityArea> getAllAreas() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<CityArea> cityAreaList = session.createQuery("from CityArea ").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return cityAreaList;
    }

    /**
     * 根据城市获取商圈
     *
     * @param city
     * @return
     */
    public List<CityArea> getAreaByCity(String city) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from CityArea as area where area.city=:n";
        Query query = session.createQuery(hql);
        query.setString("n", city);

        List<CityArea> cityAreaList = query.list();

        return cityAreaList;
    }
}
