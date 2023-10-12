package kr.or.ddit.case1.auto;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoContextPlayGround {
	public static void main(String[] args) {
		
//		컨테이너 생성 -> 구현체 : 클래스패스xmlapplica~~
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/auto/auto-context.xml");

//		컨테이너는 어플리케이션 종료시 함께 종료
		context.registerShutdownHook();
		
		Case1SamplePresentation presentation = context.getBean(Case1SamplePresentation.class);
		
		int count = context.getBeanDefinitionCount();
		String[] names = context.getBeanDefinitionNames();
		
		log.info("빈 갯수 : {}", count);
//		3개 -> dao, service, presentation
//		5개 -> annotation때문에 생긴애들 (자동으로 등록되는 빈- 어노테이션이 동작할 때 사용되는 객체들)
		log.info("빈 이름들 : {}", Arrays.toString(names));
		
//		presentation은 주입받고 a001받아서 컨텐츠 확인..어쩌구...
		String content = presentation.makeContent("a001");
		
		System.out.println(content);
	}
}
