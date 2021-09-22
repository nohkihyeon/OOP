/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * Movie 클래스: 영화정보
 * 영화분류: 아동, 일반, 최신 
 * @author 김상진 
 */
public abstract class Movie {
	private String title;			// 영화제목
	public Movie(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	abstract public int getCharge(int daysRented);
	public int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
