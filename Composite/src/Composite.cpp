/*
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 김상진
 * Composite.cpp
 * Composite Pattern
 */

#include <iostream>
#include <string>
#include "Node.h"

int main() {
	Node<std::string>* root{new NonLeaf<std::string>{"root"}};
	Node<std::string>* node1{new NonLeaf<std::string>{"sub1"}};
	Node<std::string>* node2{new Leaf<std::string>{"apple"}};
	root->addChild(node1);
	root->addChild(node2);
	node1->addChild(new Leaf<std::string>{"grape"});
	node1->addChild(new Leaf<std::string>{"mango"});

	std::string output = "";
	for(auto n: *root)
		output += n+", ";
	std::cout << output << '\n';
	delete root;

	return 0;
}
