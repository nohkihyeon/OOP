/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진
 * 상태 패턴
 * GumballState.java
 * 상태 열거형
 * State Driven Transition (상태 기반 전이)
 * 열거형으로 상태 객체들을 정의. 한 자바 파일에 모든 상태 구현.
 * 상태 객체 간의 coupling tight해지는 단점이 있지만 
 * 열거형으로 구현되기 때문에 이것이 문제되지 않음
 */
public enum GumballState{
	HASCOINSTATE {
		@Override
		public void insertCoin(GumballMachine gMachine) {
			System.out.println("이미 동전이 있음");
		}
		
		@Override
		public void ejectCoin(GumballMachine gMachine) {
			System.out.println("취소되었음");
			gMachine.changeState(NOCOINSTATE);
		}

		@Override
		public void turnCrank(GumballMachine gMachine) {
			System.out.println("손잡이를 돌렸음");
			gMachine.changeState(SOLDSTATE);
		}
		@Override
		public void dispense(GumballMachine gMachine) {
			System.out.println("손잡이를 돌려야 껌볼이 나옴");
		}
	},
	NOCOINSTATE {
		@Override
		public void insertCoin(GumballMachine gMachine) {
			System.out.println("동전이 삽입되었음");
			gMachine.changeState(HASCOINSTATE);
		}
		
		@Override
		public void ejectCoin(GumballMachine gMachine) {
			System.out.println("반환할 동전 없음");
		}

		@Override
		public void turnCrank(GumballMachine gMachine) {
			System.out.println("동전이 없어 손잡이를 돌릴 수 없음");
		}

		@Override
		public void dispense(GumballMachine gMachine) {
			System.out.println("동전을 투입해야 구입할 수 있음");
		}
	},
	SOLDSTATE {	
		@Override
		public void insertCoin(GumballMachine gMachine) {
			System.out.println("동전을 투입할 수 있는 단계가 아님");
		}

		@Override
		public void ejectCoin(GumballMachine gMachine) {
			System.out.println("반환할 동전이 없음");
		}

		@Override
		public void turnCrank(GumballMachine gMachine) {
			System.out.println("이미 손잡이를 돌렸음");
		}

		@Override
		public void dispense(GumballMachine gMachine) {
			gMachine.dispense();
			System.out.println("껌볼이 나옴");
			if(gMachine.isEmpty()){
				gMachine.changeState(SOLDOUTSTATE);
			}
			else{			
				gMachine.changeState(NOCOINSTATE);
			}
		}
	},
	SOLDOUTSTATE{
		@Override
		public void insertCoin(GumballMachine machine) {
			System.out.println("껌볼이 없어 판매가 중단됨, 동전 삽입 불가");
		}

		@Override
		public void ejectCoin(GumballMachine machine) {
			System.out.println("껌볼이 없어 판매가 중단됨, 동전 없음");
		}

		@Override
		public void turnCrank(GumballMachine machine) {
			System.out.println("껌볼이 없어 판매가 중단됨, 손잡이를 돌릴 수 없음");
		}

		@Override
		public void dispense(GumballMachine machine) {
			System.out.println("껌볼이 없어 판매가 중단됨");
		}
		
	};
	public void insertCoin(GumballMachine gMachine) {}
	public void ejectCoin(GumballMachine gMachine) {}
	public void turnCrank(GumballMachine gMachine) {}
	public void dispense(GumballMachine gMachine) {}
}
