package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 하나의 상품에 대한 정보를 캡슐화하기 위한 도메인 레이어
 *
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO implements Serializable{
	
	private int rnum;
	
	@NotBlank(groups = UpdateGroup.class)
	private String prodId;
	@NotBlank(groups = InsertGroup.class)
	private String prodName;
	@NotBlank(groups = InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups = InsertGroup.class)
	private String prodBuyer;
	@NotNull
	private Integer prodCost;
	@NotNull
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	
	@NotBlank(groups = InsertGroup.class)
	private String prodImg;	// db와 연결되는 필드
	
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate prodInsdate;
	
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private LprodVO lprod;	// has a (1:1 관계)
							//	상품 하나가 분류 하나에 소속되어있기 때문에 1:1 관계
	private BuyerVO buyer;
	private List<CartVO> cartList;	// has many
	
	private Integer memCount;
}
