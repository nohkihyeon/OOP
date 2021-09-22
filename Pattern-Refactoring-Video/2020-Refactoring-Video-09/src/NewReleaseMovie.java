/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * NewReleaseMovie 클래스: 최신 영화
 * @author 김상진 
 */
public class NewReleaseMovie extends Movie {
	public NewReleaseMovie(String title) {
		super(title);
	}
	public int getCharge(int daysRented){
		return daysRented*2000;
	}
	public int getFrequentRentalPoints(int daysRented){
		return (daysRented>1)? 200: 100;
	}
}

