/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * ChildrenMovie 클래스: 아동 영화
 * @author 김상진 
 */
public class ChildrenMovie extends Movie {
	public ChildrenMovie(String title) {
		super(title);
	}
	public int getCharge(int daysRented){
		int price = 1500;
		if(daysRented>3) price += (daysRented-3)*1500;
		return price;
	}
}
