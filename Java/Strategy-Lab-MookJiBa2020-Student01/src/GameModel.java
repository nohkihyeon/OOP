/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * GameModel.java: 
 * 묵찌바 게임에 필요한 데이터와 게임논리 구현
 * @author 노기현 
 */
public class GameModel {
	private ComputerPlayer computer 
//		= new ComputerPlayer(new HowToWinStrategy());
		= new ComputerPlayer(new lastHandBaseStrategy());
//		= new ComputerPlayer(new RandomStrategy());
	private HandType userHand = HandType.MOOK;
	private boolean playingMookJiBa = false;
	private boolean isUserAttack = false;
	
	// 새 게임을 할 때마다 객체를 생성하는 대신 사용 (상태 초기화)
	public void init() {
		playingMookJiBa = false;
		isUserAttack = false;
	}
	
	// 기본 Getter
	public HandType getUserHand() {
		return userHand;
	}
	
	public boolean isUserAttack() {
		return isUserAttack;
	}
	
	public boolean playingMookJiBa() {
		return playingMookJiBa;
	}
	
	public void setUserHand(HandType userHand) {
		this.userHand = userHand;
	}
	
	// 다음 컴퓨터 손 계산함
	public HandType getComputerNextHand() {
		return computer.nextHand();
	}
	
	/**
	 * userHand와 computer.getHand()의 값을 비교하여 묵찌바의 결과를 반환함
	 * 이때 두 값이 같으면 isUserAttack 값에 따라 승자가 결정되며,
	 * 두 값이 다르면 isUserAttack 값이 적절하게 변경되어야 
	 * 승자가 있으면 playingMookJiBa 값을 false로 설정해야 함
	 * @return 묵찌바의 결과를 반환함
	 */ 

	public GameResult playMookJiBa() {
		  if(userHand==computer.getHand()) {
		   playingMookJiBa = false;
		   return isUserAttack? GameResult.USERWIN: GameResult.COMPUTERWIN; 
		  }
		  computer.setLastUserHand(userHand);
		  isUserAttack = userHand.winValueOf() != computer.getHand();
		  computer.setPlayingMode(isUserAttack);
		  isUserAttack = computer.getHand().winValueOf()==userHand;
		  return GameResult.DRAW;
		}
	/**
	 * userHand와 computer.getHand()의 값을 비교하여 가위바위보의 결과를 반환함
	 * 승자가 있으면 playingMookJiBa 값을 true로 설정해야 함
	 * @return 묵찌바의 결과를 반환함
	 */ 

	public GameResult playGawiBawiBo() {
		  if(userHand==computer.getHand()) return GameResult.DRAW;
		  isUserAttack = computer.getHand().winValueOf()==userHand;
		  computer.setLastUserHand(userHand);
		  computer.setPlayingMode(isUserAttack);
		  playingMookJiBa = true;
		  return isUserAttack? GameResult.USERWIN: GameResult.COMPUTERWIN; 
		}
}
