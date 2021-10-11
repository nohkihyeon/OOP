/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제  
 * @file BeverageTest.java: 커피, 차 테스트 프로그램
 */
public class BeverageTest {
	public static void testCaffeineBeverage(CaffeineBeverage beverage){
		beverage.prepareRecipe();
	}
	public static void main(String[] args) {
		testCaffeineBeverage(new Coffee());
		testCaffeineBeverage(new Tea());
	}
}
