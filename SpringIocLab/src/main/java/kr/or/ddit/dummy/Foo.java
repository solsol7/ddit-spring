package kr.or.ddit.dummy;

public class Foo {
//	private Bar bar = new Bar();
//	private Baz baz = new Baz();
	
//	private Bar bar = BarFactory.getBar();
	// Foo는 BarFactory와 결합력 가짐
	
//	// 전략패턴 - 전략의 주입자 필요
//	// 결합력 없음
//	private Bar bar;
//	public void setBar(Bar bar) {
//		this.bar = bar;
//	}
	
	private Baz baz;
	
	public Foo(Bar bar, Baz baz) {
		super();
		this.bar = bar;
		this.baz = baz;	
	}
	
	private Bar bar;
	
	public void setBar(Bar bar) {
		this.bar = bar;

	}
	
	@Override
	public String toString() {
		return "Foo [baz=" + baz + ", bar=" + bar + "]";
	}
	
	
}
