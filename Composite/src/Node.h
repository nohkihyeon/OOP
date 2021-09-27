/*
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진
 * Node.h
 * Composite Pattern
 */

#ifndef NODE_H_
#define NODE_H_
#include <stdexcept>
#include <iterator>
#include <queue>
#include <vector>

template <typename T>
class Node{
protected:
	T key;
	// 순회 때 사용
	std::vector<T> list;
public:
	Node(const T& key): key{key}{}
	virtual ~Node() = default;
	void setValue(const T& key){ this->key = key; }
	const T& getValue() const { return key;	}
	virtual int numOfChilds() const { return 0; }
	virtual void addChild(Node<T>* node) = 0;
	virtual const Node<T>* getChild(unsigned int index) const = 0;
	virtual Node<T>* getChild(unsigned int index) = 0;
	virtual void removeChild(unsigned int index) = 0;
	virtual typename std::vector<T>::iterator begin(){
		return list.begin();
	}
	virtual typename std::vector<T>::iterator end(){
		return list.end();
	}
};

template <typename T>
class NonLeaf: public Node<T>{
private:
	// 자식 목록
	std::vector<Node<T>*> childs;
public:
	NonLeaf() = default;
	NonLeaf(const T& key): Node<T>{key}{}
	virtual ~NonLeaf(){
		for(auto node: childs)
			delete node;
	}
 	int numOfChilds() const override {
 		return childs.size();
 	}
	void addChild(Node<T>* node) override {
		childs.push_back(node);
	}
	const Node<T>* getChild(unsigned int index) const override {
		if(index>childs.size()) throw std::range_error("getChild: const");
		return childs[index];
	}
	Node<T>* getChild(unsigned int index) override {
		if(index>childs.size()) throw std::range_error("getChild");
		return childs[index];
	}
	void removeChild(unsigned int index) override {
		assert(index<childs.size());
		childs.erase(childs.begin()+index);
	}
	typename std::vector<T>::iterator begin() override{
		bfs();
		return this->list.begin();
	}
private:
	void bfs(){
		this->list.clear();
		std::queue<Node<T>*> bfs;
		bfs.push(this);
		while(!bfs.empty()){
			auto curr{bfs.front()};
			this->list.push_back(curr->getValue());
			bfs.pop();
			for(int i=0; i<curr->numOfChilds(); i++){
				bfs.push(curr->getChild(i));
			}
		}
	}
};

template <typename T>
class Leaf: public Node<T>{
public:
	Leaf(const T& key): Node<T>{key}{}
	virtual ~Leaf() = default;
 	void addChild(Node<T>* node) override {}
 	const Node<T>* getChild(unsigned int index) const override {
 		return nullptr;
 	}
 	Node<T>* getChild(unsigned int index) override {
 		return nullptr;
 	}
 	void removeChild(unsigned int index) override {}
};

#endif /* NODE_H_ */
