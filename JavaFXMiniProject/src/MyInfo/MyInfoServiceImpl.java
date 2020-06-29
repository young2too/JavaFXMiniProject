package MyInfo;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyInfoServiceImpl implements MyInfoService{
	CommonService comSrv = new CommonServiceImpl();
	DataBaseService dbSrv = new DataBaseServiceImpl();
	
	@Override
	public void setMyInfo() {
		// TODO Auto-generated method stub
		//확인 버튼임
		System.out.println("DB에 내 정보 수정함");
		
	}

	@Override
	public void openUpdateMyInfoForm() {
		// TODO Auto-generated method stub
		comSrv.showWindow(new Stage(), "../UpdateMyInfo/UpdateMyInfo.fxml");
	}

	@Override
	public void setLabelsToMyInfo(Parent myInfoPage) {
		// TODO Auto-generated method stub
		System.out.println("라벨들의 정보를 내 현재 정보로 변경하는 함수 DB에서 받아옴");
		
		String id = "1";//여기는 로그인 서비스를 이용하여 아이디를 받아옴
		Member m = new Member();
		m = dbSrv.SearchMemberByID(id);
		
		Label myIDLabel = (Label)myInfoPage.lookup("#myIDLabel");
		Label myScoreLabel = (Label)myInfoPage.lookup("#myScoreLabel");
		Label myRankingLabel = (Label)myInfoPage.lookup("#myRankingLabel");
		Label myNickLabel = (Label)myInfoPage.lookup("#myNickLabel");
		
		myIDLabel.setText(m.getID());
		myScoreLabel.setText(Integer.toString(m.getScore()));
		myRankingLabel.setText(Integer.toString(dbSrv.calcRankByID(m.getID())));
		myNickLabel.setText(m.getNickName());
	}


}
