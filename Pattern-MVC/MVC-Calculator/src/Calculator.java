import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진
 * Calculator.java
 * 모델: Infix 형태의 표현식을 받아 평가하는 기능
 * 뷰: 평가 결과 값을 보여주는 기능
 * 컨트롤러: 
 *   1) 사용자가 입력한 표현식을 받아 모델에 전달하고 해당 결과를 받아 뷰에 전달함
 *   2) 사용자가 입력한 표현식의 유효성을 검사함????
 */
public class Calculator extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CalculatorView view = new CalculatorView();
		CalculatorModel model = new CalculatorModel();
		@SuppressWarnings("unused")
		CalculatorController controller = new CalculatorController(view, model);
		primaryStage.setTitle("MVC 계산기");
		primaryStage.setScene(new Scene(view));
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
