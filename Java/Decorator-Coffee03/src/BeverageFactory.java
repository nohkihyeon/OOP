/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * @file BeverageFactory.java
 * reflection 사용한 Beverage 장식한다.
 */

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeverageFactory {
	static String before ="";
	public static Beverage createCoffee(String coffee, String... list) 
			throws Exception{
			//throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Beverage beverage = createBaseCoffee(coffee);
		
		beverage = decorateBaseCoffeeV1(beverage, list);
		return beverage;
	}
	
	// protected인 경우 생성자를 가지고 올 때는 getConstructor 대신에 getDeclaredConstructor 사용
	// reflection 사용하면 접근권한을 우회할 수 있음	
	private static Beverage createBaseCoffee(String coffee) throws Exception{
		//throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<? extends Beverage> coffeeClass 
			= Class.forName(coffee).asSubclass(Beverage.class);
		if(coffeeClass.getSuperclass()!=Beverage.class||coffeeClass==CondimentDecorator.class)
			throw new IllegalArgumentException("Must use Concrete Decoretee");
		/*
		// public 생성자를 제공하는 경우
		Constructor<? extends Beverage> coffeeConstructor = decorateeClass.getConstructor();
		Beverage beverage = (Beverage)coffeeConstructor.newInstance();
		*/
		// 생성자가 protected, private인 경우
		Constructor<? extends Beverage> coffeeConstructor = coffeeClass.getDeclaredConstructor();
		coffeeConstructor.setAccessible(true);
		Beverage beverage = (Beverage)coffeeConstructor.newInstance();
		return beverage;
	}
	
	private static Beverage decorateBaseCoffeeV1(Beverage beverage, String... list) throws Exception{
		//throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if(list.length==0) return beverage;
		Arrays.sort(list);

		// 더 효과적으로 할 수 있을까? Mocha가 여러 번 사용되면 이 과정이 반복됨
		// Decorator들을 대표하는 CondimentDeocorator 클래스가 없을 경우, 아래처럼 강건하게 작성할 수 없음
		for(String s: list){
			Class<? extends CondimentDecorator> condimentClass 
				= Class.forName(s).asSubclass(CondimentDecorator.class);
			
			// 정렬된 이름을 기준으로 이전(before)과 같은 장식이면 doubleAllowed()로 확인한다.
			if (before == s && !beverage.doubleAllowed()) {
				throw new IllegalArgumentException(condimentClass.getName() + " 두번 첨가가 불가합니다!!");
			}
			before = s;
			
			// public 생성자를 제공하는 경우
			//Constructor<? extends CondimentDecorator> condimentConstructor 
			//	= condimentClass.getConstructor(Beverage.class);
			//beverage = (Beverage)condimentConstructor.newInstance(beverage);
			
			Constructor<? extends CondimentDecorator> condimentConstructor 
				= condimentClass.getDeclaredConstructor(Beverage.class);
			//condimentConstructor.setAccessible(true);
			
			beverage = (Beverage)condimentConstructor.newInstance(beverage);
		}
		return beverage;
	}
	/*
	private static Beverage decorateBaseCoffeeV2(Beverage beverage, String... list) throws Exception{
		if(list.length==0) return beverage;
		Arrays.sort(list);
		Map<String, Constructor<? extends CondimentDecorator>> decoratorMap = new HashMap<>(11);
		
		for(String s: list){
			if(!decoratorMap.containsKey(s)) {
				decoratorMap.put(s, 
					Class.forName(s).asSubclass(CondimentDecorator.class)
						.getDeclaredConstructor(Beverage.class));
			}
		}
		
		for(String s: list){
			beverage = (Beverage)decoratorMap.get(s).newInstance(beverage);
		}
		return beverage;
	}
	*/
}
