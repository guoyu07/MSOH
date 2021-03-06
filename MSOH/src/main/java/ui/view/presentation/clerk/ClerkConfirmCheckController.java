package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.controller.*;
import ui.view.controllerservice.*;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.CreditChangeReason;
import util.OrderStatus;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/1.
 */
public class ClerkConfirmCheckController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkConfirmCheck.fxml";

    @FXML
    private Label cueLabel;

    //获取当前时间
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Timestamp time = Timestamp.valueOf(dateFormat.format(date));

    private String clerkID;
    private ProcessOrder processOrder;
    private OrderVO orderVO;
    private CustomerVO customerVO;
    private OrderStatus orderStatus;
    private HotelAdmin hotelAdmin;
    private UserAdmin userAdmin;
    private CreditRecord creditRecord;
    private boolean isOrderDetails;
    private EditMemberLevel editMemberLevel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }


    /**
     * 初始化方法，初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String clerkID) throws RemoteException {
        userAdmin = new UserAdminController();
        this.isOrderDetails = false;
        this.clerkID = clerkID;
        this.orderVO = orderVO;
        this.customerVO = userAdmin.findCustomerByID(orderVO.customerID);
        orderStatus = orderVO.orderType;
        if (orderStatus.equals(OrderStatus.UNEXECUTED)) {
            cueLabel.setText("确认入住？");
        } else if (orderStatus.equals(OrderStatus.EXECUTED)) {
            cueLabel.setText("确认退房？");
        } else if (orderStatus.equals(OrderStatus.ABNORMAL)) {
            cueLabel.setText("确认延迟？");
        }
    }

    /**
     * 重载初始化方法，从订单详情操作时初始界面
     *
     * @param orderVO
     */
    public void initial(OrderVO orderVO, String clerkID, boolean isOrderDetails) throws RemoteException {
        this.initial(orderVO, clerkID);
        this.isOrderDetails = isOrderDetails;
    }

    /**
     * 确认按钮结果，确认入住或退房或延迟入住，记录入住或退房或延迟入住的时间
     */
    @FXML
    private void confirmCheck() throws RemoteException {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));
        processOrder = new ProcessOrderController();
        hotelAdmin = new HotelAdminController();
        creditRecord = new CreditRecordController();
        editMemberLevel = new EditMemberLevelController();

        if (orderStatus.equals(OrderStatus.UNEXECUTED)) {
            orderVO.actualCheckinTime = time;
            orderVO.orderType = OrderStatus.EXECUTED;
            //办理入住
            ResultMessage resultMessage = processOrder.executeOrder(orderVO);
            if (resultMessage.equals(ResultMessage.Order_ExecuteOrderSuccess)) {

                if (hotelAdmin.changeOccupiedRoom(orderVO, 1).equals(ResultMessage.Hotel_changeOccupiedRoomSuccess)
                        && hotelAdmin.changeReservedRoom(orderVO, -1).equals(ResultMessage.Hotel_changeReservedRoomSuccess)) {
                    CreditRecordVO creditRecordVO = new CreditRecordVO((int) orderVO.finalPrice, time, orderVO.customerName, orderVO.customerID,
                            (int) (customerVO.credit + orderVO.finalPrice), orderVO.orderID, "", CreditChangeReason.Order_Executed);
                    if (creditRecord.addCreditRecord(customerVO.ID, creditRecordVO).equals(ResultMessage.Customer_AddCreditRecordSuccess)) {
                        stageController = this.returnMessage("办理入住成功！");
                        editMemberLevel.changeGrade(orderVO.customerID);
                        renew();
                    } else {
                        this.returnMessage("更改信用值错误！");
                    }
                } else {
                    this.returnMessage("房间数量错误！");
                }
            } else {
                this.returnMessage("未知错误！");
            }
        } else if (orderStatus.equals(OrderStatus.EXECUTED)) {
            orderVO.actualCheckoutTime = time;
            orderVO.orderType = OrderStatus.FINISHED_UNEVALUATED;
            //办理退房
            ResultMessage resultMessage = processOrder.endOrder(orderVO);
            if (resultMessage.equals(ResultMessage.Order_EndOrderSuccess)) {

                if (hotelAdmin.changeOccupiedRoom(orderVO, -1).equals(ResultMessage.Hotel_changeOccupiedRoomSuccess) &&
                        hotelAdmin.changeAvailableRoom(orderVO, 1).equals(ResultMessage.Hotel_changeAvailableRoomSuccess)) {
                    stageController = this.returnMessage("退房成功！");
                    renew();
                } else {
                    this.returnMessage("房间数量错误！");
                }
            } else {
                this.returnMessage("未知错误！");
            }
        } else if (orderStatus.equals(OrderStatus.ABNORMAL)) {

            orderVO.orderType = OrderStatus.EXECUTED;
            orderVO.actualCheckinTime = time;

            //延迟入住
            ResultMessage resultMessage = processOrder.executeOrder(orderVO);
            if (resultMessage.equals(ResultMessage.Order_ExecuteOrderSuccess)) {
                if (hotelAdmin.changeOccupiedRoom(orderVO, 1).equals(ResultMessage.Hotel_changeOccupiedRoomSuccess) &&
                        hotelAdmin.changeAvailableRoom(orderVO, -1).equals(ResultMessage.Hotel_changeAvailableRoomSuccess) &&
                        hotelAdmin.changeReservedRoom(orderVO, 1).equals(ResultMessage.Hotel_changeReservedRoomSuccess)) {

                    CreditRecordVO creditRecordVO = new CreditRecordVO((int) orderVO.finalPrice, time, orderVO.customerName, orderVO.customerID,
                            (int) (customerVO.credit + orderVO.finalPrice), orderVO.orderID, "", CreditChangeReason.Order_Delay);
                    if (creditRecord.addCreditRecord(customerVO.ID, creditRecordVO).equals(ResultMessage.Customer_AddCreditRecordSuccess)) {
                        stageController = this.returnMessage("延迟入住成功！");
                        editMemberLevel.changeGrade(orderVO.customerID);
                        renew();
                    } else {
                        this.returnMessage("更改信用值错误！");
                    }
                } else {
                    this.returnMessage("房间数量错误！");
                }
            } else {
                this.returnMessage("未知错误！");
            }
        }
    }

    /**
     * 取消按钮结果，取消操作，关闭弹窗
     */
    @FXML
    private void cancelCheck() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 返回提示信息
     *
     * @param error
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 办理成功后刷新订单列表页面
     */
    private void renew() throws RemoteException {
        stageController = new StageController();
        stageController.closeStage(resource);
        if (isOrderDetails)
            //如果从订单详情页面操作，成功后要关闭订单详情页面
            stageController.closeStage("clerk/ClerkOrderDetails.fxml");
        ClerkCheckOrderListController clerkCheckOrderListController =
                (ClerkCheckOrderListController) stageController.getController("clerk/ClerkCheckOrderList.fxml");
        clerkCheckOrderListController.initial(clerkID);
    }

}
