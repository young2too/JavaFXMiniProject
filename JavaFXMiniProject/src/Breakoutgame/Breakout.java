package Breakoutgame;

import javax.swing.JFrame;

import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import GameSelect.GameSelectImpl;
import GameSelect.GameSelectService;

import java.awt.EventQueue;

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

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
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