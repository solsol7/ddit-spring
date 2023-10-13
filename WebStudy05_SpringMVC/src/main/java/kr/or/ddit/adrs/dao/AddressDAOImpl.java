package kr.or.ddit.adrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertAddress(AddressVO adrsVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			return mapperProxy.insertAddress(adrsVO);
			
//			int adrsNO = sqlSession.selectOne("kr.or.ddit.adrs.dao.AddressDAO.generateAdrsNo");
//			adrsVO.setAdrsNo(adrsNO);
//			int cnt = sqlSession.insert("kr.or.ddit.adrs.dao.AddressDAO.insertAddress",adrsVO);
//			sqlSession.commit();
//			return cnt;
		}
	}

	@Override
	public List<AddressVO> selectAddressList(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			return sqlSession.selectList("kr.or.ddit.adrs.dao.AddressDAO.selectAddressList",memId);
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			//xml과 인터페이스가 1대1 관계여야함
			return mapperProxy.selectAddressList(memId);
		}
	}

	@Override
	public int updateAddress(AddressVO adrsVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			return mapperProxy.updateAddress(adrsVO);
			
		}
	}

	@Override
	public int deleteAddress(int adrsNo) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
				int rowcnt = mapperProxy.deleteAddress(adrsNo);
				sqlSession.commit();
				return rowcnt;
				
			}
	}

}
