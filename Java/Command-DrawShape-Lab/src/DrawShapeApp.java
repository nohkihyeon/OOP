import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * 2. 명령 패턴을 이용한 경우
 */
public class DrawShapeApp extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int RADIUS = 40;
	
	private RadioButton drawButton = new RadioButton("추가");
	private RadioButton selectButton = new RadioButton("선택");
	private Button undoButton = new Button("취소");
	private Button redoButton = new Button("재실행");
	
	private ComboBox<String> shapeChoice = new ComboBox<>();
	private ShapeType currentShape = ShapeType.SQUARE;
	private static Shape selectedShape = null;
	
	private ContextMenu popupMenu = new ContextMenu();
	
	private Pane centerPane = new Pane();
	
	private Stack<Command> undoCommands = new Stack<>();
	private Stack<Command> redoCommands = new Stack<>();
	
	private CommandHolderButton drawShapeButton = new CommandHolderButton();
	private CommandHolderButton changeColorButton = new CommandHolderButton();
	private CommandHolderButton deleteButton = new CommandHolderButton();
	
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
	// command setActions
	void setActions(Command command) {
			command.execute();
			undoCommands.push(command);
			redoCommands.clear();
		}
		
	private void redoCommand() {
		if(!redoCommands.isEmpty()) {
			Command command = redoCommands.pop();
			command.redo();
			undoCommands.push(command);
		}
	}
	private void undoCommand() {
		if(!undoCommands.isEmpty()) {
			Command command = undoCommands.pop();
			command.undo();
			redoCommands.push(command);
		}
	}

	private void mouseHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX();
		double y = mouseEvent.getY();
		drawShapeButton.setCommand(new DrawCommand(centerPane,x,y,currentShape));
		if(x<RADIUS) x = RADIUS;
		else if(x+RADIUS>WIDTH) x = WIDTH-RADIUS;
		if(y<RADIUS) y = RADIUS;
		else if(y+RADIUS>HEIGHT) y = HEIGHT-RADIUS;
		if(drawButton.isSelected()) setActions(drawShapeButton);
		else selectShape(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY());
		
	}
	
	private HBox constructControlPane() {
		String[] shapeList = {"Square", "Circle", "Triangle"};
		shapeChoice.getItems().addAll(shapeList);
		shapeChoice.getSelectionModel().selectFirst();
		shapeChoice.setOnAction(e->
			currentShape = ShapeType.valueOf(shapeChoice.getSelectionModel().getSelectedItem().toUpperCase())
		);
		// undo : 취소 버튼 처리 / redo : 재실행 버튼 처리
		undoButton.setOnAction(e->undoCommand());
		redoButton.setOnAction(e->redoCommand());
	
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
		changeColorButton.setCommand(new ChangeCommand(centerPane));
		fillColorItem.setOnAction(e->setActions(changeColorButton));
		MenuItem deleteItem = new MenuItem("삭제");
		deleteButton.setCommand(new DeleteCommand(centerPane));
		deleteItem.setOnAction(e->setActions(deleteButton));
		popupMenu.getItems().addAll(fillColorItem, deleteItem);
	}
	// 선택된 함수를 반환해주는 함수
	public static Shape getSelectedShape() {
		return selectedShape;
	}
	// 현재의 모양을 설정해주는 함수 (shape)
	public static void setSelectedShape(Shape selectedShape) {
		DrawShapeApp.selectedShape = selectedShape;
	}
	
	public Pane getCneterPane() {
		return centerPane;
	}
	
	public void setCenterPane(Pane centerPane) {
		this.centerPane = centerPane;
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
