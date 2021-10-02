/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * NoCoinState.java
 * 상태 객체
 * @author 김상진
 */
public class NoCoinState implements GumballState {
	@Override
	public boolean insertCoin() {
		System.out.println("동전이 삽입되었음");
		return true;
	}

	@Override
	public boolean ejectCoin() {
		System.out.println("반환할 동전 없음");
		return false;
	}

	@Override
	public boolean turnCrank() {
		System.out.println("동전이 없어 손잡이를 돌릴 수 없음");
		return false;
	}

	@Override
	public boolean dispense() {
		System.out.println("동전을 투입해야 구입할 수 있음");
		return false;
	}
}
