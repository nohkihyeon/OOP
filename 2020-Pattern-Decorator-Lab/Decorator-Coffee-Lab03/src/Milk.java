/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * @file Milk.java
 * 장식패턴에서 구체적인 장식자 클래스
 * 크림 첨가물
 */
public class Milk extends CondimentDecorator {
	private Beverage beverage;
	public Milk(Beverage beverage){
		//if(beverage.getDescription().contains("우유"))
		//	throw new IllegalArgumentException("우유는 두 번 첨가할 수 없음");
		Beverage next = beverage;
		while(next!=null) {
			if(next.getClass()==getClass()) throw new IllegalArgumentException("우유는 두 번 첨가할 수 없음");
			next = next.getBeverage();
		}
		this.beverage = beverage;
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", 우유";
	}
	@Override
	public int cost() {
		return beverage.cost()+500;
	}
	public Beverage getBeverage() {
		return beverage;
	}
}
