package com.springboard.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * 암호화 구조를 이용한 데이터 보호
 * 단방향 암호화 : 암호화는 가능하나 복호화 불가능, ex) 비밀번호 암호화
 * 양방향 암호화 : 암복호화가 가능한 방식 ex) e-book(DRM), 전자서명
 * 		대칭키 방식(e-book(DRM)) : 비밀키 하나로 암복호화 처리 (암호화할때 키와 복호화할 때 키가 동일함)
 * 								, 비밀키 공유라는 상황 필요
 * 		비대칭키 방식(전자서명) : 한쌍의 키를 생성하고, 암호화와 복호화를 서로 다른 키로 처리함
 * 					public key(공개키) / private key(개인키)
 * 				(공개키, 개인키 한 쌍을 만듦 - 개인키는 나만 가지고있음, 공개키는 상대방한테 줌
 * 				내가 소유하고 있는 개인키로 암호화시킴 , 상대방한테 암호문 보냄, 상대방은 공개키로 복호화함
 * 				상대방이 나한테 보낼때 공개키로 암호화시킴, 나는 개인키로 복호화함)
 *
 */
@Slf4j
class PasswordEncoderTest {
	// 단방향 암호화
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Test
	void test2() {
		String saved = "{bcrypt}$2a$10$y1m4gBIGIVJ4B8qS8BC0meWnb7kYTE40LFn4C73/Ol3Jemr.ZolRe";
		String input = "java";
		
		log.info("인증 성공 여부 : {}", passwordEncoder.matches(input, saved));
//		 matches : 단방향 암호화 -> 저장된 비밀번호 가지고 비밀번호 인증하려면 평문으로 복원해야함
//				..> 단방향암호화이기 때문에 복원 불가능 -> 평문데이터를 matches라는 녀석에 넘겨서 비교
	}
	
	@Test
	void test() {
		String plain = "java";
		
		String encoded = passwordEncoder.encode(plain);	// 암호화
		
		log.info("plain : {}, encoded : {}",plain, encoded);
		
	}

}
