import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * @author 김상진
 * 프록시 패턴: 보호 프록시
 * 테스트 프로그램
 */
public class ProxyTest {
	@SuppressWarnings("unchecked")
	public static <T> List<T> getImmutableArrayList(ArrayList<T> list){
		return (List<T>)Proxy.newProxyInstance(
				ArrayList.class.getClassLoader(), 
				new Class<?>[]{List.class},
				new ArrayListInvocationHandler<T>(list));
		
	}
	public static void main(String[] args) {
		/*
		Class<?>[] interfaces = ArrayList.class.getInterfaces();
		for(Class<?> c: interfaces) {
			System.out.println(c.getName());
		}
		*/
		
		ArrayList<String> list = new ArrayList<>();
		list.add("apple");
		list.add("lemon");
		list.add("grape");
		list.add("blueberry");
		list.add("banana");
		try {
			List<String> constList = getImmutableArrayList(list);
			constList.add("kiwi");
		}
		catch(Exception e) {
			System.out.println("동적 프록시가 잘 동작하고 있음");
		}
		
		try {
			List<String> unmodifiableList = Collections.unmodifiableList(list);
		    unmodifiableList.add("four");
		}
		catch(Exception e) {
			System.out.println("자바 라이브러리 unmodifiableList 사용");
		}
		
		try {
			List<String> unmodifiableList = List.copyOf(list);
			unmodifiableList.add("four");
		}
		catch(Exception e) {
			System.out.println("자바 라이브러리 copyOf 사용");
		}
		
	}

}
