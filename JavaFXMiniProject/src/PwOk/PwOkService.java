package PwOk;

import java.util.List;

import javafx.scene.Parent;

public interface PwOkService {
	public boolean compareId(String id, String idOk);
	// id,idOk 일치여부 확인
	
	public void checkId(Parent pwOkForm); 
	// 
	
	public void checkQuiz(Parent pwOkForm);
	public void setCheckBtn(Parent pwOkForm);
	public void registerNewPw(Parent pwOkForm);
	public boolean comparePw(Parent pwOkform);


	
	public boolean isComboBox(Parent pwOkform);
	public String getComboBoxString(Parent pwOkform);
	public void AddComboBox(Parent pwOkform);
}
