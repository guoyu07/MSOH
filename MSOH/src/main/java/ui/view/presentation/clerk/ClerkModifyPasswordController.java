package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.controller.ClerkInfoChangeController;
import ui.view.controllerservice.ClerkInfoChange;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/11/28.
 */
public class ClerkModifyPasswordController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkModifyPassword.fxml";

    @FXML
    private TextField oldPWTextField;
    @FXML
    private TextField newPWTextField1;
    @FXML
    private TextField newPWTextField2;

    private String clerkID;
    private ClerkInfoChange clerkInfoChange;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 初始化方法，设置工作人员ID
     *
     * @param clerkID
     */
    public void initial(String clerkID) {
        this.clerkID = clerkID;
    }

    /**
     * 后退按钮结果，显示提示确认退出弹窗
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.loadStage("util/ConfirmExit.fxml", 1);
        ConfirmExitController controller = (ConfirmExitController) stageController.getController();
        controller.setToBeClosed(resource);
    }

    /**
     * 确认修改按钮结果，修改密码
     */
    @FXML
    private void confirmModify() throws RemoteException {
        clerkInfoChange = new ClerkInfoChangeController();
        ResultMessage resultMessage = clerkInfoChange.changePassword(clerkID, oldPWTextField.getText(), newPWTextField1.getText(), newPWTextField2.getText());
        if (resultMessage.equals(ResultMessage.Blank)) {
            this.returnMessage("信息填写不完整！");
        } else if (resultMessage.equals(ResultMessage.DataFormatWrong)) {
            this.returnMessage("密码格式错误！");
        } else if (resultMessage.equals(ResultMessage.ChangePassword2DifferentNew)) {
            this.returnMessage("新密码不一致！");
        } else if (resultMessage.equals(ResultMessage.ChangePasswordWrongOldPw)) {
            this.returnMessage("旧密码错误！");
        } else if (resultMessage.equals(ResultMessage.ChangePasswordSuccess)) {
            stageController = this.returnMessage("修改成功！");
            stageController.closeStage(resource);
        }
    }

    /**
     * 显示提示信息
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
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
