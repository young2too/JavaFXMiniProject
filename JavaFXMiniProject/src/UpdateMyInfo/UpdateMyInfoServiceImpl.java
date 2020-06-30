package UpdateMyInfo;

import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import Login.LoginServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class UpdateMyInfoServiceImpl implements UpdateMyInfoService {
	CommonService comSrv = new CommonServiceImpl();
	DataBaseService dbSrv = new DataBaseServiceImpl();

	public void setCheckBoxListener(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		CheckBox newIDCheckBox = (CheckBox) updateMyInfoForm.lookup("#newIDCheckBox");
		CheckBox newNickCheckBox = (CheckBox) updateMyInfoForm.lookup("#newNickCheckBox");
		Button overlapIDBtn = (Button) updateMyInfoForm.lookup("#overlapBtn");
		Button overlapNickBtn = (Button) updateMyInfoForm.lookup("#overlapBtnNick");

		newIDCheckBox.setOnMouseClicked(e -> {
			TextField newIDTxt = (TextField) updateMyInfoForm.lookup("#newIDTxt");
			newIDTxt.setDisable(!newIDTxt.isDisable());
			overlapIDBtn.setDisable(!overlapIDBtn.isDisable());
		});

		newNickCheckBox.setOnMouseClicked(e -> {
			TextField newNickTxt = (TextField) updateMyInfoForm.lookup("#newNickTxt");
			newNickTxt.setDisable(!newNickTxt.isDisable());
			overlapNickBtn.setDisable(!overlapNickBtn.isDisable());
		});
	}

	@Override
	public void setTextFieldToMyInfo(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		// 현재 로그인한 ID는 로그인서비스에서 받아오면 됨
		String id = LoginServiceImpl.getCurrentUser().getID();
		Member m = new Member();
		m = dbSrv.SearchMemberByID(id);
		TextField newNickTxt = (TextField) updateMyInfoForm.lookup("#newNickTxt");
		TextField newIDTxt = (TextField) updateMyInfoForm.lookup("#newIDTxt");
		System.out.println("DB에서 받아와서 ID와 PW의 promptText를 내 것으로 바꿔줌");

		newNickTxt.setPromptText(m.getNickName());
		newIDTxt.setPromptText(m.getID());

	}

	@Override
	public void registerToDB(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub

		Map<String, TextField> txtFldMap;
		boolean nickFlag = false;
		boolean IDFlag = false;
		if (((CheckBox) updateMyInfoForm.lookup("#newNickCheckBox")).isSelected()) {
			nickFlag = true;
		}
		if (((CheckBox) updateMyInfoForm.lookup("#newIDCheckBox")).isSelected()) {
			IDFlag = true;
		}

		if (nickFlag == true && IDFlag == true) {
			String[] txtFldArr = { "#newNickTxt", "#newIDTxt" };
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if (comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다", AlertType.ERROR);
				return;
			}
		} else if (nickFlag == false && IDFlag == true) {
			String[] txtFldArr = { "#newIDTxt" };
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if (comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다", AlertType.ERROR);
				return;
			}
		} else if (nickFlag == true && IDFlag == false) {
			String[] txtFldArr = { "#newNickTxt" };
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if (comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다", AlertType.ERROR);
				return;
			}
		}

		System.out.println("다 입력했으니 DB에 등록하면 됨");
		String currentID = LoginServiceImpl.getCurrentUser().getID();// 이건 로그인 서비스에서 받아와야 함
		TextField newIDTxt, newNickTxt;
		newIDTxt = (TextField) updateMyInfoForm.lookup("#newIDTxt");
		newNickTxt = (TextField) updateMyInfoForm.lookup("#newNickTxt");
		if (nickFlag == true && IDFlag == true) {
			String sql = ("update member set id='" + newIDTxt.getText() + "', nickname='" + newNickTxt.getText()
					+ "' where id='" + currentID + "'");
			// update member set id=? and nickname=? where id=?
			System.out.println(sql);
			if (dbSrv.excuteSql(sql) == false) {
				comSrv.alertWindow("에러", "알 수 없는 오류", AlertType.ERROR);
				return;
			} else {
				dbSrv.commit();
				LoginServiceImpl.setCurrentUser(dbSrv.SearchMemberByID(newIDTxt.getText()));
			}

		} else if (nickFlag == false && IDFlag == true) {
			String sql = ("update member set id='" + newIDTxt.getText() + "' where id='" + currentID + "'");
			System.out.println(sql);
			if (dbSrv.excuteSql(sql) == false) {
				comSrv.alertWindow("에러", "알 수 없는 오류", AlertType.ERROR);
				return;
			} else {
				dbSrv.commit();
				LoginServiceImpl.setCurrentUser(dbSrv.SearchMemberByID(newIDTxt.getText()));
			}
		} else if (nickFlag == true && IDFlag == false) {
			String sql = ("update member set nickname='" + newNickTxt.getText()+"' where id='" + currentID + "'");
			
			System.out.println(sql);
			if (dbSrv.excuteSql(sql) == false) {
				comSrv.alertWindow("에러", "알 수 없는 오류", AlertType.ERROR);
				return;
			} else {
				dbSrv.commit();
			}
		}

		comSrv.WindowClose(updateMyInfoForm);
		comSrv.showWindow(new Stage(), "../MyInfo/MyInfo.fxml");
	}

	@Override
	public void overlapCheck(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		TextField newIDTxt = (TextField) updateMyInfoForm.lookup("#newIDTxt");
		if (newIDTxt.getText().length() == 0) {
			comSrv.alertWindow("에러", "ID를 적어주세요!", AlertType.ERROR);
			return;
		}
		if (dbSrv.overlapCheck(newIDTxt.getText()) == false) {
			comSrv.alertWindow("사용가능", "사용가능한 아이디입니다!", AlertType.CONFIRMATION);
			return;
		} else {
			comSrv.alertWindow("중복", "중복된 아이디입니다!", AlertType.INFORMATION);
			return;
		}
		// System.out.println("newIDTxt를 이용하여 DB 중복체크");
	}

	@Override
	public void overlapNickCheck(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		TextField newNickTxt = (TextField) updateMyInfoForm.lookup("#newNickTxt");
		if (newNickTxt.getText().length() == 0) {
			comSrv.alertWindow("에러", "닉네임을 적어주세요!", AlertType.ERROR);
			return;
		}
		if (dbSrv.SearchMemberByNickname(newNickTxt.getText()) == null) {
			comSrv.alertWindow("사용가능", "사용가능한 닉네임입니다!", AlertType.CONFIRMATION);
			return;
		} else {
			comSrv.alertWindow("중복", "중복된 닉네임입니다!", AlertType.INFORMATION);
			return;
		}
		// System.out.println("newNickTxt를 이용하여 DB 중복체크");
	}
}
