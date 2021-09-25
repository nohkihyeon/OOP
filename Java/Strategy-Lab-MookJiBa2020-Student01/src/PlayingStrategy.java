/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * PlayingStrategy.java: 전략 Interface
 * @author 노기현 
 */
public interface PlayingStrategy {
	HandType computeNextHand(HandType lastUserHand, boolean isUserAttack);
}
