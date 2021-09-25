import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현 
 * 배열 기반 비정렬 범용 리스트
 */
public class UnsortedArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	private Object[] list = new Object[capacity];
	private int size = 0;
	
	private class ArrayListIterator implements ListIterator<T>{
		private int index = 0;
		// next, prev했을 때 반환하는 값을 저장하는 변
		private T ChangedNumber;
		// Next 또는 Previous를 택했다는 것을 알려주는 flag
		private boolean isNextClicked = false;
		private boolean isPreviousClicked = false;
		@Override
		public boolean hasNext() {
			return (index < size);
		}
		@Override
		public T next() {
			if (hasNext()) {
				isNextClicked = true;
				isPreviousClicked = false;
				ChangedNumber = elementData(index++);
				return ChangedNumber;
			}
			else throw new NoSuchElementException("유효하지않는 next");
		}
		@Override
		public boolean hasPrevious() {
			return index >= 0;
		}
		@Override
		public T previous() {
			if (hasPrevious()) {
				isPreviousClicked = true;
				isNextClicked = false;
				ChangedNumber = elementData(--index);
				return ChangedNumber;
			}
			return null;
		}
		@Override
		public int nextIndex() {
			if (hasNext()) {
				return ++index;
			}
			return -1;
		}
		@Override
		public int previousIndex() {
			if (hasPrevious())
				return --index;
			return -1;
		}
		@Override
		public void remove() {
			if (isNextClicked) {
				if(isEmpty()) return;
				UnsortedArrayList.this.remove(ChangedNumber);
				--index;
				isNextClicked = false;
			}
			else if (isPreviousClicked) {
				UnsortedArrayList.this.remove(ChangedNumber);
				isPreviousClicked = false;
			}
			else throw new IllegalStateException("remove IllegalState");
		}
		@Override
		public void set(T e) {
			if (isNextClicked) {
				if(isEmpty()) return;
				for(int i=0; i<size; i++)
					if(elementData(i).equals(elementData(index))) {
						list[i-1] = e;
						break;
					}
				isNextClicked = false;
			}
			else if (isPreviousClicked) {
				for(int i=0; i<size; i++)
					if(elementData(i).equals(elementData(index))) {
						list[i-1] = e;
						break;
					}
				isPreviousClicked = false;
			}
			else throw new IllegalStateException("set IllegalState");
		}
		@Override
		public void add(T e) {
			if(isEmpty()) return;
			for(int i=size-1; i>=index; i--)
				list[i+1] = list[i];
			list[index] = e;
			index++;
			size++;
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
				for(int j=i+1; j<size; j++)
					list[j-1] = list[j];
				--size;
				break;
			}
	}
	@SuppressWarnings("unchecked")
	public void pushBackAll(T... items) {
		for(var item: items) pushBack(item);
	}
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	public ListIterator<T> listIterator() {
		return new ArrayListIterator();
	}
}
