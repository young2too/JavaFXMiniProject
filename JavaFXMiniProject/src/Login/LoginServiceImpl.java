package Login;

import javafx.stage.Stage;
import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;

public class LoginServiceImpl implements LoginService {
	private CommonService comSrv = new CommonServiceImpl();
	static Member currentUser = new Member();
	private DataBaseService dbSrv = new DataBaseServiceImpl();
	@Override
	public void LoginProc(Parent root) {
		TextField idTxt = (TextField) root.lookup("#idTxt");
		TextField pwTxt = (TextField) root.lookup("#pwTxt");
		System.out.println("ID : " + idTxt.getText() + ", PW : " + pwTxt.getText() + "가 저장되었습니다.");
		
		if(idTxt.getText().length()==0) {
			comSrv.alertWindow("오류", "아이디를 입력하세요", AlertType.INFORMATION);
			idTxt.requestFocus();
			return;
		}
		if(pwTxt.getText().length()==0) {
			comSrv.alertWindow("오류", "비밀번호를 입력하세요", AlertType.INFORMATION);
			pwTxt.requestFocus();
			return;
		}
		
		if(dbSrv.loginCheck(idTxt.getText(), pwTxt.getText())) {
			//로그인 성공시
			currentUser = dbSrv.SearchMemberByID(idTxt.getText());
			comSrv.showWindow(new Stage(), "/GameSelect/GameSelect.fxml");
			comSrv.WindowClose(root);
		}else {
			comSrv.alertWindow("실패", "비밀번호 또는 아이디가 틀립니다", AlertType.ERROR);
			idTxt.clear();
			pwTxt.clear();
			idTxt.requestFocus();
			return;
		}
		
	}

	
	@Override
	public void OpenFindForm() {
		comSrv.showWindow(new Stage(), "/PwOk/PwOkScene.fxml");
		System.out.println("아이디/비밀번호 찾기");
	}

	@Override
	public void OpenMembershipForm() {
		comSrv.showWindow(new Stage(), "/Signup/Signup.fxml");
		System.out.println("회원가입");
	}


	public static Member getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}
	public static void setCurrentUser(Member m) {
		currentUser = m;
	}

}
