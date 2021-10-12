import java.util.ArrayList;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file BlackJackPlayerHand.java 
 * 탬플릿 메소드 패턴
 * 블랙잭 게임에서 각 플레이어의 패 정보 유지
 */
public class BlackJackPlayerHand {
	private ArrayList<Card> cards;
	private int score = 0;
	private boolean isBlackJack = false;
	public BlackJackPlayerHand(ArrayList<Card> cards) {
		this.cards = cards;
		score = computeScore();
	}
	public void init() {
		cards.clear();
		score = 0;
		isBlackJack = false;
	}
	public ArrayList<Card> getCards(){
		return cards;
	}
	public void addCard(Card card) {
		cards.add(card);
		score = computeScore();
	}
	public int getScore() {
		return score;
	}
	public boolean isBlackJack() {
		return isBlackJack;
	}
	// 완성해야 하는 메소드
	private int computeScore() {
		score =0;
		int aceCount =0;
		int qkjCount =0;
		for (int i=0; i < cards.size(); i++) {
			// king queen jack 인경우 10점을 더한다.
			if (cards.get(i).getNumber() > 10) {
				score +=10;
				qkjCount++;
			}
			// ase인 경우 
			else if (cards.get(i).getNumber() == 1) {
				score +=1;
				aceCount++;
			}
			// 
			else if (cards.get(i).getNumber() ==1 && score +10 <=21) {
				System.out.println("ase 10점 더 더해서 11점 더함!");
				score += 10;
			}
			// 나머지 카드인 경우
			else
				score += cards.get(i).getNumber();
		}
		// ase를 가지고있고 10점을 더했을때 21점이하이면 10점을 더 더한다.(결국 11점을 더하게된다.)
		for (int i =0; i < cards.size(); i++) {
			if (cards.get(i).getNumber() == 1 && score +10 <= 21)
				score += 10;
		}
		// blackjack을 판별
		if (qkjCount >=1 && aceCount >=1 && score ==21)
			isBlackJack = true;
		else
			isBlackJack = false;
		
		return score;
	}
	// 점수의 경우의수를 나누어서 각 경우에 맞게 결과를 판정
	public static BlackJackGameResult determineResult(
		BlackJackPlayerHand userHand, BlackJackPlayerHand dealerHand) {
		if (userHand.getScore() > 21 && dealerHand.getScore() > 21)
			return BlackJackGameResult.DRAW;
		else if (userHand.getScore() > 21)
			return BlackJackGameResult.USERLOST;
		else if (dealerHand.getScore() > 21)
			return BlackJackGameResult.USERWIN;
		else if (userHand.getScore() <= 21 && dealerHand.getScore() <= 21) {
			int diff = userHand.getScore() - dealerHand.getScore();
			if (diff > 0)
				return BlackJackGameResult.USERWIN;
			else
				return BlackJackGameResult.USERLOST;
		}
		else if (userHand.isBlackJack && !dealerHand.isBlackJack)
			return BlackJackGameResult.USERWIN;
		else if (dealerHand.isBlackJack && !userHand.isBlackJack)
			return BlackJackGameResult.USERLOST;
		return BlackJackGameResult.DRAW;
	}
}
