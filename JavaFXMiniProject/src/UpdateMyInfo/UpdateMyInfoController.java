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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdateMyInfoController extends Controller implements Initializable{
	Parent updateMyInfoForm;
	CommonService comSrv;
	DataBaseService dbSrv;
	MyInfoService myInfoSrv;
	UpdateMyInfoService upInfoSrv;
	
	Button backImgBtn;
	
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		
		this.updateMyInfoForm = root;
		//루트 설정하고 초기화하는 중
		
		backImgBtn = (Button)updateMyInfoForm.lookup("#backImgBtn");
		comSrv.setMouserBtnCursurEffect(backImgBtn);
		upInfoSrv.setCheckBoxListener(updateMyInfoForm);
		upInfoSrv.setTextFieldToMyInfo(updateMyInfoForm);
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
