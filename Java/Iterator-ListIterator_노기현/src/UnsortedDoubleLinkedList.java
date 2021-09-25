import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * 이중 연결구조 기반 비정렬 범용 리스트
 */
public class UnsortedDoubleLinkedList<T> implements Iterable<T> {
	private static class Node<T>{
		private T item = null;
		private Node<T> prev = null;
		private Node<T> next = null;
		public Node(T item) {
			this.item = item;
		}
	}
	private class LinkedListIterator implements ListIterator<T>{
		private Node<T> curr = head;
		private int index = 0;
		// next()나 previous() 호출 이후 add()나 remove()가 호출되면 실행되지 않고 예외가 발생 하기위한 변
		private boolean isNextClicked = false;
		private boolean isPreviousClicked = false;
		private T changedNumber;
		@Override
		public boolean hasNext() {
			return curr.next != null;
		}
		@Override
		public T next() {
			if(hasNext()) {
				T tmp = curr.item;
				curr = curr.next;
				isNextClicked = true;
				isPreviousClicked = false;
				changedNumber = get(index);
				index++;
				return tmp;
			}
			else throw new NoSuchElementException("next");
		}
		@Override
		public boolean hasPrevious() {
			return curr.prev != null;
		}
		@Override
		public T previous() {
			if(hasPrevious()) {
				curr = curr.prev;
				isPreviousClicked = true;
				isNextClicked = false;
				changedNumber = get(--index);
				return curr.item;
			}
			return null;
		}
		@Override
		public int nextIndex() {
			if(hasNext()) {
			return index;
			}
			return -1;
		}
		@Override
		public int previousIndex() {
			if(hasPrevious()) {
			return index-1;
			}
			return -1;
		}
		@Override
		public void remove() {
			if (isNextClicked) {
				if (index ==0)
					popFront();
				else if (index ==size)
					popBack();
				else {
					removeFirst(changedNumber);
					index--;
				}
				isNextClicked = false;
			}
			else if (isPreviousClicked) {
				if (index ==0) {
					popFront(); index--;
				}
				else if (index ==size) {
					popBack(); index--;
				}
				else {
					Node<T> curr2 = curr;
					removeFirst(changedNumber);
					curr = curr2;
				}
				isPreviousClicked = false;	
			}
			else throw new IllegalStateException("remove state exception");
		}
		@Override
		public void set(T e) {
			if (isNextClicked) {
				remove();
				add(e);
				isNextClicked = false;
			}
			else if (isPreviousClicked) {
				remove();
				add(e);
				index--;
				isPreviousClicked = false;	
			}
			else throw new IllegalStateException("set state exception");
		}
		// 새로운 노드를 이용해서 중간에 연결을 바꾸어준다.
		@Override
		public void add(T e) {
			if (index ==0) {
				pushFront(e);
			} else if(index==size) {
				pushBack(e);
			} else {
				Node<T> newNode = new Node<>(e);
				
				Node<T> currPrev = curr.prev;
				currPrev.item =  curr.prev.item;
				currPrev.next = curr.next;
				currPrev.prev = curr.prev;
				
				curr.prev = newNode;
				newNode.prev = currPrev;
				newNode.next = curr;
				currPrev.next = newNode;
				
				++size;
			}
			++index;
			isNextClicked = false;
		}		
	}
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	public boolean isFull() {
		return false;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public int size() {
		return size;
	}
	public T get(int index) {
		if(index>=0&&index<size) {
			Node<T> curr = head;
			for(int i=0; i<index; i++) {
				curr = curr.next;
			}
			return curr.item;
		}
		else throw new IndexOutOfBoundsException();
	}
	public void pushFront(T item) {
		Node<T> newNode = new Node<>(item);
		newNode.next = head;
		if(head!=null) head.prev = newNode;
		else tail = newNode;
		head = newNode;
		++size;
	}
	public T popFront() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = head;
		head = head.next;
		if(head!=null) head.prev = null;
		else tail = null;
		--size;
		return popNode.item;
	}
	public T peekFront() {
		if(isEmpty()) throw new IllegalStateException();
		return head.item;
	}
	public void pushBack(T item) {
		Node<T> newNode = new Node<>(item);
		if(tail==null) head = tail = newNode;
		else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		++size;
	}
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = tail;
		tail = tail.prev;
		if(tail!=null) tail.next = null;
		else head = null;
		--size;
		return popNode.item;
	}
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return tail.item;
	}
	@SuppressWarnings("unchecked")
	public void pushBackAll(T... items) {
		for(var item: items) pushBack(item);
	}
	public boolean find(T item) {
		if(isEmpty()) return false;
		Node<T> curr = head;
		while(curr!=null) {
			if(curr.item.equals(item)) return true;
			curr = curr.next;
		}
		return false;
	}
	public void removeFirst(T item) {
		if(isEmpty()) return;
		Node<T> dummy = new Node<>(null);
		dummy.next = head;
		head.prev = dummy;
		Node<T> curr = head;
		while(curr!=null) {
			if(curr.item.equals(item)) {
				curr.prev.next = curr.next;
				if(curr.next!=null) curr.next.prev = curr.prev;
				if(curr==tail) tail = tail.prev;
				--size;
				break;
			}
			else curr = curr.next;
		}
		head = dummy.next;
		if(head==null) tail = null;
		else head.prev = null;
	}
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	public ListIterator<T> listIterator() {
		return new LinkedListIterator();
	}
}
