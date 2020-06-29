package Login;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.Parent;

public class LoginServiceImpl implements LoginService {
	@Override
	public void LoginProc(final Parent root) {
		final TextField idTxt = (TextField) root.lookup("#idTxt");
		final TextField pwTxt = (TextField) root.lookup("#pwTxt");
		System.out.println("ID : " + idTxt.getText() + ", PW : " + pwTxt.getText() + "가 저장되었습니다.");
	}


	@Override
	public void OpenFindForm() {
		System.out.println("아이디/비밀번호 찾기");
	}

	@Override
	public void OpenMembershipForm() {
		System.out.println("회원가입");
	}
}
