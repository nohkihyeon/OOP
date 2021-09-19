/*
 * @file: Subject.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 관찰대상 추상 클래스, 구채적 관찰대상 클래스
 */

#ifndef SUBJECT_H_
#define SUBJECT_H_

#include <algorithm>
#include <string>
#include <vector>
#include "Observer.h"

template <typename T>
class Subject {
private:
	std::vector<Observer<T>*> observers;
public:
	Subject() = default;
	virtual ~Subject() = default;
	void registerObserver(Observer<T>* observer){
		observers.push_back(observer);
	}
	void removeObserver(const Observer<T>* observer){
		observers.erase(
			std::remove(observers.begin(),observers.end(),observer),observers.end());
	}
	void notifyObservers(const T& data){
		for(auto o: observers) o->update(data);
	}
};

class SoccerServer: public Subject<std::string>{
private:
	std::string currentScore;
public:
	void updateScore(const std::string& score){
		currentScore = score;
		notifyObservers(currentScore);
	}

};

#endif /* SUBJECT_H_ */
