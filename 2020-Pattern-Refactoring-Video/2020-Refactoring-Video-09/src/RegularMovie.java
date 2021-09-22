/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * RegularMovie 클래스: 일반 영화
 * @author 김상진 
 */
public class RegularMovie extends Movie {
	public RegularMovie(String title) {
		super(title);
	}
	public int getCharge(int daysRented){
		int price = 2000;
		if(daysRented>2) price += (daysRented-2)*1500;
		return price;
	}
}
