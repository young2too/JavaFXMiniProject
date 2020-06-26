package passwordUpdate;

import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PasswordUpdateServiceImpl implements PasswordUpdateService{
	DataBaseService dbSrv = new DataBaseServiceImpl();
	CommonService comSrv = new CommonServiceImpl();
	@Override
	public void registerToDB(Parent passwordUpdateForm) {
		// TODO Auto-generated method stub
		String[] txtFldArr= {"#oldPwTxt","#newPwTxt", "#newPwOkTxt"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(passwordUpdateForm, txtFldArr);
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			comSrv.alertWindow("에러", "빈 곳이 있습니다", AlertType.ERROR);
			return;
		}
		else if(comparePW(passwordUpdateForm)) {
			System.out.println(comparePW(passwordUpdateForm));
			comSrv.alertWindow("에러", "비밀번호가 일치하지 않습니다", AlertType.ERROR);
			return;
		}
		System.out.println("다 입력했으니 DB에 등록하면 됨");
	}
	@Override
	public boolean comparePW(Parent passwordUpdateForm) {
		// TODO Auto-generated method stub
		TextField oldPwTxt = (TextField)passwordUpdateForm.lookup("#oldPwTxt");
		TextField newPwTxt = (TextField)passwordUpdateForm.lookup("#newPwTxt");
		TextField newPwOkTxt = (TextField)passwordUpdateForm.lookup("#newPwOkTxt");
		
		System.out.println("oldPw를 DB에서 찾아와서 비교하는 곳");
		
		if(newPwTxt.getText().equals(newPwOkTxt.getText())) {
			return false;
		}
		else
			return true;
	}

}
