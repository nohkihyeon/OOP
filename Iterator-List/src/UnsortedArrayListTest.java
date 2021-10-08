import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 배열 기반 비정렬 범용 리스트 테스트 프로그램
 */
class UnsortedArrayListTest {
	@Test
	void pushBack_popBackTest() {
		UnsortedArrayList<Integer> list = new UnsortedArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		list.pushBack(7);
		list.pushBack(9);
		list.pushBack(11);
		String output = "";
		while(!list.isEmpty()) {
			output += list.popBack()+",";
		}
		assertEquals(output,"11,9,7,3,5,10,");
	}
	@Test
	void removeTest() {
		UnsortedArrayList<Integer> list = new UnsortedArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		list.pushBack(1);
		list.remove(3);
		assertEquals(1,list.peekBack());
		list.remove(10);
		String output = "";
		while(!list.isEmpty()) {
			output += list.popBack()+",";
		}
		assertEquals(output,"1,5,");		
	}
	@Test
	void iteratorTest() {
		UnsortedArrayList<Integer> list = new UnsortedArrayList<>();
		list.pushBack(10);
		list.pushBack(5);
		list.pushBack(3);
		String output = "";
		for(var n: list)
			output += n+",";
		/*
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			output += iterator.next()+", ";
		}
		*/
		assertEquals(3,list.size());
		assertEquals(output,"10,5,3,");
	}

}
