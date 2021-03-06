package po;

import util.MemberType;
import util.PromotionType;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
public class PromotionPO implements Serializable{
    private static final long serialVersionUID = 2L;
    // 策略制定者名称
    private String framerName;
    // 策略制定日期
    private Timestamp frameDate;
    // 策略名称
    private String promotionName;
    // 目标客户
    private MemberType targetUser;
    // 目标商圈
    private String targetArea;
    // 目标酒店ID
    private String targetHotel;
    // 策略生效时间
    private Timestamp startTime;
    // 策略过期时间
    private Timestamp endTime;
    // 策略折扣
    private double discount;
    // 策略生效所需最少房间数
    private int minRoom;
    // 策略ID（编号）
    private int promotionID;
    // 企业名称
    private String companyName;
    // 策略类型
    private PromotionType promotionType;
    // 适用的会员等级
    private int targetMemberLevel;

    public PromotionPO() {
    }

    public PromotionPO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
                       String targetArea, String targetHotel, Timestamp startTime, Timestamp endTime, double discount, int minRoom, String companyName, PromotionType promotionType) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.promotionName = promotionName;
        this.targetUser = targetUser;
        this.targetArea = targetArea;
        this.targetHotel = targetHotel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.minRoom = minRoom;
        this.companyName = companyName;
        this.promotionType = promotionType;
    }

    public String getFramerName() {
        return framerName;
    }

    public void setFramerName(String framerName) {
        this.framerName = framerName;
    }

    public Timestamp getFrameDate() {
        return frameDate;
    }

    public void setFrameDate(Timestamp frameDate) {
        this.frameDate = frameDate;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public MemberType getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(MemberType targetUser) {
        this.targetUser = targetUser;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public String getTargetHotel() {
        return targetHotel;
    }

    public void setTargetHotel(String targetHotel) {
        this.targetHotel = targetHotel;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getMinRoom() {
        return minRoom;
    }

    public void setMinRoom(int minRoom) {
        this.minRoom = minRoom;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public int getTargetMemberLevel() {
        return targetMemberLevel;
    }

    public void setTargetMemberLevel(int targetMemberLevel) {
        this.targetMemberLevel = targetMemberLevel;
    }

}
