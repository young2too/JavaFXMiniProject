package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PangPicker extends VBox{
	
	private ImageView circleImage;
	private ImageView pangImge;
	
private String circleNotChoosen="View/resource/chooser/life.png";
private String circleChoosen="View/resource//chooser/life.png";
 
private PANG pang;

private boolean isCircleChoosen;

public PangPicker(PANG pang) {
	circleImage = new ImageView(circleNotChoosen);
	pangImge = new ImageView(pang.getUrl());
	this.pang = pang;
	isCircleChoosen = false;
	this.setAlignment(Pos.CENTER);
	this.setSpacing(20);
	this.getChildren().add(circleImage);
	this.getChildren().add(pangImge);
	
}

public PANG getPang() {
	return pang;
	
}
	
public boolean getCircleChoosen() {
	return isCircleChoosen;
}

public void setIsCircleChoosen(boolean isCircleChoosen) {
	this.isCircleChoosen = isCircleChoosen;
	String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
	circleImage.setImage(new Image(imageToSet));
	
}
}
