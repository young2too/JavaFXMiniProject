package PwOk;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PwOkController extends Controller implements Initializable {
	private Parent root;
	private CommonService comSrv;
	private PwOkService pwOkSrv;

	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		pwOkSrv = new PwOkServiceImpl();;
	}

	public void CancelProc(ActionEvent event) {
		comSrv.WindowClose(event);
	}

	private boolean isCheck(Map<String, TextField> txtFldMap, String[] txtFldArr, String[] list) {
		if (comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			System.out.println("비어 있습니다.");
			return false;
		}

		String pw = txtFldMap.get(txtFldArr[2]).getText();
		String pwOk = txtFldMap.get(txtFldArr[3]).getText();

		if (!pwOkSrv.compareNewPW(pw, pw)) {
			comSrv.alertWindow("오류", "패스워드가 다릅니다.", AlertType.INFORMATION);
			txtFldMap.get(txtFldArr[2]).clear();
			txtFldMap.get(txtFldArr[2]).requestFocus();
			txtFldMap.get(txtFldArr[3]).clear();
			System.out.println("패스워드가 다릅니다.");
			return false;
		}

		if (!pwOkSrv.isComboBox(root)) {
			return false;
		}

		return true;
	}

}
