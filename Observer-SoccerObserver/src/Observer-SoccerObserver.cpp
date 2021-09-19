/*
 * @file: Observer-SoccerObserver.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 관찰자 패턴 테스트 프로그램
 */

#include <iostream>
#include "Subject.h"

int main() {
	SoccerServer server;
	Observer<std::string>* client1 = new SoccerObserver();
	Observer<std::string>* client2 = new SoccerObserver();

	server.registerObserver(client1);
	server.registerObserver(client2);

	server.updateScore("Korea 1: Brazil 0");
	server.removeObserver(client1);
	server.updateScore("Korea 2: Brazil 1");

	delete client1;
	delete client2;
	return 0;
}
