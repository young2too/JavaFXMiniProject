package Login;

import javafx.event.ActionEvent;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;

import java.net.URL;
import javafx.scene.Parent;
import javafx.fxml.Initializable;

public class LoginController extends Controller implements Initializable
{
    private Parent root;
    private LoginService loginSrv;
    private CommonService comSrv;
    
    public void setRoot(Parent root) {
        this.root = root;
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