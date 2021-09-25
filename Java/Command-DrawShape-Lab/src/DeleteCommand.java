import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * @file DeleteCommand.java: 삭 명령 클래 
 */

public class DeleteCommand implements Command, Cloneable{
	private static Stack<Shape> redoShape = new Stack<>();
	private static Stack<Shape> undoShape = new Stack<>();
	private Pane pane;

	public DeleteCommand(Pane pane) {
		this.pane = pane;
	}
	@Override
	public void execute() {
		undoShape.push(DrawShapeApp.getSelectedShape());
		pane.getChildren().remove(DrawShapeApp.getSelectedShape());
		redoShape.clear();
	}
	
	@Override
	public void redo() {
		if(!redoShape.isEmpty()) {
			Shape redoShap = redoShape.pop();
			pane.getChildren().remove(redoShap);
			undoShape.push(redoShap);
		}
	}
	
	@Override
	public void undo() {
		if(!undoShape.isEmpty()) {
			Shape undoShap = undoShape.pop();
			pane.getChildren().add(undoShap);
			redoShape.push(undoShap);
		}
	}

	
}
