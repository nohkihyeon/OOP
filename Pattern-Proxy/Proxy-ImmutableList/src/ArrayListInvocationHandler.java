import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * @author 김상진
 * 프록시 패턴: 보호 프록시
 * ArrayList를 불변 객체로 처리할 수 있도록 해주는 프록시를 생성해주는 클래스
 */
public class ArrayListInvocationHandler<T> implements InvocationHandler {
	private final ArrayList<T> list;
	public ArrayListInvocationHandler(ArrayList<T> list) {
		this.list = list;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if(method.getName().startsWith("add")) {
				throw new IllegalAccessException();
			}
			else if(method.getName().startsWith("remove")) {
				throw new IllegalAccessException();
			}
			switch(method.getName()) {
			case "clear":
			case "ensureCapacity":
			case "forEach":
			case "replace":
			case "set":	
			case "sort":
			case "trimToSize":
				throw new IllegalAccessException();
			default:
				method.invoke(list, args);
			}
		}
		catch(InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}

}
