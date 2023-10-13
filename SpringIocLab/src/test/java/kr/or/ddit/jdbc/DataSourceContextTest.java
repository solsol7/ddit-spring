package kr.or.ddit.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.jdbc.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/jdbc/conf/datasource-context.xml")
class DataSourceContextTest {

	@Inject
	@Named("dbInfo")
	private Properties dbInfo;
	
	@Inject
	@Named("dataSource")
	private DataSource dataSource;
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void test() {
		log.info("주입된 properties : {}", dbInfo);
	}

	@Test
	void test2() throws SQLException {
		Connection conn = dataSource.getConnection();
		log.info("connection : {}", conn);
	}
	
	@Test
	void test3() {
		String sql = " SELECT BUYER_ID FROM BUYER ";
		List<String> buyerList = jdbcTemplate.queryForList(sql, String.class);
		log.info("buyerList : {}", buyerList);
	}

	@Test
	void test4() {
		String sql = " SELECT BUYER_ID, BUYER_NAME FROM BUYER ";
		List<BuyerVO> buyerList = jdbcTemplate.query(sql, new RowMapper<BuyerVO>() {

			@Override
			public BuyerVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BuyerVO vo = new BuyerVO();
				vo.setBuyerId(rs.getString("BUYER_ID"));
				vo.setBuyerId(rs.getString("BUYER_NAME"));
				return vo;
			}
			
		});
		log.info("buyerList : {}", buyerList);
	}
}
