# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 김상진
# 싱글톤 패턴
from abc import ABC, abstractmethod

'''
기존 다른 언어와 유사한 방법 (lazy instantiation)
'''
class SingletonV1:
	__unique = None
	def __init__(self):
		if SingletonV1.__unique is not None:
			raise ValueError("")
		self.n = 0
	@classmethod
	def getInstance(cls):
		if not cls.__unique:
			cls.__unique = SingletonV1()
		return cls.__unique
	def get(self):
		return self.n
	def set(self, value):
		self.n = value
	def increase(self):
		self.n += 1

'''
__Holder는 내부 클래스이기 때문에 밖에서 접근 불가
내부 클래스에 유일 객체가 제공해야 할 기능 구현
외부 클래스는 생성 제한
내부 객체에 대한 접근은 __getattr__과 __setattr__ 이용
'''
class SingletonV2:
	class __Holder:
		def __init__(self):
			self.n = 0
		def get(self):
			return self.n
		def increase(self):
			self.n += 1
		def set(self, value):
			self.n = value
	__unique = None
	def __new__(cls):
		if not SingletonV2.__unique:
			SingletonV2.__unique = SingletonV2.__Holder()
		return SingletonV2.__unique
	def __getattr__(self, attr):         
		return getattr(self.__unique, attr)
	def __setattr__(self, attr, value):        
		return setattr(self.__instance, attr, value)

'''
decorator 이용하는 방법
'''
def singleton(cls):     
	instances = {}     
	def getInstance():         
		if cls not in instances:             
			instances[cls] = cls()         
		return instances[cls]    
	return getInstance    

@singleton
class SingletonV3:    
	def __init__(self):        
		self.n = 0    
	def get(self):
		return self.n
	def set(self, value):
		self.n = value
	def increase(self):
		self.n += 1
'''
s = SingletonV1.getInstance()
x = SingletonV1.getInstance()

s = SingletonV2()
x = SingletonV2()
'''

s = SingletonV3()
x = SingletonV3()

print (hex(id(s)))
print (hex(id(x)))
x.increase()
print (s.get())
print (x.get())
s.increase()
print (s.get())
print (x.get())
s.set(5)
print (s.get())
print (x.get())




