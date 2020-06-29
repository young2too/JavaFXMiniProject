package PwOk;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oracle.jdbc.driver.DBConversion;

public class PwOkServiceImpl implements PwOkService {
	
	CommonService comSrv = new CommonServiceImpl();
	PwOkServiceImpl pwSrv;
	Member member;
	DataBaseService db = new DataBaseServiceImpl();
	
	@Override
	public boolean compareId(String id, String idOk) {
		// TODO Auto-generated method stub
		id = member.getId();
		
		return id.equals(idOk);
	}

	@Override
	public void checkId(Parent pwOkForm) {
		// TODO Auto-generated method stub
		TextField idTxt = (TextField)pwOkForm.lookup("#idTxt");
		
	}
	
	@Override
	public void checkQuiz(Parent pwOkForm) {
		// TODO Auto-generated method stub
		TextField cmbQuiz = (TextField)pwOkForm.lookup("#cmbQuiz");
		
	}

	@Override
	public void setCheckBtn(Parent pwOkForm) {
		// TODO Auto-generated method stub
		Button confirmIdBtn = (Button)pwOkForm.lookup("#confirmIdBtn");
		Button confirmQuizBtn = (Button)pwOkForm.lookup("#confirmQuizBtn");
		Button confirmPwBtn = (Button)pwOkForm.lookup("#confirmPwBtn");
		
		confirmIdBtn.setOnMouseClicked(e->{
			comSrv.setMouserBtnCursurEffect(confirmIdBtn);
		});
		
		confirmQuizBtn.setOnMouseClicked(e->{
			TextField newPwTxt = (TextField)pwOkForm.lookup("#newPwTxt");
			TextField newPwOkTxt = (TextField)pwOkForm.lookup("#newPwOkTxt");
			
			newPwTxt.setDisable(!newPwTxt.isDisable());
			newPwOkTxt.setDisable(!newPwOkTxt.isDisable());
			confirmPwBtn.setDisable(!confirmPwBtn.isDisable());
			comSrv.setMouserBtnCursurEffect(confirmIdBtn);
		});
	}

	@Override
	public void registerNewPw(Parent pwOkForm) {
		// TODO Auto-generated method stub
		String[] txtFldArr= {"#newPwTxt", "#newPwOkTxt"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(pwOkForm, txtFldArr);
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			comSrv.alertWindow("오류 발생", "작성을 완료해주세요", AlertType.ERROR);
			return;
		}
		else if(comparePw(pwOkForm)) {
			System.out.println(comparePw(pwOkForm));
			comSrv.alertWindow("오류 발생", "비밀번호가 일치하지 않습니다", AlertType.ERROR);
			return;
		}
		System.out.println("다 입력했으니 DB에 등록하면 됨");
	}

	@Override
	public boolean comparePw(Parent pwOkform) {
		// TODO Auto-generated method stub
		TextField newPwTxt = (TextField)pwOkform.lookup("#newPwTxt");
		TextField newPwOkTxt = (TextField)pwOkform.lookup("#newPwOkTxt");
		
		System.out.println("oldPw를 DB에서 찾아와서 비교하는 곳");
		
		if(newPwTxt.getText().equals(newPwOkTxt.getText())) {
			return false;
		}
		else
			return true;
	}

	@Override
	public boolean isComboBox(Parent pwOkform) {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		ComboBox<String> cmbQuiz = (ComboBox<String>)pwOkform.lookup("#cmbQuiz");
		if(cmbQuiz == null) {
			comSrv.alertWindow("오류 발생", "콤보박스가 비어 있습니다.", AlertType.ERROR);
			return false;
		} else if (cmbQuiz.getValue() == null) {
			comSrv.alertWindow("오류 발생", "콤보박스가 비어 있습니다.", AlertType.ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String getComboBoxString(Parent pwOkform) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbQuiz = (ComboBox<String>) pwOkform.lookup("#cmbQuiz");

		if (cmbQuiz == null) {
			return "";
		}
		return cmbQuiz.getValue().toString();
	}

	@Override
	public void AddComboBox(Parent pwOkform) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbQuiz = (ComboBox<String>)pwOkform.lookup("#cmbQuiz");
		
		List<String> items = new ArrayList<String>();
		items.add("어머니 성함은?");
		items.add("아버지 성함은?");
		items.add("보물 1호는?");
		
		if (cmbQuiz != null) {
			for (String item : items) {
				cmbQuiz.getItems().add(item);
			}
		}

	}
	



}
