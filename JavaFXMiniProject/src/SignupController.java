package Signup;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import PwOk.PwOkService;
import PwOk.PwOkServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class SignupController extends Controller implements Initializable{
	@FXML
	private Button backBtn;
	@FXML
	private Button idChk;
	@FXML
	private Button nickChk;
	@FXML
	private Button signBtn;
	@FXML
	private Button cancelBtn;
	
	private Parent Signup;
	private CommonService comSrv;
	private SignupService signSrv;
	PwOkService pwOkSrv = new PwOkServiceImpl();
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.Signup = root;
		
		backBtn = (Button)Signup.lookup("#backBtn");
		backBtn.setStyle("-fx-base: #FFFFFF;");
	    comSrv.setMouserBtnCursurEffect(backBtn);
	    idChk = (Button)Signup.lookup("#idChk");
	    comSrv.setMouserBtnCursurEffect(idChk);
	    nickChk = (Button)Signup.lookup("#nickChk");
	    comSrv.setMouserBtnCursurEffect(nickChk);
	    signBtn = (Button)Signup.lookup("#signBtn");
	    comSrv.setMouserBtnCursurEffect(signBtn);
	    cancelBtn = (Button)Signup.lookup("#cancelBtn");
	    comSrv.setMouserBtnCursurEffect(cancelBtn);
	    pwOkSrv.AddComboBox(Signup);
	   }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		signSrv = new SignupServiceImpl();
	}
	
	public void CancelProc(ActionEvent event) { // 취소버튼 커서 및 창닫기
		comSrv.WindowClose(event);
	}
	
	private boolean isCheck(Map<String, TextField> txtFldMap, String[] txtFldArr, String[] list) {
		if(comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return false;
		}
		
		String pw = txtFldMap.get(txtFldArr[2]).getText();
		String pwOk= txtFldMap.get(txtFldArr[3]).getText();
		
		if(!signSrv.comparePW(pw, pwOk)) {
			signSrv.signupAlertWindow("오류", "패스워드가 다릅니다",AlertType.ERROR);
			txtFldMap.get(txtFldArr[2]).clear();
			txtFldMap.get(txtFldArr[2]).requestFocus();
			txtFldMap.get(txtFldArr[3]).clear();
			return false;
		}
		
		if(!signSrv.isComboBox(Signup)) {
			return false;
		}
		
		
		return true;
	}
	
	public void signupProc() { // 가입누를 때 여러가지 기능
		String []txtFldArr = {"#idTxt","#nickTxt","#pw","#pwOk","#cmbTxt"};
		String []list = {"이름","아이디","암호","암호확인","비밀번호 질문/답변"};
		Map<String, TextField> txtFldMap = signSrv.getTextFieldInfo(Signup, txtFldArr);
		
		if(!isCheck(txtFldMap, txtFldArr, list)) {
			return;
		}
		
		if(comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return;
		}
		if(!signSrv.isComboBox(Signup)) {
			comSrv.alertWindow("사용자 정보가 없습니다.", "비밀번호 분실 시 필요할 비밀번호를 입력해주세요.", AlertType.ERROR);
			return;
		}
		
		if(!comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			signSrv.signupAlertWindow("환영합니다!","회원가입 완료됬습니다. 로그인해주세요 :)", AlertType.INFORMATION);
		}
		Stage s = (Stage)Signup.getScene().getWindow();
		s.close();
	}
}
