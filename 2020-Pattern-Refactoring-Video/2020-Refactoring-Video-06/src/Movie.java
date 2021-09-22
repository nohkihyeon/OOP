/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * Movie 클래스: 영화정보
 * 영화분류: 아동, 일반, 최신 
 * @author 김상진 
 */
public class Movie {
	public enum PriceCode {CHILDRENS, REGULAR, NEW_RELEASE};	
	private String title;
	private PriceCode priceCode;
	// 전략 패턴
	private Price price;
	public Movie(String title, PriceCode priceCode) {
		this.title = title;
		setPriceCode(priceCode);
	}
	public String getTitle() {
		return title;
	}
	public PriceCode getPriceCode() {
		return priceCode;
	}
	// 전략 패턴이지만 관계 주입 방식이 아님
	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
		switch(priceCode){
		case CHILDRENS: price = new ChildrensPrice(); break;
		case REGULAR: price = new RegularPrice(); break;
		case NEW_RELEASE: price = new NewReleasePrice();
		}
	}
	public void setPrice(Price price){
		this.price = price;
	}
	public int getCharge(int daysRented){
		return price.getCharge(daysRented);
	}
	public int getFrequentRentalPoints(int daysRented){
		return price.getFrequentRentalPoints(daysRented);
	}
}

