package GameSelect;

import java.util.ArrayList;
import java.util.List;

import Breakoutgame.Breakout;
import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import SpaceInvaders.SpaceInvaders;
import TetrisGame.Tetris;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GameSelectImpl implements GameSelectService {

	@Override
	public void SelectProc(Parent root) {
		// TODO Auto-generated method stub
		Button tetrisBtn = (Button) root.lookup("#tetrisBtn");
		Button poopBtn = (Button) root.lookup("#poopBtn");
		Button blockBtn = (Button) root.lookup("#blockBtn");
		Button spaceBtn = (Button) root.lookup("#spaceBtn");
		List<Button> btnList = new ArrayList<Button>();
		btnList.add(tetrisBtn);
		btnList.add(poopBtn);
		btnList.add(blockBtn);
		btnList.add(spaceBtn);

	}

	@Override
	public Parent OpenRankForm() {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		Stage rankForm = new Stage();
		Parent form = comSrv.showWindow(rankForm, "../Rank/RankScene.fxml");
		return form;
	}

	@Override
	public Parent OpenMyInfoForm() {
		CommonService comSrv = new CommonServiceImpl();
		Stage MyInfo = new Stage();
		Parent form = comSrv.showWindow(MyInfo, "../MyInfo/MyInfo.fxml");
		return form;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean DisableGame(List<Button> btnList, int endScore) {
		// 씬빌더로 똥피하기,오목,가위바위보,숫자야구는 비활성화 된 상태
		// TODO Auto-generated method stub
		

		boolean a = false;
		boolean b = false;
		boolean c = false;

		// comSrv.alertWindow("Final Game", "^-^ Open ^-^", AlertType.INFORMATION);
		// 게임이 끝난상태에서 최종점수에서 조건문을 돌려 새로운 게임이 오픈됬다고 알림창을 띄워줌.
		if (endScore > 300) {
			btnList.get(3).setDisable(false);
			if (c == false) {
				// comSrv.alertWindow("블러부수기 Game", "^-^ Open ^-^", AlertType.INFORMATION);
				c = true;
			}

		}
		if (endScore > 200) {
			btnList.get(2).setDisable(false);
			if (b == false) {
				// comSrv.alertWindow("오목게임 Game", "^-^ Open ^-^", AlertType.INFORMATION);
				b = true;
			}

		}
		if (endScore > 100) {
			btnList.get(1).setDisable(false);
			if (a == false) {
				// comSrv.alertWindow("똥피하기 Game", "^-^ Open ^-^", AlertType.INFORMATION);
				a = true;
			}
		}

		return true;

	}

	@Override
	public void TextFieldEndScore(int endScore, Parent root) {
		// TODO Auto-generated method stub
		TextField txtScore = (TextField) root.lookup("#endScore");
		txtScore.setText(Integer.toString(endScore));

	}

	@Override
	public void playTetris() throws Exception {
		// TODO Auto-generated method stub
		Tetris tet = new Tetris();
		tet.start(new Stage());
	}

	@Override
	public void playPoop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSpace() throws Exception {
		// TODO Auto-generated method stub
		SpaceInvaders space = new SpaceInvaders();
		space.start(new Stage());
	}

	@Override
	public void playBlock() {
		// TODO Auto-generated method stub
		Breakout.run();
		
	}

}