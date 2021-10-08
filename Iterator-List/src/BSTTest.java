import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 이진 검색 트리 테스트 프로그램
 */
class BSTTest {
	@Test
	void addTest() {
		BST<Integer> tree = new BST<>();
		tree.add(7);
		tree.add(5);
		tree.add(3);
		tree.add(9);
		tree.add(11);
		String output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"3,5,7,9,11,");
	}
	@Test
	void removeTest() {
		BST<Integer> tree = new BST<>();
		tree.add(7);
		tree.add(5);
		tree.add(3);
		tree.add(9);
		tree.add(11);
		tree.remove(7);
		String output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"3,5,9,11,");
		tree.remove(11);
		output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"3,5,9,");
	}
	@Test
	void iteratorTest() {
		BST<Integer> tree = new BST<>();
		tree.add(7);
		tree.add(5);
		tree.add(3);
		tree.add(9);
		tree.add(11);
		String output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"3,5,7,9,11,");
		tree.setTraversalType(BST.TraversalType.PREORDER);
		output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"7,5,3,9,11,");
		tree.setTraversalType(BST.TraversalType.POSTORDER);
		output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"3,5,11,9,7,");
		tree.setTraversalType(BST.TraversalType.BFS);
		output = "";
		for(var n: tree)
			output += n+",";
		assertEquals(output,"7,5,9,3,11,");
	}
}
