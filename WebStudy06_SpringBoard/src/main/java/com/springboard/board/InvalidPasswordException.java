package com.springboard.board;

public class InvalidPasswordException extends RuntimeException{
	public InvalidPasswordException() {
		super("게시글 비밀번호 오류");	// 상위 클래스의 생성자를 호출하겠다
	}
}
