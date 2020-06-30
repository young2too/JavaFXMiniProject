package Ex12LoginFinal.Service;

import javafx.scene.Parent;
import Ex12LoginFinal.Member;
import Ex12LoginFinal.DAO.DatabaseService;
import Ex12LoginFinal.DAO.DatabaseServiceImpl;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService {
	
	Member m = new Member();
	
	public  void LoginProc(Parent root) {
    TextField idTxt = (TextField)root.lookup("#idTxt");
    TextField pwTxt = (TextField)root.lookup("#pwTxt");
    
    DatabaseService db = new DatabaseServiceImpl();
    boolean login = db.Select(idTxt.getText(), pwTxt.getText());
    CommonService com = new CommonServiceImpl();
    System.out.println(login);
    if (login) {
      com.ErrorMsg("로그인 되었습니다.");
    } else {
      com.ErrorMsg("아이디 패스워드가 틀립니다.");
    }
    System.out.println("ID : " + idTxt.getText() + ", PW : " + 
      pwTxt.getText() + " 가 입력 되었습니다");
  }
	  
  public Parent OpenMembershipForm() {
    CommonService comSrv = new CommonServiceImpl();
    Stage membershipForm = new Stage();
    Parent form = comSrv.showWindow(membershipForm, "../Membership.fxml");
    
//    String[] items = { "20대 미만", "20대", "30대", "40대", "50대", "60대 이상" };
//    MembershipService memberSrv = new MembershipServiceImpl();
//    memberSrv.AddComboBox(form, Arrays.asList(items));
//    
    return form;
  }

@Override
public Parent OpenFindForm() {
	CommonService comSrv = new CommonServiceImpl();
    Stage findForm = new Stage();
    Parent form = comSrv.showWindow(findForm, "../Membership.fxml");
    
//    String[] items = { "20대 미만", "20대", "30대", "40대", "50대", "60대 이상" };
//    MembershipService memberSrv = new MembershipServiceImpl();
//    memberSrv.AddComboBox(form, Arrays.asList(items));
    
    return form;
	}
}