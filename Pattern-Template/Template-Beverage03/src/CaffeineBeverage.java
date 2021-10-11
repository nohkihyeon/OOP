/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제  
 * @file CaffeineBeverage.java: 음료
 */
public abstract class CaffeineBeverage {
	public final void prepareRecipe(){
		boilWater();
		brew();
		pourInCup();
		if(customerWantsCondiments())
			addCondiment();
	}
	private void boilWater(){
		System.out.println("물을 끓임");
	}
	protected abstract void brew();
	private void pourInCup(){
		System.out.println("컵에 따르다");
	}
	protected abstract void addCondiment();
	protected boolean customerWantsCondiments(){
		return true;
	}
}
