/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * Rental 클래스: 대여정보 
 * @author 김상진 
 */
public class Rental {
	private Movie movie;		// 영화
	private int daysRented;		// 대여날짜
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public int getCharge(){
		int price = 0;
		switch(getMovie().getPriceCode()){		// 05에서 Movie로 옮기면서 
		case REGULAR:
			price += 2000;
			if(daysRented>2) price += (daysRented-2)*1500;
			break;
		case NEW_RELEASE:
			price += daysRented*2000;
			break;
		case CHILDRENS:
			price += 1500;
			if(daysRented>3) price += (daysRented-3)*1500;
			break;
		}
		return price;
	}
	/*
		int frequentRentalPoint = 100;
		if((rental.getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
			rental.getDaysRented()>1)
			frequentRentalPoint += 100;
		return frequentRentalPoint;
	*/
	public int getFrequentRentalPoints(){
		if((getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
			getDaysRented()>1)
			return 200;
		else return 100;
	}
}
