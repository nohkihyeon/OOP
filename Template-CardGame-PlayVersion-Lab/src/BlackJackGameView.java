import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file BlackJackGameView.java 
 * 탬플릿 메소드 패턴
 * 블랙잭 게임을 진행하는 뷰
 * 딜러 전략 (새로운 방식)
 * 기존의 방식에서 딜러의 패의 합이 17미만이면 계속 hit하는 전략에서 추가로 ase카드의 갯수를 1개 이상일 때 hit를 계속하고
 * ase의 갯수와 무관하게 딜러의 패의 합이 18미만이면 hit하는 전략이다.
 * 에이스가 나오면 11점 또는 1점에 점수를 가져갈 수 있기 때문에 유리해지고,
 * 또한 블랙잭의 경우의 수에 해당하기 때문에 이러한 전략을 구상하게 되었다.
 */
public class BlackJackGameView extends Stage {
	private TilePane userPane = new TilePane();
	private TilePane dealerPane = new TilePane();
	private Button hitButton = new Button("Hit");
	private static int PREFCARDWIDTH = 100;
	private CardGame cardGame;
	private int numberOfPlayers;
	private BlackJackPlayerHand userHand;
	private BlackJackPlayerHand dealerHand;

	public BlackJackGameView(CardGame cardGame, int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		this.cardGame = cardGame;
		doLayout();
		newGame();
	}
	private void newGame() {
		cardGame.init(numberOfPlayers);
		var cards = cardGame.getUserCards();
		this.userHand = new BlackJackPlayerHand(cards.get(0));
		this.dealerHand = new BlackJackPlayerHand(cards.get(1));
		dealUserCards();
		dealComputerCards();
		hitButton.setDisable(false);
	}
	private ImageView getCardImageView(Card card) {
		String fileName = ""+
				card.getNumber()+
				card.getFace().toString().charAt(0)+".png";
		Image cardImage = new Image("/image/"+fileName);
		ImageView cardView = new ImageView(cardImage);
		cardView.setFitWidth(PREFCARDWIDTH);
		cardView.setPreserveRatio(true);
		return cardView;
	}
	private void dealUserCards() {
		userPane.getChildren().clear();
		for(Card card: userHand.getCards())
			userPane.getChildren().add(getCardImageView(card));
	}
	private void dealComputerCards() {
		dealerPane.getChildren().clear();
		ArrayList<Card> dealerCards = dealerHand.getCards();
		for(int i=0; i<dealerCards.size()-1; i++)
			dealerPane.getChildren().add(getCardImageView(dealerCards.get(i)));
		ImageView backView = new ImageView(new Image("/image/blue_back.png"));
		backView.setFitWidth(PREFCARDWIDTH);
		backView.setPreserveRatio(true);
		dealerPane.getChildren().add(backView);
	}
	
	private void doHit() {
		Card card = cardGame.getCard();
		userHand.addCard(card);
		userPane.getChildren().add(getCardImageView(card));
		if(userHand.getScore()>21) {
			hitButton.setDisable(true);
			doStand();
		}
	}
	private void doStand() {
		int aceCount=0;
		hitButton.setDisable(true);
		dealerPane.getChildren().clear();
		for(Card card: dealerHand.getCards())
			dealerPane.getChildren().add(getCardImageView(card));
		//개선된 방식
		while(dealerHand.getScore()<18 && !(aceCount >0)){ 
			Card card = cardGame.getCard();
			if (card.getNumber() == 1)
				aceCount++;
			dealerHand.addCard(card);
			dealerPane.getChildren().add(getCardImageView(card));
		}
		System.out.println("사용자 점수: "+userHand.getScore()+", "+userHand.isBlackJack());
		System.out.println("딜러 점수: "+dealerHand.getScore()+", "+dealerHand.isBlackJack());
		
		BlackJackGameResult result 
			= BlackJackPlayerHand.determineResult(userHand, dealerHand);
		switch(result) {
		case DRAW: System.out.println("무승부"); break;
		case USERWIN: System.out.println("사용자 승"); break;
		default: System.out.println("딜러 승"); break;
		}
	}
	public void doLayout() {
		BorderPane mainPane = new BorderPane();
		
		VBox cardPane = new VBox();
		cardPane.setAlignment(Pos.CENTER);
		cardPane.setSpacing(10d);
		cardPane.setPadding(new Insets(10d));
		cardPane.getChildren().addAll(userPane, dealerPane);
		
		Button standButton = new Button("Stand");
		Button newGameButton = new Button("new");
		hitButton.setOnAction(e->doHit());
		standButton.setOnAction(e->doStand());
		newGameButton.setOnAction(e->newGame());
		
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setSpacing(10d);
		buttonPane.setPadding(new Insets(10d));
		buttonPane.getChildren().addAll(hitButton, standButton, newGameButton);
		
		mainPane.setCenter(cardPane);
		mainPane.setBottom(buttonPane);
		setScene(new Scene(mainPane));
	}
}
