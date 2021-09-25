/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * Location.java: 좌표 정보
 */
public class Location {
	private int x = 0;
	private int y = 0;
	public Location() {}
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override public boolean equals(Object other) {
		if(other==null||getClass()!=other.getClass()) return false;
		if(this==other) return true;
		Location loc = (Location)other;
		return x==loc.x&&y==loc.y;
	}
}
