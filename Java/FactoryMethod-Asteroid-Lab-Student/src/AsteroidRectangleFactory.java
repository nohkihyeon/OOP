import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidDiamondFactory.java: 사각형 도형으로 소행성을 나타냄
 */
public class AsteroidRectangleFactory extends AsteroidFactory {

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		Double[] points = new Double[8];
		double random = ThreadLocalRandom.current().nextDouble(50);
		points[0] = Double.valueOf(centerLoc.getX()+radius + random);
		points[1] = Double.valueOf(centerLoc.getY()+radius);
		points[2] = Double.valueOf(centerLoc.getX()+radius + random);
		points[3] = Double.valueOf(centerLoc.getY()-radius);
		points[4] = Double.valueOf(centerLoc.getX()-radius);
		points[5] = Double.valueOf(centerLoc.getY()-radius);
		points[6] = Double.valueOf(centerLoc.getX()-radius);
		points[7] = Double.valueOf(centerLoc.getY()+radius);
		return points;
	}

}
