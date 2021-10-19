# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 관찰자 패턴
# 축구 예제
from abc import ABC, abstractmethod

class Subject(ABC):    
	def __init__(self):        
		self.observers = set()    
	def registerObserver(self, observer):        
		self.observers.add(observer)    
	def removeObserver(self, observer):        
		self.observers.remove(observer)    
	@abstractmethod    
	def notifyObservers(self):        
		pass        

class Observer(ABC):    
	@abstractmethod    
	def update(self, data):        
		pass

class SoccerServer(Subject):
	def __init__(self):        
		Subject.__init__(self)        
		self.currentScore = ""    
	def updateScore(self, score):        
		self.currentScore = score        
		self.notifyObservers()    
	def notifyObservers(self):        
		for observer in self.observers:            
			observer.update(self.currentScore)

class SoccerObserver(Observer):  
	def __init__(self, id):        
		Observer.__init__(self)
		self.id = id
	def __eq__(self, other):
		return self.id == other.id
	def __hash__(self):
		return hash(self.id)		  
	def update(self, score):        
		print(self.id+">> 현재 점수: "+score)

server = SoccerServer()
client1 = SoccerObserver("손흥민")
client2 = SoccerObserver("황희찬")
server.registerObserver(client1)
server.updateScore("Tottenham Hotspur 1: Wolverhampton 0")
server.registerObserver(client2)
server.updateScore("Tottenham Hotspur 1: Wolverhampton 1")
server.removeObserver(client1)
server.updateScore("Tottenham Hotspur 1: Wolverhampton 2")

