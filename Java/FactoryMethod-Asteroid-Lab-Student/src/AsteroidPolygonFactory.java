
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidDiamondFactory.java: 
 * 중심으로부터 일정 범위 내에 있는 15개 꼭지점을 이용하여 도형을 만듦
 */
public class AsteroidPolygonFactory extends AsteroidFactory {
	@Override
	public Double[] createPoints(Location centerLoc, int radius) {
		Double[] points = new Double[30];
		for(int i=0; i<30; i+=2) {
			double len = Math.sqrt(Math.random()) * radius;
			points[i] = centerLoc.getX() + len + radius * Math.cos(i*2*Math.PI / 30);
			points[i+1] = centerLoc.getY() + len + radius * Math.sin(i * 2 * Math.PI / 30);
			}
		return points;
	}
	
}
