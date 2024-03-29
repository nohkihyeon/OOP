/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * Composite Pattern
 * OperatorNode.java
 * 자식을 가지는 연산자 노드
 * @author 김상진
 */
public class OperatorNode extends Node {
	private Operator operator;
	private Node left;
	private Node right;
	public OperatorNode(Operator operator, Node left, Node right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	public double evaluate(){
		return operator.evaluate(left, right);
	}
}
