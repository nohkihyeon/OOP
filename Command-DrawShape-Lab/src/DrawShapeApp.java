import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * 
 */
public class DrawShapeApp extends Application {
	enum ShapeType {SQUARE, CIRCLE, TRIANGLE};
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int RADIUS = 40;
	
	private RadioButton drawButton = new RadioButton("추가");
	private RadioButton selectButton = new RadioButton("선택");
	private Button undoButton = new Button("취소");
	private Button redoButton = new Button("재실행");
	
	private ComboBox<String> shapeChoice = new ComboBox<>();
	private ShapeType currentShape = ShapeType.SQUARE;
	private Shape selectedShape = null;
	
	private ContextMenu popupMenu = new ContextMenu();
	
	private Pane centerPane = new Pane();
	
	private void drawShape(double x, double y) {
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
		centerPane.getChildren().add(shape);
	}
	
	private void changeColor() {
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(selectedShape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(selectedShape.getBoundsInLocal().getCenterY());
		centerPane.getChildren().add(picker);
		picker.setOnAction(e->{
			selectedShape.setFill(picker.getValue());
			centerPane.getChildren().remove(picker);
		});
	}
	
	private void deleteShape() {
		centerPane.getChildren().remove(selectedShape);
	}
	
	private void selectShape(double x, double y, double screenX, double screenY) {
		for(int i=centerPane.getChildren().size()-1; i>=0; i--) {
			Shape shape = (Shape) centerPane.getChildren().get(i);
			if(shape.getBoundsInLocal().contains(x, y)) {
				selectedShape = shape;
				popupMenu.show(centerPane, screenX, screenY);
				break;
			}
		}
	}
	
	private void mouseHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX();
		double y = mouseEvent.getY();
		if(x<RADIUS) x = RADIUS;
		else if(x+RADIUS>WIDTH) x = WIDTH-RADIUS;
		if(y<RADIUS) y = RADIUS;
		else if(y+RADIUS>HEIGHT) y = HEIGHT-RADIUS;
		if(drawButton.isSelected()) drawShape(x, y);
		else selectShape(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY());
	}
	
	private HBox constructControlPane() {
		String[] shapeList = {"Square", "Circle", "Triangle"};
		shapeChoice.getItems().addAll(shapeList);
		shapeChoice.getSelectionModel().selectFirst();
		shapeChoice.setOnAction(e->
			currentShape = ShapeType.valueOf(shapeChoice.getSelectionModel().getSelectedItem().toUpperCase())
		);
	
		ToggleGroup actionGroup = new ToggleGroup();
		actionGroup.getToggles().addAll(drawButton, selectButton);
		drawButton.setSelected(true);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox controlPane = new HBox();
		controlPane.setPadding(new Insets(10));
		controlPane.setSpacing(10);
		controlPane.getChildren().addAll(shapeChoice, drawButton, selectButton, spacer, undoButton, redoButton);
		return controlPane;
	}
	
	private void constructPopupMenu() {
		MenuItem fillColorItem = new MenuItem("채우기 색 변경");
		fillColorItem.setOnAction(e->changeColor());
		MenuItem deleteItem = new MenuItem("삭제");
		deleteItem.setOnAction(e->deleteShape());
		popupMenu.getItems().addAll(fillColorItem, deleteItem);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		centerPane.setOnMouseClicked(e->mouseHandle(e));
		
		mainPane.setCenter(centerPane);
		mainPane.setTop(constructControlPane());
		primaryStage.setTitle("도형 그리기");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		constructPopupMenu();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
