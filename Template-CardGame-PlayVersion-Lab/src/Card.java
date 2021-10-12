/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file Card.java
 * 탬플릿 메소드 패턴
 * 카드 한 장을 나타내는 클래스
 * 모양과 숫자
 * 0이면 조커
 */
public class Card{
	private final int number;
    private final CardFace face;
    public Card(int number, CardFace face){
    	if(number>=0&&number<=13) this.number = number;
		else throw new IllegalArgumentException();
        this.face = face;
    }
    public int getNumber(){
        return number;
    }
    public CardFace getFace(){
        return face;
    }
    @Override
    public String toString(){
    	return String.format("%d %s", number, face);
    }
}
