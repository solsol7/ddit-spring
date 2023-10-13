package kr.or.ddit.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.jdbc.dao.BuyerDAO;
import kr.or.ddit.jdbc.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/jdbc/conf/*-context.xml")
class MybatisContextTest {
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Inject
	private SqlSessionTemplate session;
	
	@Inject
	private BuyerDAO dao;
	
	@Inject
	private BuyerServiceImpl service;
	
	@Test
	void test() {
		log.info("주입된 객체 : {}", sqlSessionFactory);
	}

	@Test
	void test2() {
//		try(
//			SqlSession session = sqlSessionFactory.openSession();
//		){
			List<BuyerVO> buyerList = session.selectList("kr.or.ddit.jdbc.dao.BuyerDAO.selectBuyerList");
			log.info("buyer list : {}",buyerList);
//		}
	}
	
	@Test
	void test3() {
		log.info("buyer dao : {}", dao);
		List<BuyerVO> buyerList = dao.selectBuyerList();
		log.info("buyer list : {}",buyerList);
	}
	
	@Test
	void test4() {
		List<BuyerVO> buyerList = service.retrieveBuyerList();
		log.info("buyer list : {}",buyerList);
	}
}
