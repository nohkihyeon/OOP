/*
 * @file: PizzaStore.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 팩토리 메소드 패턴
 * 피자 가게
 */
#ifndef PIZZASTORE_H_
#define PIZZASTORE_H_
#include "Pizza.h"

class PizzaStore {
public:
	PizzaStore() = default;
	virtual ~PizzaStore() = default;
	Pizza* orderPizza(const std::string& type){
		Pizza* pizza = createPizza(type);
		pizza->prepare();
		pizza->bake();
		pizza->cut();
		pizza->box();
		return pizza;
	}
	virtual Pizza* createPizza(const std::string& type) const = 0;
};

class NYPizzaStore: public PizzaStore{
protected:
	Pizza* createPizza(const std::string& type) const override {
		if(type=="치즈")
			return new NYCheesePizza{};
		else if(type=="포테이토")
			return new NYPotatoPizza{};
		return nullptr;
	}
};


#endif /* PIZZASTORE_H_ */
