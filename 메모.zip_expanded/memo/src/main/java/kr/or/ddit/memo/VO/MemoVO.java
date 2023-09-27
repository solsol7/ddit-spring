package kr.or.ddit.memo.VO;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="code")
public class MemoVO implements Serializable{
	private Integer code;
	private String writer;
	private String email;
	private String wrdate;
	private String content;		
}
