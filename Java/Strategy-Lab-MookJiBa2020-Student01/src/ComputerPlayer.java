import java.util.Objects;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * 전략 패턴
 * ComputerPlayer.java: 전략을 활용하는 클라이언 클래스
 * @author 노기현 
 */
public class ComputerPlayer{
	private HandType hand;
	private PlayingStrategy strategy;
	private HandType lastUserHand=null;
	private boolean isUserAttack;
	public ComputerPlayer(PlayingStrategy strategy) {
		setStrategy(strategy);
	}
	public void setStrategy(PlayingStrategy strategy) {
		this.strategy = Objects.requireNonNull(strategy);
	}  // dependency injection
	public HandType getHand() {
		return hand;
	}
	
	public void setLastUserHand(HandType lastUserHand) {
		this.lastUserHand =lastUserHand;
	}
	public void setPlayingMode(boolean isUserAttack) {
		this.isUserAttack = isUserAttack;
	}
	public HandType nextHand(){
		return hand = strategy.computeNextHand(lastUserHand, isUserAttack);
	}
}
