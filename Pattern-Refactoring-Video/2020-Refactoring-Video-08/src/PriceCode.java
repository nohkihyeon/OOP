/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * PriceCode 열거형: 대여금과 적립금 
 * @author 김상진 
 */
public enum PriceCode {
	CHILDRENS{
		@Override public int getCharge(int daysRented) {
			int price = 1500;
			if(daysRented>3) price += (daysRented-3)*1500;
			return price;
		}		
	}, 
	REGULAR{
		@Override public int getCharge(int daysRented) {
			int price = 2000;
			if(daysRented>2) price += (daysRented-2)*1500;
			return price;
		}		
	}, 
	NEW_RELEASE{
		@Override public int getCharge(int daysRented) {
			return daysRented*2000;
		}
		@Override public int getFrequentRentalPoints(int daysRented){
			return daysRented>1 ? 200: super.getFrequentRentalPoints(daysRented);
		}		
	};
	public abstract int getCharge(int daysRented);
	public int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
