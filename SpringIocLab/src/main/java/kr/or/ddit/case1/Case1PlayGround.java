package kr.or.ddit.case1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.dao.Case1SampleDAO;
import kr.or.ddit.case1.dao.Case1SampleDAOImpl_Mysql;
import kr.or.ddit.case1.dao.Case1SampleDAOImpl_Oracle;
import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import kr.or.ddit.case1.service.Case1SampleService;
import kr.or.ddit.case1.service.Case1SampleServiceImpl;

public class Case1PlayGround {
	public static void main(String[] args) {
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/Case1-Context.xml");
//		어디에 있는 어떤 xml에 bean 등록돼있으니까 그거 관리해줘 -> 파라미터로 넘김
		
		Case1SamplePresentation presentation = context.getBean(Case1SamplePresentation.class);
//		bean을 식별할 수 있는 것으로 class, id... 등등 다 쓸 수 있음
//		파라미터 String인거는 반환타입 Object-> 캐스팅해가면서 써야함
//		파라미터 제네릭으로 되어있는건 반환타입도 제네릭이라 걍 쓸수 있음
		
		String pk = "a001";
		String content = presentation.makeContent(pk);
		
		System.out.println(content);
	}
}
