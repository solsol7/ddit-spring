package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;

class ProdDAOTest extends AbstractRootContextTest{
	
	@Inject
	private ProdDAO prodDAO;

	@Test
	void testSelectProd() {
		prodDAO.selectProd("P101000001");
	}

	@Test
	void testSelectTotalRecord() {
		
	}

	@Test
	void testSelectProdList() {
		
	}

	@Test
	void testInsertProd() {
		
	}

	@Test
	void testUpdateProd() {
		
	}

}
