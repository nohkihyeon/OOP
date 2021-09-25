# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 김상진
# 전략 패턴
# 오리 예제. 나는 전략
from abc import ABC, abstractmethod

class FlyBehavior(ABC):    
	@abstractmethod
	def fly(self):        
		pass

class FlyWithRocket(FlyBehavior):    
	def fly(self):        
		print("Flying with rocket")

class FlyWithWings(FlyBehavior):    
	def fly(self):        
		print("Flying with wings")

class FlyNoWay(FlyBehavior):    
	def fly(self):        
		print("Can't fly")

class Duck(ABC):    
	def __init__(self, flybehavior):        
		self.setFlyingBehavior(flybehavior)
	def setFlyingBehavior(self, flybehavior):        
		if not isinstance(flybehavior, FlyBehavior):            
			raise TypeError("must use FlyBehavior")        
		self.flybehavior = flybehavior    
	def fly(self):        
		self.flybehavior.fly()    
	@abstractmethod    
	def display(self):        
		pass

class MallardDuck(Duck):    
	def __init__(self, flybehavior):        
		Duck.__init__(self, flybehavior)  
	def display(self):        
		print("I'am a MallardDuck")

duck = MallardDuck(FlyWithWings())
duck.display()
duck.fly()
duck.setFlyingBehavior(FlyWithRocket())
duck.fly()