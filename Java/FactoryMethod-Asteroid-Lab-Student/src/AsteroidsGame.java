import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidsGame.java: Asteroid 게임
 * Asteroid 게임 중 소행성 등장하는 부분만 구현
 */
public class AsteroidsGame extends Application {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int MAXLEVEL = 10;
	// GUI Nodes
	private Label levelText = new Label("레벨");
	private Spinner<Number> gameLevel = new Spinner<>(1, MAXLEVEL, 1);
	private Button generateAsteroidButton = new Button("소행성 생성");
	private Button stopGenerationButton = new Button("중단");
	private Pane space = new Pane();
	// 게임 로직
	private Timeline asteroidGenerationTimeline = new Timeline();
	private AsteroidFactory asteroidFactory 
		//= new AsteroidDiamondFactory();
		= new AsteroidRectangleFactory();
		//= new AsteroidPolygonFactory();
	
	private void moveAsteroid(Asteroid asteroid) {
		Location start = asteroid.getStartLoc();
		Location dest = asteroid.getDestLoc();
		TranslateTransition asteriodTransition = 
			new TranslateTransition(Duration.millis(asteroid.getSpeed()),asteroid);
		asteriodTransition.setByX(dest.getX()-start.getX());
		asteriodTransition.setByY(dest.getY()-start.getY());
		asteriodTransition.setCycleCount(1);
		asteriodTransition.setAutoReverse(false);
		asteriodTransition.play();
		asteriodTransition.setOnFinished(e->{
			asteroid.setTranslateX(0);
			asteroid.setTranslateY(0);					
			space.getChildren().remove(asteroid);
		});
	}
	
	private void createAsteroid() {
		Asteroid asteroid 
			= asteroidFactory.createAsteroid((Integer)gameLevel.getValue());	
		space.getChildren().add(asteroid);
		moveAsteroid(asteroid);
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox statePane = new HBox();
		statePane.setSpacing(10);
		statePane.setPadding(new Insets(10));
		statePane.getChildren().addAll(levelText,gameLevel,
			generateAsteroidButton,stopGenerationButton);
		stopGenerationButton.setOnAction(e->asteroidGenerationTimeline.stop());
		generateAsteroidButton.setOnAction(e->asteroidGenerationTimeline.play());
				
		space.setBackground(
			new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		UserShip usership = new UserShip(240,240);
		space.getChildren().add(usership);
				
		mainPane.setTop(statePane);
		mainPane.setCenter(space);
		
		mainStage.setTitle("KoreaTech Asteroid");
		mainStage.setScene(new Scene(mainPane,500,500));
		mainStage.setResizable(false);
		mainStage.show();			
		
		// 1.5초마다 소행성 생성
		asteroidGenerationTimeline.getKeyFrames().add(
			new KeyFrame(Duration.millis(1500),e->createAsteroid()));
		asteroidGenerationTimeline.setCycleCount(Animation.INDEFINITE);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
