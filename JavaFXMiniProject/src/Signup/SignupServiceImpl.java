package Signup;

import java.util.HashMap;
import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupServiceImpl implements SignupService{
	DataBaseService dbSrv = new DataBaseServiceImpl();
	CommonService comSrv = new CommonServiceImpl();
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
		ComboBox<String> cmbQuiz = (ComboBox<String>)membershipForm.lookup("#cmbQuiz");
		
		if(cmbQuiz == null) {
			return "";
		}
		return cmbQuiz.getValue().toString();
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


	@Override
	public void idChk(Parent signForm) {
		// TODO Auto-generated method stub
		TextField idTxt = (TextField)signForm.lookup("#idTxt");
		
		if(idTxt.getText().length()==0) {
			comSrv.alertWindow("아이디 미입력", "아이디를 입력해주세요.", AlertType.WARNING);
		}else if(dbSrv.overlapCheck(idTxt.getText())) {
			comSrv.alertWindow("아이디중복", "중복된 아이디 입니다.", AlertType.WARNING);
		}else {
			comSrv.alertWindow("사용가능", "사용 가능한 아이디 입니다.", AlertType.INFORMATION);
		}
		
	}


	@Override
	public void nickChk(Parent signForm) {
		// TODO Auto-generated method stub
		TextField nickTxt = (TextField)signForm.lookup("#nickTxt");
		
		if(nickTxt.getText().length()==0) {
			comSrv.alertWindow("닉네임 미입력", "닉네임을 입력해주세요.", AlertType.WARNING);
		}else if(dbSrv.overlapCheck(nickTxt.getText())) {
			comSrv.alertWindow("닉네임중복", "중복된 닉네임 입니다.", AlertType.WARNING);
		}else{
			comSrv.alertWindow("사용가능", "사용 가능한 닉네임 입니다.", AlertType.INFORMATION);
		}
	}
}
