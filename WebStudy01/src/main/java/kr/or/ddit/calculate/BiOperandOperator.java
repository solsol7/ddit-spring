package kr.or.ddit.calculate;

@FunctionalInterface
public interface BiOperandOperator {	//functional interface(인터페이스에 구현가능한 메소드 하나)->람다식 적용 가능
	public int operator(int leftOp, int rightOp);
}
