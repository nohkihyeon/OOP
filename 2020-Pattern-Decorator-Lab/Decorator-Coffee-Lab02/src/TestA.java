import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestA {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> aClass = Class.forName("A");
		Constructor<?> defaultConstructor = aClass.getConstructor();
		A a = (A)defaultConstructor.newInstance();
		System.out.println(a.getI());
		Constructor<?> intConstructor = aClass.getConstructor(int.class);
		A a1 = (A)intConstructor.newInstance(10);
		System.out.println(a1.getI());
		Constructor<?> intStringConstructor = aClass.getConstructor(int.class,String.class);
		A a2 = (A)intStringConstructor.newInstance(10,"hello");
		System.out.println(a2.getI()+", "+a2.getS());
	}

}
