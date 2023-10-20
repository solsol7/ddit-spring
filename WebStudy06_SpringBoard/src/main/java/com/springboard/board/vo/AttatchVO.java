package com.springboard.board.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "attNo")
@NoArgsConstructor
public class AttatchVO implements Serializable{	// MultipartFile의 어뎁터
	private MultipartFile boFile;
	
	public AttatchVO(MultipartFile boFile) {
		super();
		this.boFile = boFile;
		this.attFilename = boFile.getOriginalFilename();
		this.attMime = boFile.getContentType();
		this.attFilesize = boFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
		this.attSavename = UUID.randomUUID().toString();
	}
	
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private long attFilesize;	// 1024000000
	private String attFancysize;	// 1GB
	private Integer attDownload;
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(boFile!=null)
			boFile.transferTo(new File(saveFolder, attSavename));
	}
}
