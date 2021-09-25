import javafx.scene.control.Button;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * @file CommandHolderButton.java:명령 패턴을 유지하는 버
 */
class CommandHolderButton extends Button  implements Command{
	private Command command;
	public void setCommand(Command command) {
		this.command = command;
	}
	public Command getCommand() {
		return command;
	}
	@Override
	public void execute() {
		command.execute();
	}
	@Override
	public void undo() {
		command.undo();
	}
	@Override
	public void redo() {
		command.redo();
	}
}