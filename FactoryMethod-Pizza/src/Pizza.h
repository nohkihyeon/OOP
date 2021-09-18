/*
 * @file: Pizza.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 팩토리 메소드 패턴
 * 피자
 */

#ifndef PIZZA_H_
#define PIZZA_H_
#include <iostream>
#include <string>

class Pizza {
private:
	std::string name;
public:
	Pizza() = default;
	Pizza(const std::string& name): name(name){}
	virtual ~Pizza() = default;
	void prepare(){
		std::cout << "준비중: " << name << "\n";
	}
	void bake(){
		std::cout << "25분 동안 350도에서 굽다.\n";
	}
	void cut(){
		std::cout << "피자를 8조각으로 짜른다.\n";
	}
	void box(){
		std::cout << "포장합니다!\n";
	}
};

class NYCheesePizza: public Pizza{
public:
	NYCheesePizza(const std::string& name="레지아노 치즈 씬 피자"): Pizza{name} {}
};

class NYPotatoPizza: public Pizza{
public:
	NYPotatoPizza(const std::string& name="포테이토 치즈 씬 피자"): Pizza{name} {}
};

#endif /* PIZZA_H_ */
