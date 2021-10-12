import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file BlackJackTest.java 
 * 탬플릿 메소드 패턴
 * 블랙잭 게임에서 합계 계산과 승패 결과 판단 메소드 단위 테스트 프로그램 
 */
class BlackJackTest {

	@Test
	void computeScoretest01() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(1,CardFace.SPADES));
		userCards.add(new Card(11,CardFace.SPADES));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		assertEquals(userHand.getScore(),21);
		assertTrue(userHand.isBlackJack());
	
		userHand.addCard(new Card(1,CardFace.DIAMONDS));
		assertEquals(userHand.getScore(),12);
		assertFalse(userHand.isBlackJack());
		userHand.addCard(new Card(8,CardFace.CLUBS));
		assertEquals(userHand.getScore(),20);
		assertFalse(userHand.isBlackJack());
	}

	void computeScoretest02() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(2,CardFace.SPADES));
		userCards.add(new Card(13,CardFace.SPADES));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		assertEquals(userHand.getScore(),12);
		assertFalse(userHand.isBlackJack());
	
		userHand.addCard(new Card(1,CardFace.DIAMONDS));
		userHand.addCard(new Card(1,CardFace.CLUBS));
		assertEquals(userHand.getScore(),14);
		assertFalse(userHand.isBlackJack());		
	}
	
	void determineResultTest01() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(2,CardFace.SPADES));
		userCards.add(new Card(13,CardFace.SPADES));
		userCards.add(new Card(12,CardFace.DIAMONDS));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		
		ArrayList<Card> dealerCards = new ArrayList<>();
		dealerCards.add(new Card(5,CardFace.SPADES));
		dealerCards.add(new Card(10,CardFace.SPADES));
		dealerCards.add(new Card(7,CardFace.SPADES));
		BlackJackPlayerHand dealerHand = new BlackJackPlayerHand(dealerCards);
		
		assertEquals(BlackJackGameResult.DRAW, 
				BlackJackPlayerHand.determineResult(userHand, dealerHand));		
	}
	
	void determineResultTest02() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(1,CardFace.SPADES));
		userCards.add(new Card(13,CardFace.SPADES));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		
		ArrayList<Card> dealerCards = new ArrayList<>();
		dealerCards.add(new Card(1,CardFace.HEARTS));
		dealerCards.add(new Card(11,CardFace.DIAMONDS));
		BlackJackPlayerHand dealerHand = new BlackJackPlayerHand(dealerCards);
		
		assertEquals(BlackJackGameResult.DRAW, 
				BlackJackPlayerHand.determineResult(userHand, dealerHand));		
	}
	
	void determineResultTest03() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(5,CardFace.SPADES));
		userCards.add(new Card(13,CardFace.SPADES));
		userCards.add(new Card(4,CardFace.DIAMONDS));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		
		ArrayList<Card> dealerCards = new ArrayList<>();
		dealerCards.add(new Card(9,CardFace.SPADES));
		dealerCards.add(new Card(10,CardFace.HEARTS));
		BlackJackPlayerHand dealerHand = new BlackJackPlayerHand(dealerCards);
		
		assertEquals(BlackJackGameResult.USERLOST, 
				BlackJackPlayerHand.determineResult(userHand, dealerHand));		
	}
	
	void determineResultTest04() {
		ArrayList<Card> userCards = new ArrayList<>();
		userCards.add(new Card(1,CardFace.SPADES));
		userCards.add(new Card(13,CardFace.SPADES));
		userCards.add(new Card(3,CardFace.DIAMONDS));
		userCards.add(new Card(5,CardFace.HEARTS));
		userCards.add(new Card(1,CardFace.HEARTS));
		BlackJackPlayerHand userHand = new BlackJackPlayerHand(userCards);
		
		ArrayList<Card> dealerCards = new ArrayList<>();
		dealerCards.add(new Card(9,CardFace.SPADES));
		dealerCards.add(new Card(10,CardFace.HEARTS));
		BlackJackPlayerHand dealerHand = new BlackJackPlayerHand(dealerCards);
		
		assertEquals(BlackJackGameResult.USERWIN, 
				BlackJackPlayerHand.determineResult(userHand, dealerHand));		
	}
}
