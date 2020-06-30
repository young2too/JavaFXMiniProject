package passwordUpdate;

import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import Login.LoginServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PasswordUpdateServiceImpl implements PasswordUpdateService {
	DataBaseService dbSrv = new DataBaseServiceImpl();
	CommonService comSrv = new CommonServiceImpl();

	@Override
	public void registerToDB(Parent passwordUpdateForm) {
		// TODO Auto-generated method stub
		String[] txtFldArr = { "#oldPwTxt", "#newPwTxt", "#newPwOkTxt" };
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(passwordUpdateForm, txtFldArr);

		if (comSrv.isEmpty(txtFldMap, txtFldArr)) {
			comSrv.alertWindow("에러", "빈 곳이 있습니다", AlertType.ERROR);
			return;
		} else if (comparePW(passwordUpdateForm) == false) {
			comSrv.alertWindow("에러", "비밀번호가 일치하지 않습니다", AlertType.ERROR);
			return;
		}
		String currentID = LoginServiceImpl.getCurrentUser().getID();
		String newPW = txtFldMap.get(txtFldArr[2]).getText();
		String sql = "update member set pw='" + newPW + "' where id='" + currentID + "'";
		if (dbSrv.excuteSql(sql) == false) {
			comSrv.alertWindow("오류", "알 수 없는 오류", AlertType.ERROR);
			return;
		} else {
			comSrv.alertWindow("확인", "비밀번호가 변경되었습니다", AlertType.CONFIRMATION);
			comSrv.WindowClose(passwordUpdateForm);
			
		}

	}

	@Override
	public boolean comparePW(Parent passwordUpdateForm) {
		// TODO Auto-generated method stub
		TextField oldPwTxt = (TextField) passwordUpdateForm.lookup("#oldPwTxt");
		TextField newPwTxt = (TextField) passwordUpdateForm.lookup("#newPwTxt");
		TextField newPwOkTxt = (TextField) passwordUpdateForm.lookup("#newPwOkTxt");

		String currentPW = LoginServiceImpl.getCurrentUser().getPW();// 로그인 메소드에서 패스워드 받아옴
		if (oldPwTxt.getText().equals(currentPW)) {
			if (newPwTxt.getText().equals(newPwOkTxt.getText())) {
				return true;
			} else
				return false;
		} else {
			return false;
		}

	}

}
