package Signup;

import java.util.HashMap;
import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupServiceImpl implements SignupService{
	
	@Override
	public boolean comparePW(String pw, String pwOk) {
		// TODO Auto-generated method stub
		return pw.contentEquals(pwOk);
	}


	@Override
	public boolean isComboBox(Parent membershipForm) {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		ComboBox<String> cmbBox = (ComboBox<String>)membershipForm.lookup("#cmbQuiz");
		if(cmbBox.getValue() == null) {
			comSrv.alertWindow("사용자 정보가 없습니다.", "비밀번호 질문을 선택해주세요.", AlertType.ERROR);
			return false;
		}
		return true;
	}
	
	@Override
	public String getComboBoxString(Parent membershipForm) {
		// TODO Auto-generated method stub
		ComboBox<String> selfQuiz = (ComboBox<String>)membershipForm.lookup("#cmbQuiz");
		
		if(selfQuiz == null) {
			return "";
		}
		return selfQuiz.getValue().toString();
	}

	@Override
	public void signupAlertWindow(String hdMsg, String contMsg, AlertType Type) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(hdMsg);
		alert.setContentText(contMsg);
		alert.show();
		
	}

	@Override
	public void signupAlertWindow(String contMsg, AlertType Type) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(contMsg);
		alert.show();
	}

	@Override
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr) {
		Map<String, TextField> txtFldMap = new HashMap<String, TextField>();
		
		for(String txtFldId : txtFldArr) {
			TextField txtFld = (TextField)root.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}
}
