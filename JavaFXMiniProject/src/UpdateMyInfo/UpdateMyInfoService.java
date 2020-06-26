package UpdateMyInfo;

import javafx.scene.Parent;

public interface UpdateMyInfoService {
	abstract void setCheckBoxListener(Parent updateMyInfoForm);

	abstract void setTextFieldToMyInfo(Parent updateMyInfoForm);
	
	abstract void registerToDB(Parent updateMyInfoForm);

	abstract void overlapCheck(Parent updateMyInfoForm);
	abstract void overlapNickCheck(Parent updateMyInfoForm);
}
