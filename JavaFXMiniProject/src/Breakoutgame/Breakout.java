package Breakoutgame;

import javax.swing.JFrame;

import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import GameSelect.GameSelectImpl;
import GameSelect.GameSelectService;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

public class Breakout extends JFrame {

	DataBaseService dbSrv = new DataBaseServiceImpl();
	GameSelectService gameSrv = new GameSelectImpl();
	Board game = new Board();

	public Breakout() {
		initUI();
	}

	public void initUI() {

		add(game);
		setTitle("Breakout");
		Dimension des = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLocation((int)des.getWidth()/2-(Commons.WIDTH/2), (int)des.getHeight()/2-(Commons.HEIGHT/2));
		setResizable(false);
		pack();
	}

	@Override
	public void dispose() {
		super.dispose();
		dbSrv.updateScore(game.getScore());
	}

	public static void run() {
		EventQueue.invokeLater(() -> {
			Breakout game = new Breakout();
			game.setVisible(true);
		});
	}

}