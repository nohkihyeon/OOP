import java.util.Iterator;
import java.util.ListIterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진 
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
		@Override
		public boolean hasNext() {
			return false;
		}
		@Override
		public T next() {
			return null;
		}
		@Override
		public boolean hasPrevious() {
			return false;
		}
		@Override
		public T previous() {
			return null;
		}
		@Override
		public int nextIndex() {
			return 0;
		}
		@Override
		public int previousIndex() {
			return 0;
		}
		@Override
		public void remove() {
		}
		@Override
		public void set(T e) {
		}
		@Override
		public void add(T e) {
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
