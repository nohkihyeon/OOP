import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 상태 패턴
 * @author 김상진
 * 2016136035 노기현
 * 자동판매기 뷰에서 실행되는 각종 dialog
 * 1) showStockSetupDialog: 재고 관리
 * 2) showBalanceSetupDialog: 현금 관리
 * 3) showInsertDialog: 동전/지패 투입 관리
 * 4) showInfoDialog
 */
public class VendingMachineUtility {
	public static void showStockSetupDialog(VendingMachine vendingMachine) {
		Dialog<Boolean> dialog = new Dialog<>();
		dialog.setTitle("자판기 재고 관리");
		
		ButtonType loginButtonType = new ButtonType("설정", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane contentsPane = new GridPane();
		contentsPane.setHgap(10d);
		contentsPane.setVgap(10d);
		contentsPane.setPadding(new Insets(10d));
		
		Item[] items = Item.values();
		TextField[] itemLabels = new TextField[items.length];
		TextField[] inputs = new TextField[items.length];
		for(int i=0; i<itemLabels.length; i++) {
			itemLabels[i] = new TextField();
			inputs[i] = new TextField();
			itemLabels[i].setEditable(false);
			itemLabels[i].setMaxWidth(100d);
			inputs[i].setMaxWidth(100d);
			itemLabels[i].setText(items[i].toString());
			inputs[i].setText(vendingMachine.getNumberOfItems(items[i])+"");
			contentsPane.add(itemLabels[i], 0, i);
			contentsPane.add(inputs[i], 1, i);
		}
		dialog.getDialogPane().setContent(contentsPane);
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		    	vendingMachine.clearItems();
		        for(int i=0; i<items.length; i++) {
		        	int amount = Integer.valueOf(inputs[i].getText());
		        	if(amount>0)
		        		vendingMachine.supplementItems(items[i], amount);
		        }
		    }
		    return false;
		});
		dialog.showAndWait();
	}
	public static void showBalanceSetupDialog(VendingMachine vendingMachine) {
		Dialog<Boolean> dialog = new Dialog<>();
		dialog.setTitle("자판기 돈 설정");
		
		ButtonType loginButtonType = new ButtonType("설정", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane contentsPane = new GridPane();
		contentsPane.setHgap(10d);
		contentsPane.setVgap(10d);
		contentsPane.setPadding(new Insets(10d));
		
		Currency[] moneys = Currency.values();
		TextField[] itemLabels = new TextField[moneys.length];
		TextField[] inputs = new TextField[moneys.length];
		for(int i=0; i<itemLabels.length; i++) {
			itemLabels[i] = new TextField();
			inputs[i] = new TextField();
			itemLabels[i].setEditable(false);
			itemLabels[i].setMaxWidth(100d);
			inputs[i].setMaxWidth(100d);
			itemLabels[i].setText(String.format("%,d원", moneys[i].value));
			inputs[i].setText(vendingMachine.getAmount(moneys[i])+"");
			contentsPane.add(itemLabels[i], 0, i);
			contentsPane.add(inputs[i], 1, i);
		}
		dialog.getDialogPane().setContent(contentsPane);
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        for(int i=0; i<moneys.length; i++) {
		        	vendingMachine.setCash(moneys[i], Integer.valueOf(inputs[i].getText()));
		        }
		    }
		    return false;
		});
		dialog.showAndWait();
	}
	public static Optional<InsertedCash> showInsertCoinDialog() {
		Dialog<InsertedCash> dialog = new Dialog<>();
		dialog.setTitle("자판기 돈 투입");
		
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		HBox contentsPane = new HBox();
		contentsPane.setAlignment(Pos.CENTER);
		contentsPane.setPadding(new Insets(10d));
		contentsPane.setSpacing(10d);
		
		Currency[] moneys = Currency.values();
		ComboBox<String> moneyChoice = new ComboBox<>();
		for(var money: moneys) {
			moneyChoice.getItems().add(money.toString());
		}
		Spinner<Integer> numberOfCoins = new Spinner<>(0, 100, 1);
		contentsPane.getChildren().addAll(moneyChoice, numberOfCoins);
		moneyChoice.getSelectionModel().select(2);
		
		dialog.getDialogPane().setContent(contentsPane);
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == ButtonType.OK) {
		    	InsertedCash insertedCoins = new InsertedCash();
		    	insertedCoins.setMoney(moneys[moneyChoice.getSelectionModel().getSelectedIndex()]);
		    	insertedCoins.setAmount(numberOfCoins.getValue());
		    	return insertedCoins;
		    }
		    return null;
		});
		return dialog.showAndWait();
	}
	public static void showInfoDialog(String title, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		ImageView icon = new ImageView(new Image("soda.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
		alert.showAndWait();
	}
}
