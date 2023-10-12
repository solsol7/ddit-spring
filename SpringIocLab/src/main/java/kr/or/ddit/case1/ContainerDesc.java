package kr.or.ddit.case1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerDesc {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/Container-Desc.xml");
//		kr은 클래스패스 리소스니까 거기서부터 뒤져 -> prefix(classpath:)
		context.registerShutdownHook();
//		종료 예약
		
//		Case1SamplePresentation presentation1 = context.getBean(Case1SamplePresentation.class);
//		Case1SamplePresentation presentation2 = context.getBean(Case1SamplePresentation.class);
//		log.info("주입된 객체 비교 (==) : {}",presentation1 == presentation2);
//			객체의 주소가 동일한 경우 true -> 똑같은 객체라는 뜻
	}
}

//Spring -> 등록되어있는 bean을 싱글톤으로 관리