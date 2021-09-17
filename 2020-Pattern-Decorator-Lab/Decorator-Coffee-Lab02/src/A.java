
public class A {
	private int x = 0;
	private String s = "";
	public A() {}
	public A(int x) { this.x = x; }
	public A(int x, String s) {
		this.x = x;
		this.s = s;
	}
	public int getI() {
		return x;
	}
	public String getS() {
		return s;
	}
}
