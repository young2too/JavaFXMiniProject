package View;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.PANG;
import model.PangPicker;
import model.PoopButton;
import model.PoopSubscene;

public class ViewManager {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 1024;	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X =100;
	private final static int MENU_BUTTON_START_y =100;
	
	private PoopSubscene helpSubScene;
	
	
	private PoopSubscene pangChooserScene;
	
	private PoopSubscene scenetoHide;
	
	List<PoopButton> menuButtons;
	
	List<PangPicker> pangList;
	private PANG choosenPang;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
		
		
		
	}
	
	private void showSubScene(PoopSubscene subScene) {
		if(scenetoHide != null) {
			scenetoHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		scenetoHide = subScene;
	}
	
	private void createSubScenes() {
	
		helpSubScene = new PoopSubscene();
		mainPane.getChildren().add(helpSubScene);
		
		
		
		createPangChooserSubScene();
	}
	
	private void createPangChooserSubScene() {
		// TODO Auto-generated method stub
		pangChooserScene = new PoopSubscene();
		mainPane.getChildren().add(pangChooserScene);
		
		InfoLabel choosePangLabel = new InfoLabel("CHOOSE YOUR PANG");
		choosePangLabel.setLayoutX(110);
		choosePangLabel.setLayoutY(25);
		pangChooserScene.getPane().getChildren().add(choosePangLabel);
		pangChooserScene.getPane().getChildren().add(createPangToChoose());
		pangChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createPangToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		pangList = new ArrayList<>();
		for(PANG pang : PANG.values()) {
			PangPicker pangToPick = new PangPicker(pang);
			pangList.add(pangToPick);
			box.getChildren().add(pangToPick);
			pangToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(PangPicker pang : pangList) {
						pang.setIsCircleChoosen(false);
					}
					
					pangToPick.setIsCircleChoosen(true);
					choosenPang = pangToPick.getPang();
				}
			});
		}
		box.setLayoutX(270-(118*2));
		box.setLayoutY(100);
		return box;
	}
	
	private PoopButton createButtonToStart() {
		PoopButton startButton = new PoopButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(choosenPang != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenPang);
				}
			}
		});
		
		return startButton;
		
	}

	public Stage getMainStage() {
		return mainStage;
	}

	private void addMenuButton(PoopButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_y + menuButtons.size() *100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	
	private void createButtons() {
		createStartButton();
		createHelpButton();
		createExitButton();
	}
	
	private void createStartButton() {
		PoopButton startButton = new PoopButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showSubScene(pangChooserScene);
			}
		});
		
	}

	
	private void createHelpButton() {
		PoopButton helpButton = new PoopButton("HELP");
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showSubScene(helpSubScene);
			}
		});
	}
	
	
	
	private void createExitButton() {
		PoopButton exitButton = new PoopButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				mainStage.close();
			}
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("View/resource/blue.jpg",300,300,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("View/resource/name.png");
		logo.setLayoutX(400);
		logo.setLayoutY(20);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				logo.setEffect(null);
			}
			
		});
		
		mainPane.getChildren().add(logo);
	}
}
