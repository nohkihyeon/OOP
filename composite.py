# 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
# 2020년도 2학기
# 김상진
# 합성 패턴
from abc import ABC, abstractmethod

class Node(ABC):
	def __init__(self, name):
		self.__name = name
		self.__hasChanged = False
	def getName(self):
		return self.__name
	def setChanged(self, flag):
		self.__hasChanged = flag
	def hasChanged(self):
		return self.__hasChanged
	def numberOfChilds(self):
		return 0	
	def __eq__(self, other):
		if isinstance(other, Node):
			return self.__name == other.__name and self.__hasChanged == other.__hasChanged
		else: return False
	@abstractmethod
	def add(self, node):
		pass
	@abstractmethod
	def remove(self, node):
		pass
	@abstractmethod
	def getChild(self, index):
		pass
	@abstractmethod	
	def __iter__(self):
		pass
	@abstractmethod	
	def __next__(self):
		pass

class NonLeaf(Node):
	def __init__(self, name):
		Node.__init__(self, name)
		self.__childs = []
	def numberOfChilds(self):
		return 0	
	def add(self, node):
		self.__childs.append(node)
	def remove(self, node):
		self.__childs.remove(node)
	def getChild(self, index):
		return len(self.__childs)
	def __iter__(self):
		self.queue = []
		self.queue.append(self)
		return self
	def __next__(self):
		if len(self.queue)==0: raise StopIteration
		else:
			retval = self.queue.pop(0)
			if isinstance(retval, NonLeaf): 
				for node in retval.__childs:
					self.queue.append(node)
			return retval

class Leaf(Node):
	def __init__(self, name):
		Node.__init__(self, name)
	def add(self, node):
		pass
	def remove(self, node):
		pass
	def getChild(self, index):
		pass
	def __iter__(self):
		pass
	def __next__(self):
		pass

root = NonLeaf("root")
sub1 = NonLeaf("sub1")
sub2 = NonLeaf("sub2")
file1 = Leaf("a1")
file2 = Leaf("c1")
file1.setChanged(True)
file2.setChanged(True)
root.add(file1)
root.add(sub1)
root.add(Leaf("a2"))
root.add(sub2)
sub1.add(Leaf("b1"))
sub1.add(Leaf("b2"))
sub2.add(file2)
sub2.add(Leaf("c2"))
sub2.add(Leaf("c3"))
sub2.remove(Leaf("c2"))
for node in root:
	print(node.getName())
for node in root:
	if node.hasChanged():
		print(node.getName())