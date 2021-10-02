/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * Context driven transition (문맥 기반 전이) 
 * 장점. 상태 객체에 문맥을 전달할 필요가 없음
 * 장점. 상태 객체들을 공유할 수 있는 장점이 있음
 * 장점. 상태를 전이하기 위한 getter와 setter가 필요 없음
 * 문맥 객체는 상태 기반 전이 방식보다 복잡해짐
 * 이유. 한 상태에서 상황에 따라 여러 상태로 전이될 경우에는 문맥 기반 전이는 구현이 번거로울 수 있음
 * 이유. 관련하여 조건문이 많아질 수 있음 
 * 이 버전은 상태 객체에서 상태 전이가 필요한지 여부를 boolean 값을 통해 알려줌
 * @author 김상진
 */
public class GumballMachine {
	// private final GumballState soldOutState = new SoldOutState(this);
	private static final GumballState soldOutState = new SoldOutState();
	private static final GumballState soldState = new SoldState();
	private static final GumballState noCoinState = new NoCoinState();
	private static final GumballState hasCoinState = new HasCoinState();
	private GumballState currentState;
	private int count = 0;
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){	
		if(currentState.insertCoin()) currentState = hasCoinState;
	}
	public void ejectCoin(){	
		if(currentState.ejectCoin()) currentState = noCoinState;
	}
	public void turnCrank(){	
		if(currentState.turnCrank()) {
			currentState = soldState;
			if(currentState.dispense()){
				dispense();
				if(count==0){
					System.out.println("껌볼이 더 이상 없습니다.");
					currentState = soldOutState;
				}
				else{			
					currentState = noCoinState;
				}
			}
		}
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public void dispense(){
		if(count>0) --count;
		System.out.println(count);
	}
}
