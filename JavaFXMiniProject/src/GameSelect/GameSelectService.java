package GameSelect;

import java.util.List;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.Button;


public interface GameSelectService {
	
	public boolean DisableGame(List<Button> btnList, int endScore);
	//게임을 마치고 일정 점수가 넘으면 다음게임이 활성화 되고 게임이 오픈 됬다고 알려주는 작업
	public void SelectProc(Parent root);
	//게임 버튼을 누르면 해당 게임 화면 창을 띄워주는 작업
	public Parent OpenRankForm();
	public Parent OpenMyInfoForm();

	public void TextFieldEndScore(int endScore,Parent root);
	
	public void playTetris();
	public void playPoop();
	public void playSpace();
	public void playBlock();
	
	//score와highscore를 만들어서 score<highscore면 최고 점수로 점수를 업데이트.
	//gamescore.set블라블라로 업데이트 해준다.-->새로운 메서드 만들기!!!
	//=>새로운 점수로 업데이트 후 최고점수를 get블라블라로 textfield로 쏴준다.
	
}
