package Rank;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class RankController extends Controller implements Initializable{
	private Parent rankPage;
	DataBaseService dbSrv;
	CommonService comSrv;
	RankService rankSrv;
	Button imgBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dbSrv = new DataBaseServiceImpl();
		comSrv = new CommonServiceImpl();
		rankSrv = new RankServiceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.rankPage = root;
		
	
		rankSrv.setRankLabels(rankPage);
		imgBtn = (Button)rankPage.lookup("#backImgBtn");
		comSrv.setMouserBtnCursurEffect(imgBtn);
	}
	
	public void backImgBtnAction() {
		comSrv.WindowClose(rankPage);
	}

}
