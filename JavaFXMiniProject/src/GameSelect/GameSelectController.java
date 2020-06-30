package GameSelect;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class GameSelectController extends Controller implements Initializable{

	private Parent root;
	private CommonService comSrv;
	private GameSelectService gameSrv;
	private DataBaseService dbSrv;

	
	Button tetrisBtn;
	Button poopBtn;
	Button blockBtn;
	Button spaceBtn;
	Button myInfoBtn;
	Button rankBtn;
	Button backBtn;
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
		backBtn = (Button)root.lookup("#backBtn");
		comSrv.setMouserBtnCursurEffect(backBtn);
		tetrisBtn = (Button)root.lookup("#tetrisBtn");
		comSrv.setMouserBtnCursurEffect(tetrisBtn);
		poopBtn = (Button)root.lookup("#poopBtn");
		comSrv.setMouserBtnCursurEffect(poopBtn);
		blockBtn=(Button)root.lookup("#blockBtn");
		comSrv.setMouserBtnCursurEffect(blockBtn);
		spaceBtn=(Button)root.lookup("#spaceBtn");
		comSrv.setMouserBtnCursurEffect(spaceBtn);
		myInfoBtn=(Button)root.lookup("#myInfoBtn");
		comSrv.setMouserBtnCursurEffect(myInfoBtn);
		rankBtn=(Button)root.lookup("#rankBtn");
		comSrv.setMouserBtnCursurEffect(rankBtn);
		backBtn=(Button)root.lookup("#backBtn");
		comSrv.setMouserBtnCursurEffect(backBtn);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		gameSrv = new GameSelectImpl();
		dbSrv = new DataBaseServiceImpl();
		
	}
	
	public void OpenRankForm() {
		System.out.println("랭킹 화면 ");
		Parent form = gameSrv.OpenRankForm();
		//랭킹창 띄우는 작업
	}
	
	public void OpenMyInfoForm() {
		System.out.println("내 정보 화면");
		Parent form = gameSrv.OpenMyInfoForm();
		//내정보 띄우는 작업
	}
	
	public void CancleProc(ActionEvent event) {
		//되돌아가는 작업
		System.out.println("되돌아가기(=선택메뉴 창 닫기)");
		comSrv.WindowClose(event);
	}
	
	public void SelectProc() {
		//게임 선택하는 작업

		Button tetrisBtn = (Button)root.lookup("#tetrisBtn");
		Button poopBtn = (Button)root.lookup("#poopBtn");
		Button blockBtn = (Button)root.lookup("#blockBtn");
		Button spaceBtn = (Button)root.lookup("#spaceBtn");
		List<Button> btnList = new ArrayList<Button>();
		btnList.add(tetrisBtn);
		btnList.add(poopBtn);
		btnList.add(blockBtn);
		btnList.add(spaceBtn);
		//게임이 끝난 값을 불러와서 endScore자리에 넣어준다.
		gameSrv.TextFieldEndScore(120,root);
		gameSrv.DisableGame(btnList, 120);

		
		
		
	}
}

