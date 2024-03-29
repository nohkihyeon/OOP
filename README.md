# 객체지향 프로그래밍
## 1. 역사
 - 객체지향(OO, Object-Oriented) 프로그래밍 언어가 지배적인 프로그래밍 언어로 자리잡기 이전에 널리 사용된 고급 프로그래밍 언어는 FORTRAN, COBOL 등이다. C 언어는 1972년에 Bell Labs의 Dennis Ritchie가 개 발한 범용 프로그래밍 언어이다. C 표준은 1983년에 시작되어 1988년에 처음으로 표준화되었으며, 이를 ANSI C 또는 ISO C라 한다. 그 이후 C99, C11으로 표준이 일부 개선되었다. C 언어는 운영체제를 개발하기 위해 만들어진 언어로 고급 프로그래밍 언어이지만 하드웨어와 밀접한 수준의 요소도 포함되어 있다. 또한 당시 고급 프로그래밍 언어를 이용하여 개발하는 응용이 매우 제한적이었다. 지금의 4차 산업혁명, 빅데이터, 인공지능, 사물인터넷(IoT, Internet of Things) 시대와는 비교하기가 어렵다. 어떤 프로그래밍 언어가 가지게 되는 특성은 그 언어를 개발하게 된 목적과 개발 시점의 컴퓨팅 환경에 많은 영향을 받을 수밖에 없다.

 - 정보화 시대의 발전에 따라 함수가 중심이 되는 모듈화 프로그래밍 방법으로는 그 이후 요구된 다양한 응용을 개 발하는데 어려움이 있었다. 또한 인간이 생각하는 시각을 그대로 프로그래밍 언어로 표현할 수 없었고, 프로그래머는 자신의 머리속에 있는 생각을 컴퓨터의 시각으로 전환해야 하는 어려움도 있었다. 이에 보통 사람이 생각하는 시각 을 그대로 프로그래밍 언어로 옮길 수 있는 객체지향 프로그래밍 패러다임이 자연스럽게 지배적인 개발 패러다임이 되었다. 90년대 초반 마이크로소프 윈도우 응용의 개발 붐과 함께 두각을 나타내기 시작한 객체지향 프로그래밍이 지만 30년이 지난 지금도 놀랍게 발전한 IT 환경에도 불구하고 여전히 지배적인 프로그래밍 패러다임으로 자리 잡고 있다.
 
 ## 2. 객체지향 특징
 - 우리는 어떤 사물을 인식할 때 그 사물의 특성과 그 사물을 가지고 할 수 있는 것이 무엇인지 파악하게 된다. 
 - 예를 들어 문(door)이 있으면 문의 크기, 문의 위치, 문의 현재 상태와 같은 특성과 문을 가지고 할 수 있는 문 열기, 문 닫기와 같은 기능을 인식하게 된다. 객체지향 프로그래밍은 문의 특성과 기능을 우리가 실제 인식하는 것과 동일하게 프로그래밍 언어로 표현할 수 있게 해준다.

 ### 2.1 객체와 클래스
 객체지향 프로그래밍의 핵심은 객체(object)이다. 우리가 어떤 프로그램을 객체지향 방법을 사용하여 개발할 경우 이 프로그램에서 모델링해야 하는 객체가 어떤 것이 있는지 찾아야 하며, 이들 객체 간의 관계를 잘 구조화하여야 한다. 프로그램은 이들 객체를 필요한 시점에 생성하여 서로 상호작용하도록 하여 우리가 원하는 기능이 동작되도록 해야 한다. 객체지향 개념에서 객체는 상태, 행위, 식별자, 3가지 요소를 가진다. 여기서 가장 중요한 것이 상태이다. 동일한 종류의 객체가 여러 개 있을 수 있지만 각 객체의 상태는 다를 수 있다. 예를 들어 여러 강아지가 있으면 어떤 강아지는 배가 고플 수 있고, 어떤 강아지는 현재 졸릴 수 있다.
 객체는 현재 상태에 따라 행위의 결과가 달라질 수 있다. 이전 설명에서 강아지에게 간식을 주더라도 강아지 의 상태에 따라 간식을 먹지 않을 수 있고, 먹는 양도 다를 수 있다. 이것이 객체지향의 핵심이다. 따라서 객체의 행위는 객체의 상태를 이용해야 하며, 상태를 이용하지 않는 것은 객체의 행위로 분류하지 않는다. 한 종류의 객체 가 여러 개 존재할 수 있고, 한 객체를 여러 번 조작할 수 있기 때문에 객체를 구분하기 위한 식별자가 필요하다. 프로그래밍에서는 변수가 식별자 역할을 한다.
 
 ### 2.2 객체로 모델링되는 것
 객체지향 프로그래밍으로 프로그램을 개발한다고 하여 프로그램에 필요한 모든 요소(데이터와 기능)를 객체로 모델 링해야 하는 것은 아니다. 객체로 모델링하였을 때 효과적인 것만 객체로 모델링해야 한다. 예를 들어 학생, 강아지, 문과 같은 일상 개체를 있는 그대로 프로그램 내에 모델링해야 하면 객체로 모델링하는 것이 매우 효과적이다. 일상 개체뿐만 아니라 기존 C에서 구조체로 모델링한 복합타입의 데이터이면 객체지향에서는 이를 객체로 모델링한다. 이때 데이터뿐만 아니라 이 데이터를 조작, 처리하는 함수를 클래스의 메소드로 함께 정의하여 사용한다.
프로그램에서 필요한 데이터뿐만 아니라 필요한 기능도 기능의 특성에 따라 객체로 모델링할 수 있다. 특히 기능이 과거에 대한 기억이 필요하면 객체로 모델링하는 것이 매우 효과적이다. 여기서 과거에 대한 기억이란 해당 기능의 사용 이력이나 이전에 어디까지 수행하였는지를 말한다. 예를 들어 주어진 문장에서 각 단어를 분석하는 기능인데, 요청할 때마다 문장을 구성하는 각 단어를 차례로 주어야 하면 지금까지 처리한 위치와 문장을 기억하고 있어야 하므로 객체로 모델링하기에 적합한 기능이다.
보통 인자만 가지고 기능을 온전히 구현할 수 있는 기능이면 객체로 모델링할 필요가 없다. 얘를 들어 sqrt, pow와 같은 수학 함수는 과거에 대한 기억이 필요 없고 인자만 가지고 필요한 계산을 할 수 있기 때문에 객체로 모델링하지 않는다. 또 다른 예로 문자열에서 특정 문자의 첫 번째 등장 위치를 찾는 기능을 생각하여 보자. 이 기능도 문자열과 문자를 인자로 받아 구현할 수 있기 때문에 다음과 같이 일반 함수로 정의할 수 있다.
 
```java
 class StringUtill{
     public static int indexOf(STring str, char c) {}
 }
 ```
 
하지만 인자 중 복합 타입이 있으면 그 인자를 중심으로 기능을 호출하는 형태로 정의할 수 있다. 객체지향은 데이 터와 그 데이터를 처리하는 함수를 하나의 틀로 정의하여 사용한다는 측면에서 indexOf 기능을 문자열의 메소드로 구현하여 사용할 수 있다.
인자만 가지고 온전히 구현할 수 있는 기능이더라도 이 기능을 구현할 때 필요한 인자 중 하나를 중심으로 그 인자 타입의 메소드로 구현하여 사용할 수 있다. 이때 해당 인자는 클래스로 정의하기 적합한 복합타입이거나 논리적으로 새 타입을 정의하여 사용하는 것이 필요한 인자3이어야 한다.
두 가지 방법 중 어느 것이 더 좋은 방법인지는 단정하기 어렵다. 언어의 특성, 타입의 특성, 코드 중복이라는 측면에서 살펴보아야 한다. 예를 들어 indexOf가 많은 복합타입에서 동일 알고리즘으로 구현될 수 있는 기능이면 각 타입마다 중복하여 정의하지 않고 범용 함수로 정의하는 것이 더 효과적이다.
 


