package Signup;

import java.util.List;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public interface SignupService {
	
	public boolean comparePW(String pw, String pwOk); // 비번,비번확인 일치여부
	
	public boolean isComboBox(Parent signForm);
	public String getComboBoxString(Parent signForm);
	
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr); // 해당 입력창이 비었을 때 나타내주는 텍스트 이름
	
	public void signupAlertWindow(String hdMsg, String contMsg, AlertType Type);
	public void signupAlertWindow(String contMsg, AlertType Type);
	
	
	
}
