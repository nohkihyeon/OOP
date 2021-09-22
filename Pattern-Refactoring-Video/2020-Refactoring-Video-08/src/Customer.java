import java.util.ArrayList;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 */
public class Customer {
	private String name;
	private ArrayList<Rental> rentals = new ArrayList<>();
	public Customer(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void addRental(Rental rental){
		rentals.add(rental);
	}
	// 반복문을 다 제거 하고 있다 java8부터
	public String statement(){
		String result = String.format("고객 %s님의 대여목록:\n", name);
		result += rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovieTitle(), r.getCharge()))
			.reduce("", String::concat);	
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %,d점\n", getTotalFrequencyRentalPoints());
		return result;
		/*
		StringBuilder result = new StringBuilder(64);
		result.append(String.format("고객 %s님의 대여목록:\n", name));
		result.append(rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovie().getTitle(), r.getCharge()))
			.reduce("", String::concat));	
		result.append(String.format("총금액: %,d원\n", getTotalCharge()));
		result.append(String.format("적립포인트: %d점\n", getTotalFrequencyRentalPoints()));
		return result.toString();
		*/
	}
	private int getTotalCharge(){
		return rentals.stream().map(r->r.getCharge()).reduce(0,Integer::sum);
	}
	private int getTotalFrequencyRentalPoints(){
		return rentals.stream().map(r->r.getFrequentRentalPoints()).reduce(0,Integer::sum);
	}
}
	
