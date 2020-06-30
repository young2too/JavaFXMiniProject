package Login;

import DataBase.Member;
import javafx.scene.Parent;

public interface LoginService {
    void LoginProc(final Parent p0);
    void OpenFindForm();
    void OpenMembershipForm();
}