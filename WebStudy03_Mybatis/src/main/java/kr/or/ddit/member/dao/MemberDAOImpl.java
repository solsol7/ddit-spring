package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();				
		){
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberForAuth", inputData);
		}
		
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			
			return mapperProxy.selectMemberList();
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
