/*
 * @file: Observer.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 관찰자 추상 클래스, 구채적 관찰자 클래스
 */

#ifndef OBSERVER_H_
#define OBSERVER_H_

#include <string>
#include <iostream>

template <typename T>
class Observer {
public:
	Observer() = default;
	virtual ~Observer() = default;
	virtual void update(const T& data) = 0;
};

class SoccerObserver: public Observer<std::string>{
public:
	SoccerObserver() = default;
	void update(const std::string& result){
		std::cout << "current score: " << result << '\n';
	}
};

#endif /* OBSERVER_H_ */
