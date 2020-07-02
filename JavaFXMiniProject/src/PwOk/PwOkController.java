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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PwOkController extends Controller implements Initializable {
	
	@FXML
	private Button confirmPwBtn;
	@FXML
	private Button confirmQuizBtn;
	@FXML
	private Button confirmIdBtn;
	@FXML
	private ComboBox<String> cmbQuiz;
	
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
		comSrv.setMouserBtnCursurEffect(confirmIdBtn);
		comSrv.setMouserBtnCursurEffect(confirmPwBtn);
		comSrv.setMouserBtnCursurEffect(confirmQuizBtn);
		
		cmbQuiz.setOnMouseEntered(e->{
			cmbQuiz.setCursor(Cursor.HAND);
		});
		cmbQuiz.setOnMouseExited(e->{
			cmbQuiz.setCursor(Cursor.DEFAULT);
		});
		
		pwOkSrv.setCheckBtn(pwOkForm);
		pwOkSrv.AddComboBox(pwOkForm);
	}

	public void cancelBtnAction() {
		comSrv.WindowClose(pwOkForm);
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
