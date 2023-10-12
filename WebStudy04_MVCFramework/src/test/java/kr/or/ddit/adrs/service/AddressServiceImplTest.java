package kr.or.ddit.adrs.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.AddressVO;

class AddressServiceImplTest {
	
	AddressService service = new AddressServiceImpl();
	
	@Test
	void testCreateAddress() {
		AddressVO vo = new AddressVO();
		vo.setMemId("a001");
		vo.setAdrsName("테스트");
		vo.setAdrsHp("111-1111-1111");
		vo.setAdrsAdd("서울");
		boolean result = service.createAddress(vo);
		assertEquals(true, result);
	}

	@Test
	void testRetriveAddressList() {
		List<AddressVO> adrsList = assertDoesNotThrow(()->{
			return service.retriveAddressList("a001");
		});
		assertNotNull(adrsList);
	}

	@Test
	void testModifyAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAddress() {
		boolean result = service.removeAddress(1);
		assertNotEquals(true, result);
	}

}
