# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 팩토리 메소드 패턴
# 피자 예제
from abc import ABC, abstractmethod

class Pizza(ABC):    
	def __init__(self, name):        
		self.name = name    
	def prepare(self):
		print("준비중: %s" % self.name)
	def bake(self):
	    print("25분 동안 350도에서 굽다")
	def cut(self):	
		print("피자를 8조각으로 짜른다")
	def box(self):    
		print("포장합니다")

class NYCheesePizza(Pizza):
	def __init__(self):
		Pizza.__init__(self, "레지아노 치즈 씬 피자")

class NYPotatoPizza(Pizza):
	def __init__(self):
		Pizza.__init__(self, "포테이토 치즈 씬 피자")

class PizzaStore(ABC):    
	def orderPizza(self, type):
		pizza = self.createPizza(type)
		pizza.prepare()
		pizza.bake()
		pizza.cut()
		pizza.box()
		return pizza
	@abstractmethod
	def createPizza(self, type):
		pass

class NYPizzaStore(PizzaStore):
	def createPizza(self, type):
		if(type=="치즈"):
			return NYCheesePizza()
		elif(type=="포테이토"):
			return NYPotatoPizza()

pizzaStore = NYPizzaStore()
pizza = pizzaStore.orderPizza("치즈")
pizza = pizzaStore.orderPizza("포테이토")
