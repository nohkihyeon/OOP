package kakaoAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jdi.Type;

public class kakaoJson {
	public static void main(String[] argc) throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		String jsonDoc = gson.toJson(getJsonDoc("이루온"));
		
		String jsonString = getJsonDoc("이루온");
		Information information = new Gson().fromJson(jsonString, Information.class);
		System.out.println(information);
//		for (Document doc : information.document) {
//			System.out.println(doc);
//		}
		
	}

	private static String getJsonDoc( String keyword ) throws Exception {
		
		String encodeKeyword = "";  // 한글 주소는 encoding 해서 날려야 함
		try { encodeKeyword = URLEncoder.encode( keyword, "UTF-8" ); } 
		catch ( UnsupportedEncodingException e ) { e.printStackTrace(); }
		
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" 
	                                                                     + encodeKeyword;
		String auth = "KakaoAK " + "1191cf18c7960d3f7bf40f82d831ef30";
		
		URL url = new URL( apiUrl );
	    HttpsURLConnection conn = ( HttpsURLConnection ) url.openConnection();
		conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Authorization", auth );
	    
	    BufferedReader br;

	    int responseCode = conn.getResponseCode();
	    if( responseCode == 200 ) {  // 호출 OK
	    	br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8") );
	    } else {  // 에러
	    	br = new BufferedReader( new InputStreamReader(conn.getErrorStream(), "UTF-8") );
	    }
	    
	    String jsonString = new String();
	    String stringLine;
	    while ( ( stringLine= br.readLine()) != null ) {
	        jsonString += stringLine;
	    }
	    return jsonString;
	}
}

