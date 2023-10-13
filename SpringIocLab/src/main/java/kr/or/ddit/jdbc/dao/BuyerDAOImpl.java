package kr.or.ddit.jdbc.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.jdbc.vo.BuyerVO;

//@Repository
public class BuyerDAOImpl implements BuyerDAO {

	@Inject
	private SqlSessionFactory sqlSessionFactory;
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BuyerVO> selectBuyerList() {
		
//		return sqlSession.selectList("kr.or.ddit.jdbc.dao.BuyerDAO.selectBuyerList");
		BuyerDAO proxy = sqlSession.getMapper(BuyerDAO.class);
		return proxy.selectBuyerList();
	}

}
