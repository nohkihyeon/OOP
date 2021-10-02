/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * SoldState.java
 * 상태 객체
 * @author 김상진
 */
public class SoldState implements GumballState {
	private static final SoldState instance = new SoldState();
	private SoldState(){}
	public static SoldState getInstance(){
		return instance;
	}
	@Override
	public void insertCoin(GumballMachine gumballMachine) {
		System.out.println("동전을 투입할 수 있는 단계가 아님");
	}

	@Override
	public void ejectCoin(GumballMachine gumballMachine) {
		System.out.println("동전이 없음");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		System.out.println("이미 손잡이를 돌렸음");
	}

	@Override
	public void dispense(GumballMachine gumballMachine) {
		System.out.println("껌볼이 나옴");
		gumballMachine.dispense();
		if(gumballMachine.isEmpty()){
			System.out.println("껌볼이 더 이상 없습니다.");
			gumballMachine.changeStateTo(SoldOutState.getInstance());
		}
		else{
			gumballMachine.changeStateTo(NoCoinState.getInstance());
		}
	}

}
