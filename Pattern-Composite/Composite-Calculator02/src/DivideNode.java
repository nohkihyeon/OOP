/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * Composite Pattern
 * MultiplyNode.java
 * 자식을 가지는 연산자 노드 중 나눗셈 노드
 * @author 김상진
 */
public class DivideNode extends OperatorNode {
	public DivideNode(Node left, Node right) {
		super(left, right);
	}
	@Override
	public double evaluate() {
		return left.evaluate()/right.evaluate();
	}
}
