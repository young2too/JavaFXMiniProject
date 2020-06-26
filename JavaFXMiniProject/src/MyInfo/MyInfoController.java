package MyInfo;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyInfoController extends Controller implements Initializable{
	private Parent myInfoPage;
	private CommonService comSrv;
	private DataBaseService dbSrv;
	private MyInfoService myInfoSrv;
	Button backImgBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		dbSrv = new DataBaseServiceImpl();
		myInfoSrv = new MyInfoServiceImpl();
	}


	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.myInfoPage = root;

		backImgBtn = (Button)myInfoPage.lookup("#backImgbtn");
		comSrv.setMouserBtnCursurEffect(backImgBtn);
		myInfoSrv.setLabelsToMyInfo(myInfoPage);
	}
	
	public void confirmBtnAction() {
		myInfoSrv.setMyInfo();
		comSrv.WindowClose(myInfoPage);
	}
	
	public void myInfoUpdateBtnAction() {
		myInfoSrv.openUpdateMyInfoForm();
		comSrv.WindowClose(myInfoPage);
	}

	public void cancelBtnAction() {
		System.out.println("취소버튼임");
		comSrv.WindowClose(myInfoPage);
	}
	

}
