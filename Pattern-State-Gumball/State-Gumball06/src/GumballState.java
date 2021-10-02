/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * GumballStateEvent.java
 * 컴볼기기들의 상태가 제공해야 하는 interface
 * State Driven Transition (상태 기반 전이)
 * @author 김상진
 */
public interface GumballState {
	void insertCoin(GumballMachine stateTransition);
	void ejectCoin(GumballMachine stateTransition);
	void turnCrank(GumballMachine stateTransition);
	void dispense(GumballMachine stateTransition);
}
