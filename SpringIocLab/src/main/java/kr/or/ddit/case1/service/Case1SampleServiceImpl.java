package kr.or.ddit.case1.service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case1.dao.Case1SampleDAO;
import kr.or.ddit.case1.dao.Case1SampleDAOFactory;
import kr.or.ddit.case1.dao.Case1SampleDAOImpl_Mysql;
import kr.or.ddit.case1.dao.Case1SampleDAOImpl_Oracle;
import lombok.extern.slf4j.Slf4j;

@Service("service")
@Slf4j
public class Case1SampleServiceImpl implements Case1SampleService {
	
//	1. new 키워드로 의존 객체를 직접 생성(의존 관계에 묶여 두 객체 사이 결합력이 최상)
//	private Case1SampleDAO dao = new Case1SampleDAOImpl_Oracle();

//	2. Factory Object Pattern(factory 객체와 의존 관계에 묶인 다른 객체들간의 결합력 발생)
//	private Case1SampleDAO dao = Case1SampleDAOFactory.getCase1SampleDAO();

//	3. Strategy Pattern(전략 패턴, Dependency Injection) - 전략의 주입자(모든 결합력의 책임)가 필요함 --> container
	
	private Case1SampleDAO dao;
	
	public Case1SampleServiceImpl() {
		log.info("{} 생성되었음.", this.getClass().getSimpleName());
	}

	public void init() {
		log.info("{} 객체 생성 후 초기화 완료.",this.getClass().getSimpleName());
	}

	public void destroy() {
		log.info("{} 객체 소멸.",this.getClass().getSimpleName());
	}
	
//	@Autowired //	관계 형성을 자동으로 해줌 - 파라미터타입의 구현체가 빈에 등록되어있으면 그걸 주입해라
		// 주입할 대상으로 type 으로 검색함.
		// autowired 할때 식별 조건으로 사용할건 타입밖에 없는데 빈에 등록된 그 인터페이스의 구현체가 2개이상이면 exception뜸
		// -> 대신 쓸 수 있는게 @Resource -> name속성은 id값(오토인젝션이면 클래스이름에 첫글자 소문자)
		// 스프링에서만 쓸 수 있음 - Resource 쓰는게 프레임워크와의 종속성 낮춤
//	@Resource(name = "case1SampleDAOImpl_Oracle")
		// jdk, jvm만 있으면 언제든지 쓸 수 있음
		// 근데 이건 빈에 등록된 구현체 하나여도 조건 줘야함
	@Inject  // 메이븐 디펜던시 필요(autowired하고 똑같은 방식으로 동작함, 근데 java꺼)
	@Named("case1SampleDAOImpl_Mysql")	// Inject 쓸 때 구현체 두 개 이상이면
	public void setDao(Case1SampleDAO dao) {
		this.dao = dao;
		log.info("{} 를 setter injection 으로 주입받음", dao.getClass().getSimpleName());
	}
	
	@Override
	public StringBuffer retrieveSample(String pk) {
		String rawData = dao.selectSample(pk);
		StringBuffer model = new StringBuffer();
		model.append(rawData);
		model.append("를 가공한 infomation");
		return model;
	}

}
