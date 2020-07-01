package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
public class InfoLabel extends Label{
	public final static String FONT_PATH = "src/model/resource/BMHANNA_11YRS_TTF.TTF";
	
	public final static String BACKGROUND_IMAGE = "model/resource/yellowBtnfree.jpg"; 
	
	
	
	
	public InfoLabel(String text) {
		
		setPrefWidth(500	);
		setPrefHeight(10);
		setPadding(new javafx.geometry.Insets(10, 40, 40, 40));
		setText(text);
		setWrapText(true);
		setLabelFont();
		
		BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 300, 49, false, true),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		setBackground(new Background(backgroundImage));
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("Verdana",23));
		}
		
	}

}
