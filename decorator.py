# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 장식 패턴
# 커피 예제
from abc import ABC, abstractmethod

class Beverage(ABC):    
	def __init__(self, description="이름없는 음료"):        
		self.description = description    
	def getDescription(self):        
		return self.description    
	@abstractmethod    
	def cost(self):        
		pass        

class CondimentDecorator(Beverage):    
	@abstractmethod    
	def getDescription(self):        
		pass

class DarkRoast(Beverage):
	def __init__(self):        
		Beverage.__init__(self, "DarkRoast")        	
	def cost(self):        
		return 2100

class Mocha(CondimentDecorator):
	def __init__(self, beverage):
		if not isinstance(beverage, Beverage):
			raise TypeError("must use Beverage")
		self.beverage = beverage
	def getDescription(self):
		return "%s, Mocha" % self.beverage.getDescription()
	def cost(self):
		return self.beverage.cost()+200

class Whip(CondimentDecorator):
	def __init__(self, beverage):
		if not isinstance(beverage, Beverage):
			raise TypeError("must use Beverage")
		self.beverage = beverage
	def getDescription(self):
		return "%s, Whip" % self.beverage.getDescription()
	def cost(self):
		return self.beverage.cost()+500

beverage = DarkRoast()
beverage = Mocha(beverage)
beverage = Whip(beverage)
beverage = Mocha(beverage)
print (beverage.getDescription())
print (beverage.cost())

