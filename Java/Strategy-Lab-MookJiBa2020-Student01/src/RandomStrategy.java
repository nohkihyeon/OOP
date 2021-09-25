import java.util.concurrent.ThreadLocalRandom;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * RandomStrategy.java: 구체적인 전략 클래스
 * @author 노기현 
 */
public class RandomStrategy implements PlayingStrategy {
	@Override
	public HandType computeNextHand(HandType lastUserHand, boolean isUserAttack) {
		return HandType.valueOf(ThreadLocalRandom.current().nextInt(3));
	}
}
