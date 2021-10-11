import java.util.Iterator;
import java.util.Stack;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년 2학기
 * @author 김상진
 * 합성 패턴
 * 스택을 이용한 깊이 우선 탐색 반복자
 * 중간, 단말 노드 모두 반환함
 */
public class DFSIterator<T> implements Iterator<Node<T>> {
	Stack<Node<T>> stack = new Stack<>();
	public DFSIterator(Node<T> node){
		stack.push(node);
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	@Override
	public Node<T> next() {
		Node<T> node = stack.pop();
		if(node instanceof NonLeaf) {
 			for(int i=0; i<node.numberOfChilds(); i++)
 				stack.push(node.getChild(i));
 		}
		return node;
	}
}
