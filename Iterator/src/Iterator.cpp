/*
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 반복자 패턴
 * Iterator.cpp
 * 테스트 프로그램
 */

#include <iostream>
#include "UnsortedArrayList.h"
#include "SingleUnsortedLinkedList.h"


void test_UnsortedArrayList(){
	UnsortedArrayList<std::string> list1{"apple", "grape", "banana", "melon", "kiwi", "mango"};
	for(auto fruit: list1){
		std::cout << fruit << ", ";
	}
	std::cout << '\n';

	UnsortedArrayList<int> list2{5, 4, 1, 10, 3, 6, 2};
	for(auto n: list2){
		std::cout << n << ", ";
	}
	std::cout << '\n';
}

void test_UnsortedLinkedList(){
	SingleUnsortedLinkedList<std::string> list1{"apple", "grape", "banana", "melon", "kiwi", "mango"};
	for(auto fruit: list1){
		std::cout << fruit << ", ";
	}
	std::cout << '\n';

	SingleUnsortedLinkedList<int> list2{5, 4, 1, 10, 3, 6, 2};
	for(auto n: list2){
		std::cout << n << ", ";
	}
	std::cout << '\n';

	auto it = list1.begin();
	*it = "strawberry";

	for(auto fruit: list1){
		std::cout << fruit << ", ";
	}
	std::cout << '\n';

	auto cit = list1.cbegin();
	//*cit = "xxx";

	while(cit!=list1.cend()){
		std::cout << *cit << ", ";
		++cit;
	}
	std::cout << '\n';

}


int main() {
	test_UnsortedArrayList();
	test_UnsortedLinkedList();
	return 0;

}
