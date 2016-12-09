package util.filter;

import businesslogic.hotel_bl.HotelUtil;
import util.CalculateDays;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/9.
 */
public class FilterCriteriaDate implements FilterCriteria {
    Timestamp firstDate;
    Timestamp secondDate;

    public FilterCriteriaDate(Timestamp timestamp1, Timestamp timestamp2) {
        this.firstDate = timestamp1;
        this.secondDate = timestamp2;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) throws RemoteException {
        if (firstDate == null && secondDate == null)
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        HotelUtil hotelUtil = new HotelUtil();
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (firstDate.getTime() - secondDate.getTime()) / oneDay + 1;//算共住多少天
        List<Timestamp> timestamps = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            timestamps.add(new Timestamp(firstDate.getTime() + i * oneDay));
        }
        boolean hasRoom = false;
        for (HotelVO hotelVO : list) {
            for (Timestamp timestamp : timestamps) {
                List<RoomVO> roomVOList = hotelUtil.getDailyRoomInfo(hotelVO.hotelID, timestamp).room;
                for (RoomVO roomVO : roomVOList) {
                    if (roomVO.leftRooms >= 0)
                        hasRoom = true;
                }
            }
            if (hasRoom) {
                hasRoom = false;
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
