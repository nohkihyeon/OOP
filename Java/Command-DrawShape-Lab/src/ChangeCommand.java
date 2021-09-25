
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.Stack;


/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * @file ChangeCommand.java: 바꾸는 명령 클래 
 */

public class ChangeCommand implements Command, Cloneable{
	private static  Stack<Shape> undoShape = new Stack<>();
	private static  Stack<Shape> redoShape = new Stack<>();
	private static Stack<Color> undoColor = new Stack<>(); 
	private static Stack<Color> redoColor = new Stack<>();
	private Pane pane;

	public ChangeCommand(Pane pane) {
		this.pane = pane;
	}
	@Override
	public void execute() {
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(DrawShapeApp.getSelectedShape().getBoundsInLocal().getCenterX());
		picker.setLayoutY(DrawShapeApp.getSelectedShape().getBoundsInLocal().getCenterY());
		pane.getChildren().add(picker);
		picker.setOnAction(e->{
			undoColor.push((Color)DrawShapeApp.getSelectedShape().getFill());
			DrawShapeApp.getSelectedShape().setFill(picker.getValue());
			pane.getChildren().remove(picker);
			undoShape.push(DrawShapeApp.getSelectedShape());
			redoColor.clear();
			redoShape.clear();
		});
	}

	@Override
	public void redo() {
		if(!redoShape.isEmpty()) {
			Shape redoShap = redoShape.pop();
			Color redoCol = redoColor.pop();
			undoColor.push((Color)redoShap.getFill());
			undoShape.push(redoShap);
			redoShap.setFill(redoCol);
		}
	}

	@Override
	public void undo() {
		if(!undoShape.isEmpty()) {
			Shape undoShap = undoShape.pop();
			Color undoCol = undoColor.pop();
			redoColor.push((Color)undoShap.getFill());
			redoShape.push(undoShap);
			undoShap.setFill(undoCol);
		}
	}
	
}
