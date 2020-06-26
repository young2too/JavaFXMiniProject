import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{
	public static void main(String[] args) {
		System.out.println("미니 프로젝트 GIT입니다");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 각 FXML 파일은 해당 패키지에 넣고
		 * FXML파일의 컨트롤러도 해당 패키지에 넣습니다
		 * 컨트롤러는 CommonService내부의 controller을 상속받고
		 * 자신이 맡은 창의 기능들을 구현합시다
		 * 이미지는 모두 img 폴더 경로에 있으니 ImageView의 이미지 경로는
		 * 이쪽으로 설정해 주시면 됩니다
		 * 각 창마다 패키지를 만들어 두었으니
		 * 각 창에 대한 Service, Impl, controller 구현은
		 * 해당 패키지 내부에서 진행하시면 최대한
		 * 충돌이 덜 날 겁니다
		 */
	}
}
