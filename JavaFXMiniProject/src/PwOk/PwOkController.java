package PwOk;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class PwOkController extends Controller implements Initializable {
	Parent pwOkForm;
	CommonService comSrv;
	PwOkService pwOkSrv;
	Member member;
	DataBaseService dbSrv;

	Button backBtn;

	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.pwOkForm = root;
		backBtn = (Button) pwOkForm.lookup("#backImgBtn");
		comSrv.setMouserBtnCursurEffect(backBtn);
		pwOkSrv.setCheckBtn(pwOkForm);
		pwOkSrv.AddComboBox(pwOkForm);
	}

	public void cancelBtnAction() {
		comSrv.WindowClose(pwOkForm);
		comSrv.showWindow(new Stage(), "/Login/Login.fxml");
	}
	
	public void confirmIdBtnAction() {
		pwOkSrv.checkId(pwOkForm);
	}

	public void confirmQuizBtnAction() {
		pwOkSrv.checkQuiz(pwOkForm);
	}
	
	public void confirmPwBtnAction() {
		pwOkSrv.registerNewPw(pwOkForm);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		pwOkSrv = new PwOkServiceImpl();
		dbSrv = new DataBaseServiceImpl();
	}

	
}