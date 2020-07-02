package Signup;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import PwOk.PwOkService;
import PwOk.PwOkServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController extends Controller implements Initializable {
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
	@FXML
	private ComboBox<String> cmbQuiz;
	private Parent Signup;
	private CommonService comSrv;
	private SignupService signSrv;
	PwOkService pwOkSrv = new PwOkServiceImpl();
	private DataBaseService dbSrv;
	
	boolean idOk = false;
	boolean nickOK = false;
	boolean pwOk = false;

	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.Signup = root;

		backBtn = (Button) Signup.lookup("#backBtn");
		backBtn.setStyle("-fx-base: #FFFFFF;");
		comSrv.setMouserBtnCursurEffect(backBtn);
		idChk = (Button) Signup.lookup("#idChk");
		comSrv.setMouserBtnCursurEffect(idChk);
		nickChk = (Button) Signup.lookup("#nickChk");
		comSrv.setMouserBtnCursurEffect(nickChk);
		signBtn = (Button) Signup.lookup("#signBtn");
		comSrv.setMouserBtnCursurEffect(signBtn);
		cancelBtn = (Button) Signup.lookup("#cancelBtn");
		comSrv.setMouserBtnCursurEffect(cancelBtn);
		pwOkSrv.AddComboBox(Signup);
		cmbQuiz.setOnMouseEntered(e->{
			cmbQuiz.setCursor(Cursor.HAND);
		});
		cmbQuiz.setOnMouseExited(e->{
			cmbQuiz.setCursor(Cursor.DEFAULT);
		});

		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		signSrv = new SignupServiceImpl();
		dbSrv = new DataBaseServiceImpl();
	}

	public void CancelProc(ActionEvent event) { // 취소버튼 커서 및 창닫기
		comSrv.WindowClose(event);
	}

	private boolean isCheck(Map<String, TextField> txtFldMap, String[] txtFldArr, String[] list) {
		if (comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return false;
		}

		String pw = txtFldMap.get(txtFldArr[2]).getText();
		String pwOk = txtFldMap.get(txtFldArr[3]).getText();

		if (!signSrv.comparePW(pw, pwOk)) {
			comSrv.alertWindow("오류", "패스워드가 다릅니다", AlertType.ERROR);
			txtFldMap.get(txtFldArr[2]).clear();
			txtFldMap.get(txtFldArr[2]).requestFocus();
			txtFldMap.get(txtFldArr[3]).clear();
			return false;
		}

		if (!signSrv.isComboBox(Signup)) {
			return false;
		}
		return true;
	}

	public void idChk() {
		idOk = signSrv.idChk(Signup);
	}

	public void nickChk() {
		nickOK = signSrv.nickChk(Signup);
	}

	public void signupProc() { // 가입누를 때 여러가지 기능
		
		String[] txtFldArr = { "#idTxt", "#nickTxt", "#pw", "#pwOk", "#cmbTxt" };
		String[] list = { "아이디 ", "닉네임 ", "비밀번호 ", "비밀번호 확인 ", "비밀번호 질문/답변 " };
		Map<String, TextField> txtFldMap = signSrv.getTextFieldInfo(Signup, txtFldArr);

		if (!(pwOk=isCheck(txtFldMap, txtFldArr, list))) {
			return;
		}

		if (comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return;
		}
		if (!signSrv.isComboBox(Signup)) {
			comSrv.alertWindow("사용자 정보가 없습니다.", "비밀번호 분실 시 필요할 비밀번호를 입력해주세요.", AlertType.ERROR);
			return;
		}

		if(idOk&&pwOk&&nickOK) {
			Member addMem = new Member();
			addMem.setID(txtFldMap.get(txtFldArr[0]).getText());
			addMem.setNickName(txtFldMap.get(txtFldArr[1]).getText());
			addMem.setPW(txtFldMap.get(txtFldArr[2]).getText());
			addMem.setQuiz(signSrv.getComboBoxString(Signup));
			addMem.setAnswer(txtFldMap.get(txtFldArr[4]).getText());
			addMem.setScore(0);
			dbSrv.insert(addMem);
			comSrv.alertWindow("가입성공", "축하합니다!", AlertType.CONFIRMATION);
			comSrv.WindowClose(Signup);
		}else {
			comSrv.alertWindow("오류", "가입할 수 없습니다", AlertType.ERROR);
			return;
		}
	}
}

