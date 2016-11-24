package po;

import util.OrderStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zqh
 */
public class OrderPO implements Serializable {
    // 客户姓名
    private String customerName;
    // 客户联系方式
    private String phone;
    // 客户ID
    private String customerID;
    // 酒店ID
    private String hotelID;
    // 酒店名称
    private String hotelName;
    // 订单号
    private String orderID;
    // 预计check in时间
    private Timestamp estimatedCheckinTime;
    // 实际check in时间
    private Timestamp actualCheckinTime;
    // 预计check out时间
    private Timestamp estimatedCheckoutTime;
    // 实际check out时间
    private Timestamp actualCheckoutTime;
    // 最晚执行时间
    private Timestamp latestExecutedTime;
    // 所订客房
    private List<String> rooms;
    // 入住人数
    private int numOfCustomers;
    // 是否有儿童
    private boolean haveChildren;
    // 备注（特殊要求，如：“需要三双拖鞋”
    private String remarks;
    // 所用策略名称
    private String promotionName;
    // 最初价格
    private double initialPrice;
    // 折后价格
    private double finalPrice;
    // 订单状态
    private OrderStatus orderType;

    public OrderPO() {

    }

    public OrderPO(String customerName, String phone, String customerID, String hotelID, String hotelName, String orderID,
                   Timestamp estimatedCheckinTime, Timestamp actualCheckinTime, Timestamp estimatedCheckoutTime, Timestamp actualCheckoutTime,
                   Timestamp latestExecutedTime, List<String> rooms, int numOfCustomers, boolean haveChildren, String remarks,
                   String promotionName, double initialPrice, double finalPrice, OrderStatus orderType) {
        this.customerName = customerName;
        this.phone = phone;
        this.customerID = customerID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.orderID = orderID;
        this.estimatedCheckinTime = estimatedCheckinTime;
        this.actualCheckinTime = actualCheckinTime;
        this.estimatedCheckoutTime = estimatedCheckoutTime;
        this.actualCheckoutTime = actualCheckoutTime;
        this.latestExecutedTime = latestExecutedTime;
        this.rooms = rooms;
        this.numOfCustomers = numOfCustomers;
        this.haveChildren = haveChildren;
        this.remarks = remarks;
        this.promotionName = promotionName;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.orderType = orderType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Timestamp getEstimatedCheckinTime() {
        return estimatedCheckinTime;
    }

    public void setEstimatedCheckinTime(Timestamp estimatedCheckinTime) {
        this.estimatedCheckinTime = estimatedCheckinTime;
    }

    public Timestamp getActualCheckinTime() {
        return actualCheckinTime;
    }

    public void setActualCheckinTime(Timestamp actualCheckinTime) {
        this.actualCheckinTime = actualCheckinTime;
    }

    public Timestamp getEstimatedCheckoutTime() {
        return estimatedCheckoutTime;
    }

    public void setEstimatedCheckoutTime(Timestamp estimatedCheckoutTime) {
        this.estimatedCheckoutTime = estimatedCheckoutTime;
    }

    public Timestamp getActualCheckoutTime() {
        return actualCheckoutTime;
    }

    public void setActualCheckoutTime(Timestamp actualCheckoutTime) {
        this.actualCheckoutTime = actualCheckoutTime;
    }

    public Timestamp getLatestExecutedTime() {
        return latestExecutedTime;
    }

    public void setLatestExecutedTime(Timestamp latestExecutedTime) {
        this.latestExecutedTime = latestExecutedTime;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public void setNumOfCustomers(int numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public OrderStatus getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderStatus orderType) {
        this.orderType = orderType;
    }

}