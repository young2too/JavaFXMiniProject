package Start;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController extends Controller implements Initializable {
	Parent startForm;
	CommonService comSrv;
	Button startBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.startForm = root;
		startBtn = (Button)startForm.lookup("startBtn");
//		comSrv.setMouserBtnCursurEffect(startBtn);
	}
	
	public void startBtnAction(ActionEvent event) {
		comSrv.WindowClose(startForm);
		comSrv.showWindow(new Stage(), "/Login/Login.fxml");
	}
}
