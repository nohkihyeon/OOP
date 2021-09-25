import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * Asteroid.java: 소행성
 */
public class Asteroid extends Polygon{
	private Location startLoc;
	private Location destLoc;
	private int speed;
	
	// 주어진 좌표를 다각형의 꼭짓점으로 사용하여 소행성을 생성함
	public Asteroid(Location startLoc, Location destLoc, int speed, Double[] points) {
		this.startLoc = startLoc;
		this.destLoc = destLoc;
		this.speed = speed;
		getPoints().addAll(points);
		setStroke(Color.LIGHTGRAY);
		setFill(null);
		setStrokeWidth(3);
	}
	
	public Location getStartLoc() {
		return startLoc;
	}
	public Location getDestLoc() {
		return destLoc;
	}
	public int getSpeed() {
		return speed;
	}
	@Override public boolean equals(Object other) {
		if(other==null||getClass()!=other.getClass()) return false;
		if(this==other) return true;
		Asteroid a = (Asteroid)other;
		return startLoc.equals(a.startLoc)&&
			destLoc.equals(a.destLoc)&&
			speed==a.speed;
	}
}
