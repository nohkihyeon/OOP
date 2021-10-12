import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * @file UserCardView.java
 * 탬플릿 메소드 패턴
 * 각 플레이어마다 받은 카드를 보여주는 뷰
 */
public class UserCardView extends Stage {
	private ArrayList<Card> userCards;
	private static int PREFCARDWIDTH = 100;
	private int height;
	public UserCardView(ArrayList<Card> userCards) {
		this.userCards = userCards;
		doLayout();
	}
	public void doLayout() {
		TilePane mainPane = new TilePane();
		for(Card card: userCards) {
			String fileName = "";
			if(card.getNumber()==0) {
				fileName = card.getFace()==CardFace.SPADES?
						"joker_black.png": "joker_red.png";
			}
			else {
				fileName = ""+
					card.getNumber()+
					card.getFace().toString().charAt(0)+".png";
			}
			Image cardImage = new Image("/image/"+fileName);
			height = (int)cardImage.getHeight();
			height = PREFCARDWIDTH*height/(int)cardImage.getWidth();
			ImageView cardView = new ImageView(cardImage);
			cardView.setFitWidth(PREFCARDWIDTH);
			cardView.setPreserveRatio(true);
			mainPane.getChildren().add(cardView);
		}
		setScene(new Scene(mainPane,userCards.size()*PREFCARDWIDTH,height));
	}
}
