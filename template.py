# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 김상진
# template 메소드 패턴
from abc import ABC, abstractmethod
from typing import final

class InternetShopping(ABC):
	@final
	def processOrder(self):
		self.doSelect()
		self.doPayment()
		self.doDelivery()
	@final
	def doSelect(self):
		print("구입물건 선택")
	def doDelivery(self):
		print("택배로 전달")
	@abstractmethod	
	def doPayment(self):
		pass

class CreditCardPayment(InternetShopping):
	def doPayment(self):
		print("신용카드로 지불")

class BankTransferPayment(InternetShopping):
	def doDelivery(self):
		print("로켓배송으로 전달")
	def doPayment(self):
		print("계좌이체로 지불")

shopping1 = CreditCardPayment()
shopping2 = BankTransferPayment()
shopping1.processOrder()
shopping2.processOrder()

