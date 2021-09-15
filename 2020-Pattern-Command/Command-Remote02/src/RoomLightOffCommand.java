/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file RoomLightOffCommand.java: 방등 점멸 명령 객체
 */
public class RoomLightOffCommand implements Command {
	private RoomLight light;
	public RoomLightOffCommand(RoomLight light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.off();
	}
	@Override
	public void undo() {
		light.on();
	}
}
