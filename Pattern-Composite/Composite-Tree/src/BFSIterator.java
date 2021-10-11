import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년 2학기
 * @author 김상진
 * 합성 패턴
 * 큐를 이용한 너비 우선 탐색 반복자
 * 중간, 단말 노드 모두 반환함
 */
public class BFSIterator<T> implements Iterator<Node<T>> {
	Queue<Node<T>> queue = new ArrayDeque<>();
	public BFSIterator(Node<T> node){
		queue.add(node);
	}
	// hasNext의 역할은 반복할 것이 있는지 여부만 알려주는 것임
	// 하지만 이 메소드는 이 메소드는 다음 반복을 작업까지 하고 있음
	@Override
	public boolean hasNext() {
		if(queue.isEmpty()) return false;
		else{
			Node<T> node = queue.peek();
	 		if(node instanceof NonLeaf) {
	 			for(int i=0; i<node.numberOfChilds(); i++)
	 				queue.add(node.getChild(i));
	 		}
	 		return true;
 		} 
	}
	@Override
	public Node<T> next() {
		return queue.poll();
	}
}
