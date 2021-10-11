import java.util.Iterator;
import java.util.Stack;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년 2학기
 * @author 김상진
 * 합성 패턴
 * 반복자를 이용한 합성 패턴을 위한 반복자 구현
 * 스택을 이용한 깊이 우선 탐색 
 * 단말 노드만 반환함
 */
public class TreeIteratorDFS<T> implements Iterator<Node<T>> {
	Stack<Iterator<Node<T>>> stack = new Stack<>();
	public TreeIteratorDFS(Iterator<Node<T>> iterator){
		stack.push(iterator);
	}
	@Override
	public boolean hasNext() {
		if(stack.empty()) return false;
		else{
			Iterator<Node<T>> iterator = stack.peek();
	 		if(iterator.hasNext()) return true;
			else{ 
				stack.pop(); 
				return hasNext(); 
			}
 		} 
	}
	@Override
	public Node<T> next() {
		Node<T> node = stack.peek().next();
		if(node instanceof NonLeaf){ // Leaf의 NullIterator는 활용되지 않음
			stack.push(node.iterator());
			return next(); // NonLeaf를 반환하고 싶지 않으면
		}
		else return node;
	}
}
