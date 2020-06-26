package CommonService;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public interface CommonService {
	public void WindowClose(ActionEvent event); // 창 닫는 메소드
	public void WindowClose(Parent root); // 창 닫는 메소드
	public Parent showWindow(Stage s,String formPath);
	
	public void alertWindow(String hdMsg, String contMsg, AlertType Type);
	
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr);
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr);
	
	public void setMouserBtnCursurEffect(Button btn);
}
