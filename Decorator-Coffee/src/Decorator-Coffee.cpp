/*
 * @file: Decorator-Coffee.cpp
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 장식 패턴
 */

#include <iostream>
#include "Coffee.h"

int main() {
	Beverage *beverage{new DarkRoast()};
	beverage = new Mocha(beverage);
	beverage = new Whip(beverage);
	std::cout << beverage->getDescription()
		<< ": " << beverage->cost() << "원" << std::endl;

	delete beverage;
	return 0;
}
