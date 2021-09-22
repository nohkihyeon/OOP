import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RentalSystemTest {
	// 대여금액: 일반>> 기본 2,000원, 2일 이후 대여일 x 1,500원
	// 대여금액: 최신>> 대여일 x 2,000원
	// 대여금액: 아동>> 기본 1,500원, 3일 이후 대여일 x 1,500원 
	// 적립금액: 일반, 아동, 최신>> 100점
	// 적립금액: 최신>> 2일 이상이면 추가 100점
	@Test
	void rentalBasicTest() {
		Customer sangjin = new Customer("김상진");
		sangjin.addRental(new Rental(new Movie("어벤져스: 인피니티워", Movie.PriceCode.REGULAR),2));
		sangjin.addRental(new Rental(new Movie("알라딘", Movie.PriceCode.CHILDRENS),2));
		sangjin.addRental(new Rental(new Movie("어벤져스: 엔드게임", Movie.PriceCode.NEW_RELEASE),2));
		String expectedResult = 
			"고객 김상진님의 대여목록:\n"+
			"\t어벤져스: 인피니티워\t2,000원\n" +
			"\t알라딘\t1,500원\n" +
			"\t어벤져스: 엔드게임\t4,000원\n" +
			"총금액: 7,500원\n" +
			"적립포인트: 400점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
	
	@Test
	void rentalAdvancedTest() {
		Customer sangjin = new Customer("김상진");
		sangjin.addRental(new Rental(new Movie("어벤져스: 인피니티워", Movie.PriceCode.REGULAR),4));
		sangjin.addRental(new Rental(new Movie("몬스터호텔3", Movie.PriceCode.CHILDRENS),4));
		sangjin.addRental(new Rental(new Movie("조커", Movie.PriceCode.NEW_RELEASE),3));
		String expectedResult = 
			"고객 김상진님의 대여목록:\n"+
			"\t어벤져스: 인피니티워\t5,000원\n" +
			"\t몬스터호텔3\t3,000원\n" +
			"\t조커\t6,000원\n" +
			"총금액: 14,000원\n" +
			"적립포인트: 400점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
}
