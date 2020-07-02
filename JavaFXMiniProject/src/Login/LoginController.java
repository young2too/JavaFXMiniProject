package Login;

import javafx.event.ActionEvent;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;

import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

public class LoginController extends Controller implements Initializable
{
    private Parent root;
    private LoginService loginSrv;
    private CommonService comSrv;
    
    private Button LoginBtn;
    private Button CancelBtn;
    private Button FindBtn;
    private Button MembershipBtn;
    
    public void setRoot(Parent root) {
    	this.root = root;
    	
    	LoginBtn = (Button) root.lookup("#LoginBtn");
		comSrv.setMouserBtnCursurEffect(LoginBtn);
		CancelBtn = (Button) root.lookup("#CancelBtn");
		comSrv.setMouserBtnCursurEffect(CancelBtn);
		FindBtn = (Button) root.lookup("#FindBtn");
		comSrv.setMouserBtnCursurEffect(FindBtn);
		MembershipBtn = (Button) root.lookup("#MembershipBtn");
		comSrv.setMouserBtnCursurEffect(MembershipBtn);




    	
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