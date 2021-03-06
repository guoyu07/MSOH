package ui.controller;

import businesslogic.order_bl.Order;
import businesslogic.order_bl.OrderUtil;
import businesslogicservice.order_blservice.OrderUtil_BLService;
import businesslogicservice.order_blservice.Order_BLService;
import ui.view.controllerservice.ProcessOrder;
import util.OrderStatus;
import util.ResultMessage;
import vo.OrderPriceVO;
import vo.OrderVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class ProcessOrderController implements ProcessOrder {
    private OrderUtil_BLService orderUtil_blService;
    private Order_BLService order_blService;

    public ProcessOrderController() {
        orderUtil_blService = new OrderUtil();
        order_blService = new Order();
    }

    /**
     * 新建订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.createOrder(orderVO);
    }

    /**
     * 线下创建订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage createOrderOffline(OrderVO orderVO) throws RemoteException {
        return order_blService.createOrderOffline(orderVO);
    }

    /**
     * 撤销订单
     *
     * @param orderVO
     * @return
     */
    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.cancelOrder(orderVO);
    }


    /**
     * 执行订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage executeOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.executeOrder(orderVO);
    }

    /**
     * 结束订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage endOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.endOrder(orderVO);
    }

    /**
     * 恢复异常订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.renewOrder(orderVO);
    }

    /**
     * 根据订单状态得到订单
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByStatus(OrderStatus status) throws RemoteException {
        return orderUtil_blService.getOrderByStatus(status);
    }

    public List<OrderVO> getOrderByCustomerID(String customerID) throws RemoteException {
        return orderUtil_blService.getOrdersByCustomerID(customerID);
    }


    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException {
        return orderUtil_blService.getOrderByIDAndStatus(customerID, orderStatus);
    }

    /**
     * 根据酒店ID得到该酒店不同状态的订单
     *
     * @param hotelID
     * @param status
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByHotelAndStatus(String hotelID, OrderStatus status) throws RemoteException {
        return orderUtil_blService.getOrderByHotelAndStatus(hotelID, status);
    }

    /**
     * 将订单按照时间排序
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException {
        return orderUtil_blService.sortByTime(list);
    }

    /**
     * 根据订单号得到一个订单
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public OrderVO getSingle(String ID) throws RemoteException {
        return orderUtil_blService.getSingle(ID);
    }

    /**
     * 获得该用户在酒店对应状态的订单
     *
     * @param ID
     * @param hotelID
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndHotelIDAndStatus(String ID, String hotelID, OrderStatus orderStatus) throws RemoteException {
        return orderUtil_blService.getOrderByIDAndHotelIDAndStatus(ID, hotelID, orderStatus);
    }

    /**
     * 获得用户在该酒店的订单
     *
     * @param ID
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndHotelID(String ID, String hotelID) throws RemoteException {
        return orderUtil_blService.getOrderByIDAndHotelID(ID, hotelID);
    }

    /**
     * 获得某一天某一种状态的订单
     *
     * @param timestamp
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByStatusAndDate(Timestamp timestamp, OrderStatus orderStatus) throws RemoteException {
        return orderUtil_blService.getOrderByStatusAndDate(timestamp, orderStatus);
    }


    /**
     * 适用促销策略
     * @param orderVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<OrderPriceVO> usePromotion(OrderVO orderVO) throws IOException, ClassNotFoundException {
        return  order_blService.usePromotion(orderVO);
    }

    /**
     * 获得订单最低价格
     * @param orderPriceVOs
     * @return
     * @throws RemoteException
     */
    public OrderPriceVO getLowestPrice(List<OrderPriceVO> orderPriceVOs) throws RemoteException {
        return order_blService.getLowestPrice(orderPriceVOs);
    }
}
