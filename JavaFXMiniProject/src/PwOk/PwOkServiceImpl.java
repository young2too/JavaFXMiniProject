package PwOk;

import java.util.Arrays;
import java.util.List;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PwOkServiceImpl implements PwOkService {

	@Override
	public boolean compareNewPW(String newPw, String newPwOk) {
		// TODO Auto-generated method stub
		newPw = "";
		newPwOk = "";
		return newPw.contentEquals(newPwOk);
	}

	@Override
	public boolean compareQuiz(String quiz, String quizOk) {
		// TODO Auto-generated method stub
		return quiz.contentEquals(quizOk);
	}

	@Override
	public boolean compareAnswer(String answer, String answerOk) {
		// TODO Auto-generated method stub
		return answer.contentEquals(answerOk);
	}

	@Override
	public boolean isComboBox(Parent root) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Parent OpenMembershipForm() {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		Stage membershipForm = new Stage();
		Parent form = comSrv.showWindow(membershipForm, "/PwOk/PwOkScene.fxml");

		String[] items = {"어머니 성함은?", "아버지 성함은?", "보물 1호는?"};
		PwOkService pwOkSrv = new PwOkServiceImpl();
		pwOkSrv.AddComboBox(form, Arrays.asList(items));

		return form;
	}
	
	@Override
	public String getComboBoxString(Parent membershipForm) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbQuiz = (ComboBox<String>) membershipForm.lookup("#cmbQuiz");

		if (cmbQuiz == null) {
			return "";
		}
		return cmbQuiz.getValue().toString();
	}

	@Override
	public void AddComboBox(Parent form, List<String> items) {
		// TODO Auto-generated method stub
		ComboBox<String> cmbQuiz = (ComboBox<String>) form.lookup("#cmbQuiz");

		if (cmbQuiz != null) {
			for (String item : items) {
				cmbQuiz.getItems().add(item);
			}
		}

	}

}
