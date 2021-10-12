import java.util.ArrayList;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file BlackJackGame.java
 * 탬플릿 메소드 패턴
 * 블랙잭 게임
 */
public class BlackJackGame extends CardGame {
	public BlackJackGame() {
		super(2);
	}
	@Override
	protected void dealCards() {
		for(int i=0; i<numberOfPlayers; i++) {
			ArrayList<Card> userCard = new ArrayList<>();
			for(int j=0; j<NUMBEROFCARDSPERPLAYER; j++)
				userCard.add(remainingDeck.poll());
			userCards.add(userCard);
		}
	}
}
