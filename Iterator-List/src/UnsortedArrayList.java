import java.util.Arrays;
import java.util.Iterator;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 배열 기반 비정렬 범용 리스트
 */
public class UnsortedArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	private Object[] list = new Object[capacity];
	private int size = 0;
	
	private class ArrayListIterator implements Iterator<T>{
		private int index = 0;
		@Override
		public boolean hasNext() {
			return index<size;
		}
		@Override
		public T next() {
			T retval = elementData(index);
			++index;
			return retval;
		}
	}
	
	public boolean isFull(){
		return false;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size() {
		return size;
	}
	@SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T)list[index];
    }
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return elementData(size-1);
	}
	public void pushBack(T item){
		if(size==capacity) {
			capacity *= 2;
			list = Arrays.copyOf(list, capacity);
		}
		list[size] = item;
		++size;
	}
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		T retval = elementData(size-1);
		--size;
		return retval;
	}
	public T get(int index){
		if(index>=0 && index<size) return elementData(index);
		else throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
	}
	public void remove(T item) {
		if(isEmpty()) return;
		for(int i=0; i<size; i++)
			if(elementData(i).equals(item)) {
				for(int j=i+1; j<size; j++) {
					list[j-1] = list[j];
				}
				--size;
				break;
			}
	}
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
}
