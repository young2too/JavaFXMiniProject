package PwOk;

import java.util.List;

import javafx.scene.Parent;

public interface PwOkService {
	
	public Parent OpenMembershipForm();
	public boolean compareNewPW(String newPw, String newPwOk);
	/*
	 *  compareNewpW -새로운 비밀번호 비교 메소드
	 *  newPw: 새로운 비밀번호 필드 
	 *  newPwOk: 새로운 비밀번호 확인 필드
	 */
	public boolean compareQuiz(String quiz, String quizOk);;
	/*
	 *  compareQuiz -질문 비교 메소드
	 *  quiz: 질문 필드(회원가입에서 입력할 질문) 
	 *  quizOk: 질문 확인 필드(비밀번호찾기화면에서 선택할 질문)
	 */
	public boolean compareAnswer(String answer, String answerOk);
	/*
	 *  compareAnswer -질문답 비교 메소드
	 *  answer: 질문답 필드(회원가입에서 입력할 질문) 
	 *  answerOk: 질문답 확인 필드(비밀번호찾기화면에서 선택할 질문)
	 */
	public boolean isComboBox(Parent membershipForm);
	public String getComboBoxString(Parent membershipForm);
	public void AddComboBox(Parent form, List<String> items);
}
