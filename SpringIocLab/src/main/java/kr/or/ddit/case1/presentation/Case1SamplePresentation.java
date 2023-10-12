package kr.or.ddit.case1.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.case1.service.Case1SampleService;
import kr.or.ddit.case1.service.Case1SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Case1SamplePresentation {
	
	private Case1SampleService service;
	
	public Case1SamplePresentation() {
		super();
		log.info("기본 생성자로 {} 생성", this.getClass().getSimpleName());
	}

//	기본생성자 없고 갖고있는 생성자가 이것밖에 없음 - 근데 service가 필요해 - 컨테이너가 등록된 빈들 뒤져서 알아서 주입함 
	@Autowired
	public Case1SamplePresentation(Case1SampleService service) {
		super();
		this.service = service;
		log.info("{} 생성되었음. 생성자 주입으로 {} 로 주입받음."
				, this.getClass().getSimpleName(), service.getClass().getSimpleName());
	}
	
//	주입방법 두가지 -> 기본생성자 없으면 무조건 생성자 통해 주입됨
//	기본생성자 있으면 뭘로 주입할지 모름 - 
//	반드시 setter로 주입할거라면 autowired 어노테이션
//	@Autowired
	public void setService(Case1SampleService service) {
		this.service = service;
		log.info("setter 주입으로 {} 로 주입받음.", service.getClass().getSimpleName());
	}

	public String makeContent(String pk) {
		StringBuffer model = service.retrieveSample(pk);
		return String.format("%s 를 꾸며서 만들어진 content", model);
	}
}
