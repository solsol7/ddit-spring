package kr.or.ddit.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResourceVO {
//	문자열이 알아서 해당타입으로 바뀐 다음 들어와야함 => @Value
	@Value("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
	private Resource res1;
	@Value("classpath:kr/or/ddit/db/dbInfo.properties")
	private Resource res2;
	@Value("file:D:/01.medias/images/cute7.JPG")
	private Resource res3;
}
