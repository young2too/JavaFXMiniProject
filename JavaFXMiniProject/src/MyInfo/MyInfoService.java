package MyInfo;

import javafx.scene.Parent;

public interface MyInfoService {
	abstract void setLabelsToMyInfo(Parent myInfoPage);
	
	abstract void setMyInfo();
	abstract void openUpdateMyInfoForm();
}
