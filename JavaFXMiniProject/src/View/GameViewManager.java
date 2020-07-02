package View;

import java.util.Random;

import CommonService.CommonService;
import CommonService.CommonServiceImpl;
import DataBase.DataBaseService;
import DataBase.DataBaseServiceImpl;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PANG;
import model.SmallInfoLabel;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private DataBaseService db = new DataBaseServiceImpl();
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	private final int PANGSPEED = 5;
	private final int POOPSPEED = 14;
	private final int MAXPOOPSIZE = 15;
	
	private ImageView pang;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private int angle;
	private AnimationTimer gameTimer;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "View/resource/blue.jpg";
	
	
	private final static String METEOR_BROWN_IMAGE = "View/resource/chooser/poop.png";
	private final static String METEOR_GOLD_IMAGE = "View/resource/chooser/ypoop.png";
	
	private ImageView[] brownMeteors;
	private ImageView[] goldMeteors;
	Random randomPositionGenerator;
	
	
	private ImageView star;
	private SmallInfoLabel pointsLabel;
	private ImageView[] playerLifes;
	private int playerLife;
	private int points;
	private final static String GOLD_STAR_IMAGE="View/resource/chooser/ffish.png";
	
	private final static int STAR_RADIUS =12;
	private final static int PANG_RADIUS =27;
	private final static int METEOR_RADIUS =28;
	public int endscore;
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
	}

	private void createKeyListeners() {
		// TODO Auto-generated method stub
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
			}
		});
	}

	private void initializeStage() {
		// TODO Auto-generated method stub
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane,GAME_WIDTH,GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
	}
	
	public void createNewGame(PANG choosenPang) {
		createBackground();
		System.out.println(choosenPang);
		createPang(choosenPang);
		createGameElements(choosenPang);
		createGameLoop();
		gameStage.show();
	}
	
	private void createGameElements(PANG choosenPang) {
		playerLife =2;
		star = new ImageView(GOLD_STAR_IMAGE);
		setNewElementPosition(star);
		gamePane.getChildren().add(star);
		pointsLabel = new SmallInfoLabel("POINTS : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		playerLifes = new ImageView[3];
		
		for(int i=0;i<playerLifes.length;i++) {
			playerLifes[i]= new ImageView(choosenPang.getUrlLife());
			playerLifes[i].setLayoutX(455+(i*50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		
		
		brownMeteors = new ImageView[MAXPOOPSIZE];
		for(int i =0;i< brownMeteors.length;i++) {
			brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
			setNewElementPosition(brownMeteors[i]);
			gamePane.getChildren().add(brownMeteors[i]);
		}
		
		goldMeteors = new ImageView[MAXPOOPSIZE];
		for(int i =0;i< brownMeteors.length;i++) {
			goldMeteors[i] = new ImageView(METEOR_GOLD_IMAGE);
			setNewElementPosition(goldMeteors[i]);
			gamePane.getChildren().add(goldMeteors[i]);
		}
		
	}
	
	private void moveGameElements() {
		star.setLayoutY(star.getLayoutY()+5);
		for(int i=0;i<brownMeteors.length;i++) {
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY()+POOPSPEED);
			brownMeteors[i].setRotate(brownMeteors[i].getRotate()+4);
		}
		
		for(int i=0;i<goldMeteors.length;i++) {
			goldMeteors[i].setLayoutY(goldMeteors[i].getLayoutY()+POOPSPEED);
			goldMeteors[i].setRotate(goldMeteors[i].getRotate()+4);
		}
	}
	
	private void checkIfElementsAceBehindThePangAndlocate() {
		if(star.getLayoutY()>1200) {
			setNewElementPosition(star);
		}
		
		for(int i=0;i<brownMeteors.length;i++) {
			if(brownMeteors[i].getLayoutY()>900) {
				setNewElementPosition(brownMeteors[i]);
				points++;
				pointsLabel.setText("POINT : "+points);
			}
		}
		
		for(int i=0;i<goldMeteors.length;i++) {
			if(goldMeteors[i].getLayoutY()>900) {
				setNewElementPosition(goldMeteors[i]);
				points++;
				pointsLabel.setText("POINT : "+points);
			}
		}
		
	}
	//
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(480));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));
	}
	
	private void createPang(PANG choosenPang) {
		pang = new ImageView(choosenPang.getUrl());
		pang.setLayoutX(GAME_WIDTH/2);
		pang.setLayoutY(GAME_HEIGHT -120);
		gamePane.getChildren().add(pang);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				moveBackground();
				moveGameElements();
				checkIfElementsAceBehindThePangAndlocate();
				checkIfElementsCollide();
				movePang();
			}
		};
		
		gameTimer.start();
	}
	
	private void movePang() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle > -30) {
				angle-=5;
				
			}
			pang.setRotate(angle);
			if(pang.getLayoutX() > -20) {
				pang.setLayoutX(pang.getLayoutX()-PANGSPEED);
			}
		}
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(angle < 30) {
				angle +=5;
			}
			pang.setRotate(angle);
			if(pang.getLayoutX() < 522) {
				pang.setLayoutX(pang.getLayoutX()+PANGSPEED);
			}
		}
		if(!isLeftKeyPressed && !isRightKeyPressed) {
			if(angle < 0) {
				angle+=5;
			}else if(angle>0) {
				angle-=5;
			}
			pang.setRotate(angle);
		}
		if(isLeftKeyPressed && isRightKeyPressed) {
			if(angle < 0) {
				angle+=5;
			}else if(angle>0) {
				angle-=5;
			}
			pang.setRotate(angle);
		}
	}

	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for( int i =0;i<12;i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1, i%3, i/3);
			GridPane.setConstraints(backgroundImage2, i%3, i/3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
			
		}
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1,gridPane2);
	}
	
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY()+0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY()+0.5);
		
		if(gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}

		if(gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
	
	private void checkIfElementsCollide() {
		if(PANG_RADIUS+STAR_RADIUS>calculateDistance(pang.getLayoutX()+49, star.getLayoutX()+15, pang.getLayoutY()+37, star.getLayoutY()+15)) {
			setNewElementPosition(star);
			
			points++;
			String textToSet = "POINT : ";
			
			pointsLabel.setText(textToSet+points);
		}
		
		for(int i=0;i<brownMeteors.length;i++) {
			if(METEOR_RADIUS+PANG_RADIUS>calculateDistance(pang.getLayoutX()+49,	 brownMeteors[i].getLayoutX()+20, pang.getLayoutY()+37, brownMeteors[i].getLayoutY()+20)) {
				removeLife();
				setNewElementPosition(brownMeteors[i]);
			}
		}
		for(int i=0;i<goldMeteors.length;i++) {
			if(METEOR_RADIUS+PANG_RADIUS>calculateDistance(pang.getLayoutX()+49,	 goldMeteors[i].getLayoutX()+20, pang.getLayoutY()+37, goldMeteors[i].getLayoutY()+20)) {
				removeLife();
				setNewElementPosition(goldMeteors[i]);
			}
		}
		
	}
	
	private void removeLife() {
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if(playerLife<0) {
			
			gameTimer.stop();
			gamePane.getChildren().clear();
			Alert alertGameEnd = new Alert(AlertType.INFORMATION);
			alertGameEnd.setHeaderText("게임 종료!");
			alertGameEnd.setContentText("당신의 점수 : "+(points*10));
			alertGameEnd.setOnCloseRequest(e->{
				db.updateScore(points*10);
				playerLife=3;
				gameStage.close();
			});
			alertGameEnd.show();
		}
	}
	
	
	private double calculateDistance(double x1, double x2, double y1,double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
}
