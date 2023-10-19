package com.springboard.board.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "boNo")
public class FreeBoardVO implements Serializable{
	private Integer boNo;
	private String boTitle;
	private String boWriter;
	private String boIp;
	private String boMail;
	@JsonIgnore		// 마샬링 제외
	private transient String boPass; // transient 직렬화 제외
	@ToString.Exclude
	private String boContent;
	private LocalDateTime boDate;
	private Integer boHit;
}
