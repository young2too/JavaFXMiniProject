package passwordUpdate;

import javafx.scene.Parent;

public interface PasswordUpdateService {
	abstract void registerToDB(Parent passwordUpdateForm);
	abstract boolean comparePW(Parent passwordUpdateForm);
}
