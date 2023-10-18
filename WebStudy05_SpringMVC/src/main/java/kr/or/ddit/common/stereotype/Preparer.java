package kr.or.ddit.common.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 어노테이션을 언제까지 살려놓을건지
public @interface Preparer {
//이 어노테이션을 사용했을때 해당 클래스가 빈으로 등록되도록 함

}
