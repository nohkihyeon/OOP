/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * ChildrensPrice: 아동 영화 대여금과 적립금 
 * @author 김상진 
 */
public class ChildrensPrice implements Price {
	@Override
	public int getCharge(int daysRented) {
		int price = 1500;
		if(daysRented>3) price += (daysRented-3)*1500;
		return price;
	}
}
