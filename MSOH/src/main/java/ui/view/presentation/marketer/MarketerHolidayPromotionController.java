package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import vo.ClerkVO;
import vo.HotelVO;
import vo.MarketerVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/14.
 */
public class MarketerHolidayPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TextField promotionNameTextField;
    @FXML
    private Label discountLabel;
    @FXML
    private Button startTimeButton;
    @FXML
    private Button endTimeButton;
    @FXML
    private Button confirmButton;

    private String marketerID;
    private String marketerName;
    private MarketerVO marketerVO;
    private UserAdmin userAdmin;
    private EditPromotion editPromotion;
    private DecimalFormat df = new DecimalFormat("0.0");

    //获取当前日期
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String initialTime = sdf.format(date);

    private String resource = "marketer/MarketerHolidayPromotion.fxml";


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String marketerID) throws RemoteException {
        confirmButton.setText("制定");
        userAdmin = new UserAdminController();
        this.marketerID = marketerID;
        this.marketerVO = userAdmin.findMarketerByID(marketerID);
        this.marketerName = marketerVO.name;
        startTimeButton.setText(initialTime);
        endTimeButton.setText(initialTime);
    }

    /**
     * 重载initial方法，用于修改策略时初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        userAdmin = new UserAdminController();
        this.initial(userAdmin.findMarketerByName(promotionVO.framerName).get(0).ID);
        confirmButton.setText("修改");
        discountLabel.setText(String.valueOf(promotionVO.discount));
        startTimeButton.setText(String.valueOf(promotionVO.startTime).substring(0, 10));
        endTimeButton.setText(String.valueOf(promotionVO.endTime).substring(0, 10));
    }

    /**
     * 加号按钮结果，增加折扣数字
     */
    @FXML
    private void addDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 9.9) {
            discountLabel.setText(df.format((discount + 0.1)));
        }
    }

    /**
     * 减号按钮结果，减少折扣数字
     */
    @FXML
    private void minusDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 0.1) {
            discountLabel.setText(df.format((discount - 0.1)));
        }
    }

    /**
     * 开始时间按钮结果，显示选择开始时间弹窗
     */
    @FXML
    private void showBeginTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "start");
        selectTimeViewController.init();
    }

    /**
     * 结束时间按钮结果，显示选择结束时间弹窗
     */
    @FXML
    private void showEndTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "end");
        selectTimeViewController.init();
    }

    /**
     * 确定按钮结果，创建订单
     */
    @FXML
    private void confirmCreate() throws IOException, ClassNotFoundException {
        stageController = new StageController();

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        editPromotion = new EditPromotionController();

        String name = promotionNameTextField.getText();
        Timestamp startTime = Timestamp.valueOf(startTimeButton.getText() + " 00:00:00");
        Timestamp endTime = Timestamp.valueOf(endTimeButton.getText() + " 00:00:00");
        double discount = Double.parseDouble(discountLabel.getText());

        if (confirmButton.getText().equals("制定")) {
            ResultMessage resultMessage = editPromotion.addWebPromotion(new PromotionVO(marketerName, time, name, MemberType.NONMEMBER
                    , "所有商圈", startTime, endTime, discount, 0, PromotionType.WebPromotion_Holiday));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_AddPromotionSuccess)) {
                stageController = this.returnMessage("创建成功！");
                stageController.closeStage("marketer/MarketerCreateWebPromotion.fxml");
                renew();
            } else {
                this.returnMessage("未知错误！");
            }
        } else if (confirmButton.getText().equals("修改")) {
            ResultMessage resultMessage = editPromotion.modifyWebPromotion(new PromotionVO(marketerName, time, name, MemberType.NONMEMBER
                    , "所有商圈", startTime, endTime, discount, 0, PromotionType.WebPromotion_Holiday));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_ModifyPromotionSuccess)) {
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage("marketer/MarketerModifyPromotion.fxml");
                renew();
            } else {
                this.returnMessage("未知错误！");
            }
        }

    }

    /**
     * 显示提示信息(四个promotion复制的)
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
     * 回显选择的开始时间
     *
     * @param time
     */
    public void setStartTime(String time) {
        startTimeButton.setText(time);
    }

    /**
     * 回显选择的结束时间
     *
     * @param time
     */
    public void setEndTime(String time) {
        endTimeButton.setText(time);
    }

    /**
     * 创建或修改促销策略后，刷新列表
     */
    private void renew() throws IOException, ClassNotFoundException {
        stageController = new StageController();
        MarketerWebPromotionController marketerWebPromotionController = (MarketerWebPromotionController) stageController.getController("marketer/MarketerWebPromotion.fxml");
        marketerWebPromotionController.initial(marketerID);
    }

}
