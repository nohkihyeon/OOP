/*
 * @file: FactoryMethod-Pizza.cpp
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 팩토리 메소드 패턴
 * 테스트 프로그램
 */

#include <iostream>
#include "PizzaStore.h"

void orderPizza(PizzaStore& pizzaStore){
	Pizza* cheesePizza{pizzaStore.orderPizza("치즈")};
	Pizza* potatoPizza{pizzaStore.orderPizza("포테이토")};
	delete cheesePizza;
	delete potatoPizza;
}

int main() {
	NYPizzaStore pizzaStore{};
	orderPizza(pizzaStore);
	return 0;
}
