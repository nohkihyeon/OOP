/*
 * @file: Template-InternetShopping.cpp
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 템플릿 메소드 패턴
 */

#include <iostream>

class InternetShopping {
public:
	virtual ~InternetShopping() = default;
	void processOrder() const{
		doSelect();
		doPayment();
		doDelivery();
	}
protected:
	virtual void doPayment() const = 0;
	virtual void doDelivery() const {
		std::cout << "택배로 전달\n";
	}
private:
	void doSelect() const {
		std::cout << "구입물건 선택\n";
	}
};

class CreditCardPayment: public InternetShopping{
protected:
	void doPayment() const override{
		std::cout << "신용카드로 지불\n";
	}
};

class BankTransferPayment: public InternetShopping{
protected:
	void doPayment() const override{
		std::cout << "계좌이체로 지불\n";
	}
	void doDelivery() const override{
		std::cout << "로켓배송으로 전달\n";
	}
};

void doInternetShopping(const InternetShopping& shopping){
	shopping.processOrder();
}

int main() {
	CreditCardPayment creditCardPayment;
	BankTransferPayment bankTransferPayment;
	doInternetShopping(creditCardPayment);
	doInternetShopping(bankTransferPayment);
	return 0;
}
