package CommonService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void WindowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent root = (Parent) event.getSource();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public Parent showWindow(Stage s, String formPath) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
		Parent root = null;

		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Controller ctrler = loader.getController();
		ctrler.setRoot(root);

		s.show();
		return root;
	}

	@Override
	public void alertWindow(String hdMsg, String contMsg, AlertType Type) {
		// TODO Auto-generated method stubs
		Alert alert = new Alert(Type);
		alert.setHeaderText(hdMsg);
		alert.setContentText(contMsg);
		alert.show();
	}

	@Override
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldArr) {
		// TODO Auto-generated method stub
		Map<String, TextField> txtFldMap = new HashMap<String, TextField>();

		for (String txtFldId : txtFldArr) {
			TextField txtFld = (TextField) root.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}

	@Override
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldArr) {
		// TODO Auto-generated method stub
		for (String txtFldId : txtFldArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			if (txtFld.getText().isEmpty()) {
				txtFld.requestFocus();
				return true;
			}
		}
		return false;
	}

	@Override
	public void WindowClose(Parent root) {
		// TODO Auto-generated method stub
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public void setMouserBtnCursurEffect(Button btn) {
		// TODO Auto-generated method stub
		btn.setOnMouseEntered(e->{
			btn.setCursor(Cursor.HAND);
		});
		btn.setOnMouseExited(e->{
			btn.setCursor(Cursor.DEFAULT);
		});
	}

}
