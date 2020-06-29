package passwordUpdate;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class PasswordUpdateController extends Controller implements Initializable{
	Parent passwordUpdateForm;
	CommonService comSrv;
	DataBaseService dbSrv;
	PasswordUpdateService passSrv;
	
	Button backImgBtn;
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.passwordUpdateForm = root;
		
		backImgBtn = (Button)passwordUpdateForm.lookup("#backImgBtn");
		comSrv.setMouserBtnCursurEffect(backImgBtn);
	}
	
	public void cancelBtnAction() {
		comSrv.WindowClose(passwordUpdateForm);
	}
	
	public void confirmBtnAction() {
		passSrv.registerToDB(passwordUpdateForm);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		dbSrv = new DataBaseServiceImpl();
		passSrv = new PasswordUpdateServiceImpl();
	}

}
