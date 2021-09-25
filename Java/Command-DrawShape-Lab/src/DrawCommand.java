import java.util.Stack;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 2016136035 노기현
 * @file DrawCommand.java: 그리는 명령 클래스
 */

public final class DrawCommand implements Command, Cloneable{
	private static Stack<Shape> undoShape = new Stack<>();
	private static Stack<Shape> redoShape = new Stack<>();
	private Pane pane;
	private ShapeType currentShape;
	private double x;
	private double y;
	private static final int RADIUS = 40;
	
	public DrawCommand(Pane pane, double x, double y, ShapeType cs) {
		this.pane = pane;
		this.x = x;
		this.y = y;
		this.currentShape = cs;
	}
	@Override
	public void execute() {
		Shape shape = null;
		switch(currentShape) {
		case SQUARE:
			shape = new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
			break;
		case CIRCLE:
			shape = new Circle(x,y, RADIUS);
			break;
		case TRIANGLE:
			Polygon triangle = new Polygon();
			final double radian = Math.PI / 180F;
			triangle.getPoints().addAll(new Double[] {
				x+RADIUS*Math.cos(30*radian),
				y+RADIUS*Math.sin(30*radian),
				x+RADIUS*Math.cos(150*radian),
				y+RADIUS*Math.sin(150*radian),
				x+RADIUS*Math.cos(270*radian),
				y+RADIUS*Math.sin(270*radian)
			});
			shape = triangle;
		}
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		pane.getChildren().add(shape);
		undoShape.push(shape);
	}

	@Override
	public void redo() {
		if(!redoShape.isEmpty()) {
			Shape shape = redoShape.pop();
			pane.getChildren().add(shape);
			undoShape.push(shape);
		}
	}
	@Override
	public void undo() {
		if(!undoShape.isEmpty()) {
			Shape shape = undoShape.pop();
			pane.getChildren().remove(shape);
			redoShape.push(shape);
		}
	}

	
}
