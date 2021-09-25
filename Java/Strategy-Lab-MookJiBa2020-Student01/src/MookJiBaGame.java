import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * 전략 패턴 실습
 * MookJiBaGame.java: 묵지바 메인 클래스
 * @author 노기현 
 */
public class MookJiBaGame extends Application{
	private GameModel gameModel = new GameModel();
	private GameView gameView = new GameView(gameModel);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("묵찌빠 게임");
		primaryStage.setScene(new Scene(gameView));
		primaryStage.show();
		gameView.init();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
