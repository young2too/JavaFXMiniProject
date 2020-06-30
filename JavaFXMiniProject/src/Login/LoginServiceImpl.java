package Login;

import javafx.stage.Stage;
import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.Parent;

public class LoginServiceImpl implements LoginService {
	CommonService comSrv = new CommonServiceImpl();
	@Override
	public void LoginProc(Parent root) {
		TextField idTxt = (TextField) root.lookup("#idTxt");
		TextField pwTxt = (TextField) root.lookup("#pwTxt");
		System.out.println("ID : " + idTxt.getText() + ", PW : " + pwTxt.getText() + "가 저장되었습니다.");
		
		
		
		if(true) {
			//로그인 성공시
			comSrv.showWindow(new Stage(), "/GameSelect/GameSelect.fxml");
			comSrv.WindowClose(root);
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
}
