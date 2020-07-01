package Breakoutgame;

import javax.swing.JFrame;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import CommonService.Controller;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.awt.EventQueue;
import java.io.IOException;

public class Breakout extends JFrame {

	DataBaseService dbSrv = new DataBaseServiceImpl();
	CommonService comSrv = new CommonServiceImpl();
	Board game = new Board();
	public Breakout() {
		initUI();
	}

	public void initUI() {
		
		add(game);
		setTitle("Breakout");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
	}
	
	@Override
	public void dispose(){
		dbSrv.updateScore(game.getScore());
		Platform.runLater((new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				comSrv.alertWindow("게임 종료!", "점수 : "+game.getScore(), AlertType.CONFIRMATION);
				comSrv.showWindow(new Stage(), "/GameSelect/GameSelect.fxml");
			}
		}));
		super.dispose();
	}

	public static void run() {
		EventQueue.invokeLater(() -> {
			Breakout game = new Breakout();
			game.setVisible(true);
		});
	}

}