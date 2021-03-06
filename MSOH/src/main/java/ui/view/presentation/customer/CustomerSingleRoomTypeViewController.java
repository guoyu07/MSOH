package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.RoomVO;

/**
 * Created by island on 2016/11/30.
 */
public class CustomerSingleRoomTypeViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Label roomTypeLabel;

    private String roomType;

    @FXML
    private Label roomNumLabel;

    private String roomNum;

    @FXML
    private Label roomPriceLabel;

    private String roomPrice;

    @FXML
    private Button reserveButton;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void init(RoomVO roomVO){
        roomType = roomVO.roomType;
        roomNum = roomVO.leftRooms + "";
        roomPrice = roomVO.price + "";
        roomTypeLabel.setText(roomType);
        roomNumLabel.setText(roomNum);
        roomPriceLabel.setText(roomPrice);
    }

    public void reserve(){

    }

}