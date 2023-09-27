package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.memo.VO.MemoVO;
import kr.or.ddit.memo.db.CustomSqlSessionFactoryBuilder;

public class MemoDAOImpl implements MemoDAO {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMemo(MemoVO memo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true); 	
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
			return mapperProxy.insertMemo(memo);
		}
	}

	@Override
	public List<MemoVO> selectList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(); 	
			){
				MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
				return mapperProxy.selectList();
			}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true); 	
			){
				MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
				return mapperProxy.updateMemo(memo);
			}
	}

	@Override
	public int deleteMemo(int code) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true); 	
			){
				MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
				return mapperProxy.deleteMemo(code);
			}
	}

}
