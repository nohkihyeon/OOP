/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * HasCoinState.java
 * 상태 객체
 * @author 김상진
 */
public class HasCoinState implements GumballState {
	private GumballMachine gMachine;
	public HasCoinState(GumballMachine gMachine){
		this.gMachine = gMachine;
	}
	
	@Override
	public void insertCoin() {
		System.out.println("이미 동전이 있음");
	}

	@Override
	public void ejectCoin() {
		System.out.println("취소되었음");
		//gMachine.setState(gMachine.getNoCoinState());
		gMachine.changeToNoCoinState();
	}

	@Override
	public void turnCrank() {
		System.out.println("손잡이를 돌렸음");
		gMachine.setState(gMachine.getSoldState());
	}

	@Override
	public void dispense() {
		System.out.println("손잡이를 돌려야 껌볼이 나옴");
	}
}
