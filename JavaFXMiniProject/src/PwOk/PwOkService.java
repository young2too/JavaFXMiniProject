package PwOk;

import java.util.List;
import javafx.scene.Parent;

public interface PwOkService {

	public void checkId(Parent pwOkForm); 
	// 아이디 중복확인
	
	public void checkQuiz(Parent pwOkForm);
	// 질문 중복확인
	
	public void setCheckBtn(Parent pwOkForm);
	// 버튼 환경설정
	
	public void registerNewPw(Parent pwOkForm);
	// 새로운 비밀번호 등록
	
	public boolean comparePw(Parent pwOkform);
	// 비밀번호 중복확인 
	
	
	
	public void AddComboBox(Parent pwOkform);
	// 콤보박스 내용 추가 
	
	
	
	public boolean isComboBox(Parent pwOkform);
	public String getComboBoxString(Parent pwOkform);
	

}
