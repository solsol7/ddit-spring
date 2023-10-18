package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	
	private LprodVO lprod; // has a (1:1)
	
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private List<ProdVO> prodList; // has many(1:N)
	
	private String buyerImg;
	
	private MultipartFile buyerImage;
	
	public void setBuyerImage(MultipartFile buyerImage) {
		if(buyerImage!=null && !buyerImage.isEmpty()) {
			this.buyerImage = buyerImage;
			buyerImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(buyerImage!=null) {
			buyerImage.transferTo(new File(saveFolder,buyerImg));
		}
	}
}
