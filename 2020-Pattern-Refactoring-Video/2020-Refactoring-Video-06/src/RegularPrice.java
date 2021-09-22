/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * RegularPrice: 최신 영화 대여금과 적립금 
 * @author 김상진 
 */
public class RegularPrice implements Price {
	@Override
	public int getCharge(int daysRented) {
		int price = 2000;
		if(daysRented>2)
			price += (daysRented-2)*1500;
		return price;
	}
}
