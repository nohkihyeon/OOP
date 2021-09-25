package kakaoAPI;

import java.util.List;

public class Information {
	public static List<Document> document;
	public Meta meta;
	
	public static void printDoc() {
		for(Document d : document) {
			System.out.println(d);
		}
	}
}
