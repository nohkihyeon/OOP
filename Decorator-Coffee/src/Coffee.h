/*
 * @file: Coffee.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 장식 패턴
 */

#ifndef COFFEE_H_
#define COFFEE_H_
#include <string>
#include <memory>

class Beverage {
private:
	std::string description;
public:
	explicit Beverage(const std::string& description="Unknown Beverage") noexcept
		: description(description){}
 	virtual ~Beverage() = default;
	virtual std::string getDescription() const noexcept {
		return description;
	}
	virtual int cost() const noexcept = 0;
};

class DarkRoast final: public Beverage {
public:
	explicit DarkRoast() noexcept: Beverage(std::string("Dark Roast")){}
	virtual ~DarkRoast() = default;
	int cost() const noexcept final { return 2000; }
};

class CondimentDecorator: public Beverage {
public:
	explicit CondimentDecorator() = default;
	virtual ~CondimentDecorator() = default;
	std::string getDescription() const noexcept override = 0;
};

class Mocha final: public CondimentDecorator {
private:
	Beverage* beverage;
public:
	explicit Mocha(Beverage* beverage) noexcept: beverage{beverage}{}
	virtual ~Mocha(){
		delete beverage;
	}
	std::string getDescription() const noexcept final {
		return beverage->getDescription()+", Mocha";
	}
	int cost() const noexcept final {
		return 200 + beverage->cost();
	}
};

class Whip final: public CondimentDecorator {
private:
	Beverage* beverage;
public:
	explicit Whip(Beverage* beverage) noexcept: beverage{beverage}{}
	virtual ~Whip(){
		delete beverage;
	}
	std::string getDescription() const noexcept final {
		return beverage->getDescription()+", Mocha";
	}
	int cost() const noexcept final {
		return 200 + beverage->cost();
	}
};

#endif /* COFFEE_H_ */
