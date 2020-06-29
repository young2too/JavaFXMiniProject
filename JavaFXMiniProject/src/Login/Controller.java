package Login;

import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.scene.Parent;
import javafx.fxml.Initializable;

public class Controller implements Initializable
{
    private Parent root;
    private LoginService loginSrv;
    
    public void setRoot(final Parent root) {
        this.root = root;
    }
    
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        this.loginSrv = (LoginService)new LoginServiceImpl();
    }
    
    public void LoginProc() {
        this.loginSrv.LoginProc(this.root);
    }
    
    public void CancelProc(final ActionEvent e) {
        this.loginSrv.CancelProc(e);
    }
    
    public void OpenFindForm() {
    	this.loginSrv.OpenFindForm();
    }
    
    public void OpenMembershipForm() {
        this.loginSrv.OpenMembershipForm();
    }
}