import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * GameView.java: 부모 클래스: javafx.scene.layout.BorderPane
 * View와 Controller가 결합되어 있는 형태
 * 사용자에게 보여지는 부분과 사용자의 입력 처리
 * @author 노기현 
 */
public class GameView extends BorderPane{
	private GameModel gameModel = new GameModel();
	private RadioButton mookButton = new RadioButton("묵");
	private RadioButton jiButton = new RadioButton("찌");
	private RadioButton BaButton = new RadioButton("빠");
	private Button newgameButton = new Button("새 게임");
	private Button doneButton = new Button("선택");
	private ImageView userView = new ImageView();
	private ImageView compView = new ImageView();
	private TextField gameStatus = new TextField();
	private SequentialTransition handEffect = new SequentialTransition();
	
	// 사용자가 선택한 손과 컴퓨터가 선택한 손을 가지고 묵지바를 진행
	private void playMookJiBa() {	
		if(gameModel.playMookJiBa()==GameResult.DRAW) {
			if(gameModel.isUserAttack()) gameStatus.setText("사용자 공격 차례");
			else gameStatus.setText("컴퓨터 공격 차례");
		}
		else {
			if(gameModel.isUserAttack()) gameStatus.setText("사용자 승");
			else gameStatus.setText("컴퓨터 승");
			newgameButton.setDisable(false);
		} 
	}
	
	// 사용자가 선택한 손과 컴퓨터가 선택한 손을 가지고 가위바위보를 진행
	private void playGawiBawiBo() {
		switch(gameModel.playGawiBawiBo()) {
		case USERWIN: gameStatus.setText("사용자 승: 사용자 공격 차례"); break;
		case COMPUTERWIN: gameStatus.setText("컴퓨터 승: 컴퓨터 공격 차례"); break;
		case DRAW: gameStatus.setText("비김: 가위바위보를 다시");
		}
	}
	
	// 사용자가 선택한 손에 따라 이미지 변경 및 사용자 손 정보 변경
	private void changeUserHandImage() {
		HandType nextHand = HandType.MOOK;
		
		if(mookButton.isSelected()) nextHand = HandType.MOOK;
		else if(jiButton.isSelected()) nextHand = HandType.JI;
		else nextHand = HandType.BA;
		
		gameModel.setUserHand(nextHand);
		userView.setImage(nextHand.getImage());
	}
	
	// 사용자가 선택 버튼(사용자 손을 선택한 후)을 눌렀을 때 실행되는 메소드
	// 게임의 시작은 항상 가위바위보로
	private void nextTurn() {
		doneButton.setDisable(true);
		HandType computerHand = gameModel.getComputerNextHand();
		
		// 묵지바가 순환적으로 표시되기 위한 코드
		// 최종적으로는 컴퓨터가 선택한 손이 표시
		handEffect.setOnFinished(e->{
			compView.setImage(computerHand.getImage());
			if(gameModel.playingMookJiBa()) playMookJiBa();	
			else playGawiBawiBo();
			doneButton.setDisable(false);
			doneButton.requestFocus();
		});
		handEffect.play();
	}
	
	// 화면 구성을 위한 메소
	private GridPane constructGamePaneVIew() {
		userView.setImage(HandType.MOOK.getImage());
		userView.setFitWidth(220);
		userView.setPreserveRatio(true);
		compView.setImage(HandType.MOOK.getImage());
		compView.setFitWidth(220);
		compView.setPreserveRatio(true);
		GridPane gamePane = new GridPane();
		gamePane.setPadding(new Insets(10));
		gamePane.setHgap(10);
		gamePane.setVgap(10);
		gamePane.add(new Label("사용자"), 0, 0);
		gamePane.add(new Label("컴퓨터"), 1, 0);
		gamePane.add(userView, 0, 1);
		gamePane.add(compView, 1, 1);
		return gamePane;
	}
	
	// 화면 구성을 위한 메소
	private HBox constructButtonPaneView() {
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10d));
		buttonPane.setSpacing(10d);
		buttonPane.setAlignment(Pos.CENTER);
		
		Region spacer1 = new Region();
		Region spacer2 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		buttonPane.getChildren().addAll(spacer1, mookButton, jiButton, BaButton, doneButton, spacer2, newgameButton);
		ToggleGroup userChoiceGroup = new ToggleGroup();
		userChoiceGroup.getToggles().addAll(mookButton, jiButton, BaButton);
		
		mookButton.setOnAction(e->changeUserHandImage());
		jiButton.setOnAction(e->changeUserHandImage());
		BaButton.setOnAction(e->changeUserHandImage());
		doneButton.setOnAction(e->nextTurn());
		mookButton.setSelected(true);
		newgameButton.setOnAction(e->newgame());
		newgameButton.setDisable(true);
		return buttonPane;
	}
	
	public GameView(GameModel gameModel){
		this.gameModel = gameModel;
		
		gameStatus.setEditable(false);
		gameStatus.setText("먼저 가위바위보를 하세요!!!");
		setTop(gameStatus);
		setCenter(constructGamePaneVIew());
		setBottom(constructButtonPaneView());

		PauseTransition mookEffect = new PauseTransition(Duration.millis(150));
		PauseTransition jiEffect = new PauseTransition(Duration.millis(150));
		PauseTransition baEffect = new PauseTransition(Duration.millis(150));
		mookEffect.setOnFinished(event->compView.setImage(HandType.MOOK.getImage()));
		jiEffect.setOnFinished(event->compView.setImage(HandType.JI.getImage()));
		baEffect.setOnFinished(event->compView.setImage(HandType.BA.getImage()));
		handEffect.getChildren().addAll(mookEffect,jiEffect,baEffect);
		handEffect.setCycleCount(4);
	}
	
	public void init() {
		doneButton.requestFocus();
	}
	
	private void newgame() {
		gameModel.init();
		newgameButton.setDisable(true);
		gameStatus.setText("먼저 가위바위보를 하세요!!!");
		changeUserHandImage();
	}
}
