package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerImageDownloadController {

	@Value("#{appInfo.buyerImages}")
	private Resource buyerImages;
	
	@Inject
	private BuyerService service;
	
//	컨트롤러에서 바로 응답이 나갈 수 있도록 ResponseEntity 사용
//	ResponseEntity -> 응답데이터가 포장되는 규칙이 그대로 적용
//	line/header/body -> line - statusCode /header /body - contents 셋팅
	@GetMapping("/buyer/{buyerId}/buyerImage")
	public ResponseEntity<Resource> buyerImageDownload(@PathVariable String buyerId) throws IOException {
//		제네릭 -> 바디 영역 안에 들어갈 컨텐츠 타입
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		if(buyer==null) {
			// 해당 제조사 없음.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							String.format("%s 제조사 없음.", buyerId));
		}
		
		String filename = buyer.getBuyerImg();
		
		if(StringUtils.isBlank(filename)) {
			// 사업자 등록증 사본 없음
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							String.format("%s 사업자 등록증 사본 없음.", buyer.getBuyerName()));
		}
		
		Resource imageFile = buyerImages.createRelative(filename);
		
//		Content-Disposition: attachment; filename="filename.jpg"
		ContentDisposition disposition =  ContentDisposition.attachment()
										.filename(buyer.getBuyerName(), Charset.defaultCharset())
										.build();
//				그릴게(inline) 아니라 다운로드해야함 -> attachment
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(disposition);
		headers.setContentLength(imageFile.contentLength());
		return ResponseEntity.ok()	 // response의 line 셋팅 - 상태코드 200
					.headers(headers)	// response의 헤더 셋팅
					.body(imageFile);	// response의 바디 셋팅 -바디에 이미지파일 넣어줌
										// 스프링이 이미지파일을 스트링카피 떠서 응답보내줌
	}
}
