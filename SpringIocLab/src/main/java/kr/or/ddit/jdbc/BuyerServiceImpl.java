package kr.or.ddit.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.jdbc.dao.BuyerDAO;
import kr.or.ddit.jdbc.vo.BuyerVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyerServiceImpl {
	
	private final BuyerDAO dao;
		// final -> 객체 생성단계에서 꼭 필요함 -> 생성자 하나밖에 없음 -> proxy 알아서 주입해줌
	
	
//	생성자 뒤에 동작하도록(모든 주입이 끝난 후 동작함)
	@PostConstruct
	public void init() {
		log.info("주입된 객체 : {}", dao);
	}
	
	public List<BuyerVO> retrieveBuyerList(){
		return dao.selectBuyerList();
	}
}
