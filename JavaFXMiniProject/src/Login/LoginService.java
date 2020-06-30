package Ex12LoginFinal.Service;

import javafx.scene.Parent;

public abstract interface LoginService {
  public abstract void LoginProc(Parent paramParent);
  public Parent OpenFindForm();
  public Parent OpenMembershipForm();
}