package Rank;

import java.util.ArrayList;

import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import DataBase.Member;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class RankServiceImpl implements RankService{
	
	Label nickArr[] = new Label[5];
	Label scoreArr[] = new Label[5];
	DataBaseService dbSrv = new DataBaseServiceImpl();
	private void setArr(Parent rankPage) {
		nickArr[0] = (Label)rankPage.lookup("#nick1st");
		nickArr[1] = (Label)rankPage.lookup("#nick2nd");
		nickArr[2] = (Label)rankPage.lookup("#nick3rd");
		nickArr[3] = (Label)rankPage.lookup("#nick4th");
		nickArr[4] = (Label)rankPage.lookup("#nick5th");
		
		scoreArr[0] = (Label)rankPage.lookup("#score1st");
		scoreArr[1] = (Label)rankPage.lookup("#score2nd");
		scoreArr[2] = (Label)rankPage.lookup("#score3rd");
		scoreArr[3] = (Label)rankPage.lookup("#score4th");
		scoreArr[4] = (Label)rankPage.lookup("#score5th");
		
	}
	
	@Override
	public void setRankLabels(Parent rankPage) {
		// TODO Auto-generated method stub
		
		setArr(rankPage);
		
		ArrayList<Member> members = dbSrv.select();
		int size = 5;
		if(members.size()<size) {
			size = members.size();
		}
		for(int i=0;i<size;i++) {
			nickArr[i].setText(members.get(i).getNickName());
			scoreArr[i].setText(Integer.toString(members.get(i).getScore()));
		}
		
	}

}
