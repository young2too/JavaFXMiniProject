package UpdateMyInfo;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import MyInfo.MyInfoService;
import MyInfo.MyInfoServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class UpdateMyInfoController extends Controller implements Initializable{
	Parent updateMyInfoForm;
	CommonService comSrv;
	DataBaseService dbSrv;
	MyInfoService myInfoSrv;
	UpdateMyInfoService upInfoSrv;
	
	Button backImgBtn;
	Button overlapBtnNick;
	Button overlapBtn;
	Button confirmBtn;
	Button cancelBtn;
	Button passwordUpdateBtn;
	@FXML
	private CheckBox newNickCheckBox;
	@FXML
	private CheckBox newIDCheckBox;
	
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		
		this.updateMyInfoForm = root;
		//루트 설정하고 초기화하는 중
		
		backImgBtn = (Button)updateMyInfoForm.lookup("#backImgBtn");
		comSrv.setMouserBtnCursurEffect(backImgBtn);
		overlapBtnNick = (Button)updateMyInfoForm.lookup("#overlapBtnNick");
		comSrv.setMouserBtnCursurEffect(overlapBtnNick);
		overlapBtn = (Button)updateMyInfoForm.lookup("#overlapBtn");
		comSrv.setMouserBtnCursurEffect(overlapBtn);
		confirmBtn = (Button)updateMyInfoForm.lookup("#confirmBtn");
		comSrv.setMouserBtnCursurEffect(confirmBtn);
		cancelBtn = (Button)updateMyInfoForm.lookup("#cancelBtn");
		comSrv.setMouserBtnCursurEffect(cancelBtn);
		passwordUpdateBtn = (Button)updateMyInfoForm.lookup("#passwordUpdateBtn");
		comSrv.setMouserBtnCursurEffect(passwordUpdateBtn);
		upInfoSrv.setCheckBoxListener(updateMyInfoForm);
		upInfoSrv.setTextFieldToMyInfo(updateMyInfoForm);
		newNickCheckBox.setOnMouseEntered(e->{
			newNickCheckBox.setCursor(Cursor.HAND);
		});
		newNickCheckBox.setOnMouseExited(e->{
			newNickCheckBox.setCursor(Cursor.DEFAULT);
		});
		newIDCheckBox.setOnMouseEntered(e->{
			newIDCheckBox.setCursor(Cursor.HAND);
		});
		newIDCheckBox.setOnMouseExited(e->{
			newIDCheckBox.setCursor(Cursor.DEFAULT);
		});

	}
	

	

	public void cancelBtnAction() {
		comSrv.WindowClose(updateMyInfoForm);
		comSrv.showWindow(new Stage(), "../MyInfo/MyInfo.fxml");
	}
	
	public void confirmBtnAction() {
		upInfoSrv.registerToDB(updateMyInfoForm);
	}
	
	public void overlapBtnAction() {
		upInfoSrv.overlapCheck(updateMyInfoForm);
	}
	
	public void overlapNickBtnAction() {
		upInfoSrv.overlapNickCheck(updateMyInfoForm);
	}
	
	public void passwordUpdateBtnAction() {
		comSrv.showWindow(new Stage(), "../passwordUpdate/passwordUpdate.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		dbSrv = new DataBaseServiceImpl();
		myInfoSrv = new MyInfoServiceImpl();
		upInfoSrv = new UpdateMyInfoServiceImpl();
	}
}
