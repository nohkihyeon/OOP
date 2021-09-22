/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * Price interface: 대여금과 적립금 
 * @author 김상진 
 */
public interface Price {
	int getCharge(int daysRented);
	default int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
