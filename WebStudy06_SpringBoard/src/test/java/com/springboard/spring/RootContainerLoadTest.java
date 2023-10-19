package com.springboard.spring;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/com/springboard/spring/conf/*-context.xml")
class RootContainerLoadTest {
	@Resource(name="dataSource")
	private DataSource dataSource;
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Inject
	private MessageSourceAccessor accessor;
	
	@Test
	void test() {
		log.info("datasource : {}",dataSource);
		log.info("sqlSession : {}",sqlSession);
		log.info("accessor : {}",accessor);
		log.info("hi message : {}",accessor.getMessage("hi"));
	}

}
