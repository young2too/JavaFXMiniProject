package Login;

import javafx.event.ActionEvent;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;

import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController extends Controller implements Initializable
{
	
	@FXML
	private Button MembershipBtn; 
	@FXML
	private Button LoginBtn;
	@FXML
	private Button FindBtn;
	@FXML
	private Button CancelBtn;
	
    private Parent root;
    private LoginService loginSrv;
    private CommonService comSrv;
    
    public void setRoot(Parent root) {
        this.root = root;
        
        comSrv.setMouserBtnCursurEffect(MembershipBtn);
        comSrv.setMouserBtnCursurEffect(CancelBtn);
        comSrv.setMouserBtnCursurEffect(FindBtn);
        comSrv.setMouserBtnCursurEffect(LoginBtn);
    }
    
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        this.loginSrv = new LoginServiceImpl();
        this.comSrv = new CommonServiceImpl();
    }
    
    public void LoginProc() {
        this.loginSrv.LoginProc(this.root);
    }
    
    public void CancelProc() {
       	comSrv.WindowClose(root);
    }
    
    public void OpenFindForm() {
    	this.loginSrv.OpenFindForm();
    }
    
    public void OpenMembershipForm() {
        this.loginSrv.OpenMembershipForm();
    }
}