/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * DoubleSoldState.java
 * 상태 객체
 * @author 김상진
 */
public class DoubleSoldState implements GumballState {
	private GumballMachine gMachine;
	public DoubleSoldState(GumballMachine gMachine){
		this.gMachine = gMachine;
	}
	
	@Override
	public void insertCoin() {
		System.out.println("동전을 투입할 수 있는 단계가 아님");
	}

	@Override
	public void ejectCoin() {
		System.out.println("동전이 없음");

	}

	@Override
	public void turnCrank() {
		System.out.println("이미 손잡이를 돌렸음");
	}

	@Override
	public void dispense() {
		System.out.println("껌볼이 2개가 나옴");
		gMachine.dispense();
		if(gMachine.getNumberOfGumballs()>=1){
			gMachine.dispense();
		}
		if(gMachine.isEmpty()){
			System.out.println("껌볼이 더 이상 없습니다.");
			gMachine.changeToSoldOutState();
		}
		else{			
			gMachine.changeToNoCoinState();
		}
	}
}
