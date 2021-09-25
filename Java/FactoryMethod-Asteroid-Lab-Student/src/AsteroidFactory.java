import java.util.concurrent.ThreadLocalRandom;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidFactory.java: 팩토리 메소드 패턴에서 생산자
 */
public abstract class AsteroidFactory {
	public enum Direction {NORTH, SOUTH, WEST, EAST};
	private Direction startDirection; 
	// 이동하는 속도는 동일구간을 이동하는데 걸리는 ms로 표현됨 
	// 따라서 값이 작을수록 빠르게 움직임
	// 레벨이 증가할수록 빠르게 이동활 확률이 높아짐
	protected int getSpeed(int level) {
		int speedProb = level*3+15;
		int speed = ThreadLocalRandom.current().nextInt(200);
		int currProb = ThreadLocalRandom.current().nextInt(100);
		if(currProb<=speedProb/2) speed += 1000;
		else if(currProb<=speedProb) speed += 1500;
		else if(currProb<=speedProb*2) speed += 2000;
		else speed += 2500;
		return speed;
	}
	// 소행성의 크기는 원 개념을 이용하여 반지름으로 나타냄
	// 레벨이 높을수록 크기가 커질 확률이 높아짐
	protected int getRadius(int level) {
		int radiusProb = level*3+15;
		int radius = ThreadLocalRandom.current().nextInt(10);
		int currProb = ThreadLocalRandom.current().nextInt(100);
		if(currProb<=radiusProb/2) radius += 90;
		else if(currProb<=radiusProb) radius += 60;
		else if(currProb<=radiusProb*2) radius += 30;
		else radius += 15;
		return radius;
	}
	protected Location getStartLocation(int radius) {
		int xLoc = ThreadLocalRandom.current().nextInt(AsteroidsGame.WIDTH);
		int yLoc = ThreadLocalRandom.current().nextInt(AsteroidsGame.HEIGHT);
		switch(ThreadLocalRandom.current().nextInt(4)) {
		case 0: // NORTH
			startDirection = Direction.NORTH;
			return new Location(xLoc, -radius);
		case 1: // WEST
			startDirection = Direction.WEST;
			return new Location(-radius, yLoc);
		case 2: // SOUTH
			startDirection = Direction.SOUTH;
			return new Location(xLoc, radius+AsteroidsGame.HEIGHT);
		default: // EAST
			startDirection = Direction.EAST;
			return new Location(radius+AsteroidsGame.WIDTH, yLoc);
		}
	}
	protected Location getDestLocation(int radius) {
		int xLoc = ThreadLocalRandom.current().nextInt(AsteroidsGame.WIDTH);
		int yLoc = ThreadLocalRandom.current().nextInt(AsteroidsGame.HEIGHT);
		switch(startDirection) {
		case NORTH:
			return new Location(xLoc, radius+AsteroidsGame.HEIGHT);
		case WEST:
			return new Location(radius+AsteroidsGame.WIDTH, yLoc);
		case SOUTH: 
			return new Location(xLoc, -radius);
		default:
			return new Location(-radius, yLoc);
		}
	}
	// 생성 프레임워크 (template method)
	public final Asteroid createAsteroid(int level) {
		int radius = getRadius(level);
		Location startLoc = getStartLocation(radius);
		Location destLoc = getDestLocation(radius);
		Double[] points = createPoints(startLoc, radius);
		return new Asteroid(startLoc, destLoc, getSpeed(level), points);
	}
	// 자식이 구현해야 하는 팩토리 메소드의 일부분 
	protected abstract Double[] createPoints(Location centerLoc, int radius);
}
