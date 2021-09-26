/*
 * @copyright 한국기술교육대학교 컴퓨터공학부 자료구조및실습
 * @version 2020년도 2학기
 * @author 김상진
 * SingleUnsortedLinkedList.h
 * 반복자 패턴
 * 단일 연결구조: 범용, 비정렬, 중복 허용, No Tail
 */

#ifndef SINGLEUNSORTEDLINKEDLIST_H_
#define SINGLEUNSORTEDLINKEDLIST_H_
#include <stdexcept>
#include <iterator>
#include <initializer_list>

template <typename T>
class SingleUnsortedLinkedList {
private:
	// =============================== //
	struct Node{
		T item;
		Node* next{nullptr};
		Node() = default;
		Node(const T& value, Node* next=nullptr): item{value}, next{next}{}
		Node(Node* next): next{next}{}
	};
	// =============================== //
	template <typename U>
	class ListIterator: public std::iterator<std::input_iterator_tag,U>{
		Node *p;
	public:
		explicit ListIterator(Node *p) : p{p} {}
		const ListIterator<U>& operator++() noexcept {
			p = p->next; return *this;
		}
		ListIterator<U> operator++(int) noexcept {
			auto retval = *this; p = p->next; return retval;
		}
		bool operator==(const ListIterator<U>& other) const noexcept {return p == other.p;}
		bool operator!=(const ListIterator<U>& other) const noexcept {return p != other.p;}
		U& operator*() noexcept {return p->item;}
	};
	// =============================== //
	Node *head{nullptr};
	unsigned int size{0};

	Node* getTail() const{
		Node* tail{head};
		for(unsigned int i=0; i<size-1; i++){
			tail = tail->next;
		}
		return tail;
	}

	int& get(unsigned int index) const {
		if(index>=size) throw std::range_error("ERROR: get("+std::to_string(index)+")");
		Node *curr{head};
		for (unsigned int i = 0; i < index; i++)
			curr = curr->next;
		return curr->item;
	}
	void removeNode(Node* prev, Node* curr){
		prev->next = curr->next;
		--size;
		delete curr;
	}
	void appendList(const SingleUnsortedLinkedList& other){
		Node* src_it{other.head};
		Node* dest_it{getTail()};
		while(src_it){
			Node* newNode = new Node(src_it->item);
			if(dest_it==nullptr) head = newNode;
			else{
				dest_it->next = newNode;
			}
			dest_it = newNode;
			src_it = src_it->next;
			++size;
		}
	}

public:
	SingleUnsortedLinkedList() = default;
	SingleUnsortedLinkedList(std::initializer_list<T> initList){
		for(auto it = std::rbegin(initList); it != std::rend(initList); ++it)
			pushFront(*it);
	}
	SingleUnsortedLinkedList(const SingleUnsortedLinkedList& other){
		appendList(other);
	}
	SingleUnsortedLinkedList(SingleUnsortedLinkedList&& tmp) noexcept{
		size = tmp.size;
		head = tmp.head;
		tmp.head = nullptr;
	}
	virtual ~SingleUnsortedLinkedList(){
		clear();
	}

	bool isEmpty() const noexcept {
		return head==nullptr;
	}
	bool isFull() const noexcept {
		return false;
	}
	unsigned int getSize() const noexcept {
		return size;
	}
	void clear(){
		Node *curr = head;
		while(curr!=nullptr){
			Node *delNode = curr;
			curr = curr->next;
			delete delNode;
		}
		head = nullptr;
		size = 0;
	}

	const SingleUnsortedLinkedList& operator=(const SingleUnsortedLinkedList& other){
		clear();
		appendList(other);
		return *this;
	}
	const SingleUnsortedLinkedList& operator=(SingleUnsortedLinkedList&& tmp){
		clear();
		size = tmp.size;
		head = tmp.head;
		tmp.head = nullptr;
		return *this;
	}
	const T& operator[](int index) const{
		return get(index);
	}
	T& operator[](int index){
		return get(index);
	}

	void pushFront(const T& value) noexcept {
		Node* newNode{new Node(value)};
		newNode->next = head;
		head = newNode;
		++size;
	}
	const T& peekFront() const{
		if(isEmpty()) throw std::runtime_error("ERROR: peekFront");
		return head->item;
	}
	T popFront(){
		if(isEmpty()) throw std::runtime_error("ERROR: popFront");
		Node* deleteNode{head};
		T retval{deleteNode->item};
		head = head->next;
		delete deleteNode;
		--size;
		return retval;
	}
	void pushBack(const T& value) noexcept {
		Node* newNode{new Node(value)};
		if(isEmpty()) pushFront(value);
		else{
			Node* tail{getTail()};
			tail->next = newNode;
			++size;
		}
	}
	const T& peekBack() const{
		if(isEmpty()) throw std::runtime_error("ERROR: peekBack");
		return getTail()->item;
	}
	T popBack(){
		if(isEmpty()) throw std::runtime_error("ERROR: popBack");
		if(size==1) return popFront();
		else{
			Node* prev{head};
			Node* curr{head->next};
			while(curr->next){
				prev = curr;
				curr = curr->next;
			}
			prev->next = nullptr;
			--size;
			T retval{curr->item};
			delete curr;
			return retval;
		}
	}
	bool find(const T& key) const noexcept{
		if(isEmpty()) return false;
		Node* curr{head};
		while(curr){
			if(curr->item==key) return true;
			curr = curr->next;
		}
		return false;
	}
	void removeFirst(const T& key) noexcept{
		if(isEmpty()) return;
		Node dummy{head};
		Node* prev{&dummy};
		Node* curr{head};
		while(curr){
			if(curr->item==key){
				removeNode(prev, curr);
				head = dummy.next;
				break;
			}
			else{
				prev = curr;
				curr = curr->next;
			}
		}
	}
	void removeAll(const T& key) noexcept{
		if(isEmpty()) return;
		Node dummy{head};
		Node* prev{&dummy};
		Node* curr{head};
		while(curr){
			if(curr->item==key){
				Node* next = curr->next;
				removeNode(prev, curr);
				curr = next;
			}
			else{
				prev = curr;
				curr = curr->next;
			}
		}
		head = dummy.next;
	}
	ListIterator<T> begin() { return ListIterator<T>(head); }
	ListIterator<T> end() { return ListIterator<T>(nullptr); }
	ListIterator<const T> cbegin() const { return ListIterator<const T>(head); }
	ListIterator<const T> cend() const { return ListIterator<const T>(nullptr); }

};

#endif /* SINGLEUNSORTEDLINKEDLIST_H_ */
