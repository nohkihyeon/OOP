/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * @file CondimentDecorator.java
 * 장식패턴에서 장식자 추상 클래스
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	public CondimentDecorator(Beverage beverage) {
		this.beverage = beverage;
	}
	public abstract String getDescription();
	public Beverage removeCondiment() {
		return beverage;
	}
}
