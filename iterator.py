# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 단일 연결구조
# 반복자 패턴
from abc import ABC, abstractmethod

class Node:
	def __init__(self, data):
		self.data = data
		self.next = None

class LinkedList:
	def __init__(self):
		self.__head = None
		self.__size = 0
	def size(self):
		return self.__size
	def isEmpty(self):
		return self.__size==0
	def pushFront(self, data):
		newNode = Node(data)
		newNode.next = self.__head
		self.__head = newNode
		self.__size = self.__size+1
	def popFront(self):
		if(self.isEmpty()): raise RuntimeError
		retval = self.__head.data
		self.__head = self.__head.next
		self.__size = self.__size-1
		return retval
	def __iter__(self):
		self.curr = self.__head
		return self
	def __next__(self):
		if self.curr != None:
			retval = self.curr.data
			self.curr = self.curr.next
			return retval
		else: raise StopIteration

list = LinkedList()
list.pushFront(3)
list.pushFront(5)
list.pushFront(7)
list.pushFront(7)
print(list.popFront())
for e in list:
	print(e)
print(list.size())

