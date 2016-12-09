package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ui.controller.CustomerInfoChangeController;
import ui.view.controllerservice.CustomerInfoChange;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerModifyPasswordViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerModifyPasswordView.fxml";

    private String customerID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField originalPasswordTextField;

    @FXML
    private TextField firstNewPasswordTextField;

    @FXML
    private TextField secondNewPasswordTextField;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    @FXML
    private void confirmModifyPassword(){
        String originalPassword = originalPasswordTextField.getText();
        String firstNewPassword = firstNewPasswordTextField.getText();
        String secondNewPassword = secondNewPasswordTextField.getText();
        CustomerInfoChange customerInfoChange = new CustomerInfoChangeController();
        try {
            ResultMessage resultMessage = customerInfoChange.changePassword(customerID, originalPassword, firstNewPassword, secondNewPassword);
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            if(resultMessage ==ResultMessage.Blank){
                errorBoxController.setLabel("未完成填写！");
            }
            else if(resultMessage == ResultMessage.ChangePasswordWrongOldPw){
                errorBoxController.setLabel("原密码输入错误！");
                clearField();
            }
            else if(resultMessage == ResultMessage.ChangePassword2DifferentNew){
                errorBoxController.setLabel("两次密码输入不一致！");
                clearField();
            }
            else if(resultMessage == ResultMessage.ChangePasswordSuccess){
                errorBoxController.setLabel("成功修改密码！");
                clearField();
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private void clearField(){
        originalPasswordTextField.setText("");
        firstNewPasswordTextField.setText("");
        secondNewPasswordTextField.setText("");
    }

    public void init(String customerID){
        this.customerID = customerID;
    }
}
