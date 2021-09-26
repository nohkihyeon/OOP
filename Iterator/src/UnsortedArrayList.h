/*
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 반복자 패턴
 * UnsortedArrayList.h
 * 동적배열 기법 이용, 중복 허용, 배열을 이용한 비정렬 정수 리스트
 */

#ifndef UNSORTEDARRAYLIST_H_
#define UNSORTEDARRAYLIST_H_
#include <algorithm>
#include <iostream>
#include <stdexcept>
#include <iterator>
#include <initializer_list>

template <typename T>
class UnsortedArrayList {
	template <typename U>
	class ListIterator: public std::iterator<std::input_iterator_tag, U>{
        U *p;
    public:
        explicit ListIterator(U *p) : p{p} {}
        const ListIterator& operator++() noexcept {++p; return *this;}
        ListIterator operator++(int) noexcept {auto retval = *this; ++p; return retval;}
        bool operator==(const ListIterator& other) const noexcept {return p == other.p;}
        bool operator!=(const ListIterator& other) const noexcept {return p != other.p;}
        U& operator*() noexcept {return *p;}
    };
private:
	unsigned int capacity{0};
	T* list{nullptr};
	unsigned int size{0};
	bool needMoreSpace() const noexcept{
		return size==capacity;
	}
	void increaseCapacity(){
		capacity *= 2;
		T *tmp{new T[capacity]};
		std::copy(list, list+size, tmp);
		delete [] list;
		list = tmp;
	}
	int search(const T& key, int startIndex = 0) const noexcept{
		for(int i=startIndex; i<size; i++)
			if(list[i]==key) return i;
		return -1;
	}
public:
	explicit UnsortedArrayList(unsigned int capacity = 10):
		capacity{capacity}, list{new T[capacity]}{}
	explicit UnsortedArrayList(const std::initializer_list<T>& initList):
		UnsortedArrayList(initList.size()){
		for(auto n: initList) pushBack(n);
	}
	UnsortedArrayList(const UnsortedArrayList& other):
		UnsortedArrayList(other.capacity){
		size = other.size;
		std::copy(other.list, other.list+size, list);
	}
	UnsortedArrayList(UnsortedArrayList&& other){
		capacity = other.capacity;
		size = other.size;
		list = other.list;
		other.list = nullptr;
	}
	virtual ~UnsortedArrayList(){
		if(list) delete [] list;
	}
	const UnsortedArrayList& operator=(const UnsortedArrayList& other){
		capacity = other.capacity;
		size = other.size;
		if(list) delete [] list;
		list = new T[capacity];
		std::copy(other.list, other.list+size, list);
		return *this;
	}
	const UnsortedArrayList& operator=(UnsortedArrayList&& other){
		capacity = other.capacity;
		size = other.size;
		if(list) delete [] list;
		list = other.list;
		other.list = nullptr;
		return *this;
	}

	bool isEmpty() const noexcept{
		return size==0;
	}
	bool isFull() const noexcept{
		return false;
	}
	unsigned int getSize() const noexcept{
		return size;
	}
	void clear() noexcept{
		size = 0;
	}

	const T& operator[](int index) const{
		if(index>=0&&index<size) return list[index];
		else throw std::out_of_range("ERROR: [] const");
	}
	T& operator[](int index){
		if(index>=0&&index<size) return list[index];
		else throw std::out_of_range("ERROR: []");
	}

	void pushBack(const T& key){
		if(needMoreSpace()) increaseCapacity();
		list[size] = key;
		++size;
	}
	void pushFront(const T& key){
		if(needMoreSpace()) increaseCapacity();
		for(int i=size; i>=1; i--){
			list[i] = list[i-1];
		}
		list[0] = key;
		++size;
	}
	const T& popBack(){
		if(isEmpty()) throw std::runtime_error("ERROR: popBack, State: empty");
		--size;
		return list[size];
	}
	T popFront(){
		if(isEmpty()) throw std::runtime_error("ERROR: popFront, State: empty");
		T ret{list[0]};
		for(int i=0; i<size-1; i++){
			list[i] = list[i+1];
		}
		--size;
		return ret;
	}
	const T& peekFront() const{
		if(isEmpty()) throw std::runtime_error("ERROR: peekFront, State: empty");
		return list[0];
	}
	const T& peekBack() const{
		if(isEmpty()) throw std::runtime_error("ERROR: peekBack, State: empty");
		return list[size-1];
	}
	bool find(const T& key) const noexcept{
		return search(key)!=-1;
	}
	void removeFirst(const T& key) noexcept{
		int index = search(key);
		if(index!=-1){
			for(int i=index; i<size-1; i++){
				list[i] = list[i+1];
			}
			--size;
		}
	}
	void removeAll(const T& key) noexcept{
		int i = 0;
		int j = 0;
		while(i<size){
			if(list[i]!=key){
				list[j] = list[i];
				++j;
			}
			++i;
		}
		size = j;
	}
	ListIterator<T> begin() {return ListIterator<T>(list);}
	ListIterator<T> end() {return ListIterator<T>(list+size);}
	ListIterator<const T> cbegin() const {return ListIterator<const T>(list);}
	ListIterator<const T> cend() const {return ListIterator<const T>(list+size);}
};

#endif /* UNSORTEDARRAYLIST_H_ */
