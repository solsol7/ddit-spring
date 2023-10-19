package kr.or.ddit.buyer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService{
	
	private final BuyerDAO dao;
	
	@Value("#{appInfo.buyerImages}")
	private Resource buyerImages;
	
//	@Value("${appinfo.buyerImages}")
//	private String fileName;
	
	private File saveFolder;
	
	@PostConstruct
	private void init() throws IOException {
		saveFolder=buyerImages.getFile();
//		saveFolder = new File(fileName);
	}
	
	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		return dao.selectBuyer(buyerId);
	}

	public void processBuyerImage(BuyerVO buyer) {
		try {
			buyer.saveTo(saveFolder);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		int cnt = dao.insertBuyer(buyer);
		ServiceResult result = null;
		if(cnt>0) {
			processBuyerImage(buyer);
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		int cnt = dao.updateBuyer(buyer);
		ServiceResult result = null;
		if(cnt>0) {
			processBuyerImage(buyer);
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public void retrieveBuyerList(PaginationInfo<BuyerVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		paging.setDataList(dao.selectBuyerList(paging));
	}

}
