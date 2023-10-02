package kr.or.ddit.vo;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="cartNo")
public class CartVO {
	private ProdVO prod;	// 구매기록 한 건이 상품 하나와 관련이 있음
							// has a 관계
	
	private String cartMember;
	private String cartNo;
	private String cartProd;
	private Integer cartQty;
	private LocalDate cartDate;
}
