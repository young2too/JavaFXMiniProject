package UpdateMyInfo;

import java.util.Map;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class UpdateMyInfoServiceImpl implements UpdateMyInfoService{
	CommonService comSrv = new CommonServiceImpl();
	public void setCheckBoxListener(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		CheckBox newIDCheckBox = (CheckBox)updateMyInfoForm.lookup("#newIDCheckBox");
		CheckBox newNickCheckBox = (CheckBox)updateMyInfoForm.lookup("#newNickCheckBox");
		Button overlapIDBtn = (Button)updateMyInfoForm.lookup("#overlapBtn");
		Button overlapNickBtn = (Button)updateMyInfoForm.lookup("#overlapBtnNick");
		
		
		newIDCheckBox.setOnMouseClicked(e->{
			TextField newIDTxt = (TextField)updateMyInfoForm.lookup("#newIDTxt");
			newIDTxt.setDisable(!newIDTxt.isDisable());
			overlapIDBtn.setDisable(!overlapIDBtn.isDisable());
		});
		
		newNickCheckBox.setOnMouseClicked(e->{
			TextField newNickTxt = (TextField)updateMyInfoForm.lookup("#newNickTxt");
			newNickTxt.setDisable(!newNickTxt.isDisable());
			overlapNickBtn.setDisable(!overlapNickBtn.isDisable());
		});
	}

	@Override
	public void setTextFieldToMyInfo(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		System.out.println("DB에서 받아와서 ID와 PW의 promptText를 내 것으로 바꿔줌");
	}

	@Override
	public void registerToDB(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		
		Map<String, TextField> txtFldMap;
		boolean nickFlag = false;
		boolean IDFlag = false;
		if(((CheckBox)updateMyInfoForm.lookup("#newNickCheckBox")).isSelected()) {
			nickFlag =true;
		}
		if(((CheckBox)updateMyInfoForm.lookup("#newIDCheckBox")).isSelected()) {
			IDFlag =true;
		}
		
		if(nickFlag == true&&IDFlag==true) {
			String[] txtFldArr= {"#newNickTxt","#newIDTxt"};
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다",AlertType.ERROR);
				return;
			}
		}
		else if(nickFlag == false &&IDFlag==true) {
			String[] txtFldArr= {"#newIDTxt"};
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다",AlertType.ERROR);
				return;
			}
		}
		else if(nickFlag == true &&IDFlag==false) {
			String[] txtFldArr= {"#newNickTxt"};
			txtFldMap = comSrv.getTextFieldInfo(updateMyInfoForm, txtFldArr);
			if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
				comSrv.alertWindow("에러", "빈 곳이 있습니다",AlertType.ERROR);
				return;
			}
		}
		
		System.out.println("다 입력했으니 DB에 등록하면 됨");
		comSrv.WindowClose(updateMyInfoForm);
		comSrv.showWindow(new Stage(), "../MyInfo/MyInfo.fxml");
	}

	@Override
	public void overlapCheck(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		TextField newIDTxt = (TextField)updateMyInfoForm.lookup("#newIDTxt");
		System.out.println("newIDTxt를 이용하여 DB 중복체크");
	}

	@Override
	public void overlapNickCheck(Parent updateMyInfoForm) {
		// TODO Auto-generated method stub
		TextField newNickTxt = (TextField)updateMyInfoForm.lookup("#newNickTxt");
		System.out.println("newNickTxt를 이용하여 DB 중복체크");
	}
}
