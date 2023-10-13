package kr.or.ddit.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링에서 파일(Resource) 자원 검색
 *  : 모든 자원은 Resource 의 구현체로 캡슐화됨. ex) ClassPathResource, FileSystemResource, UrlResource
 *  
 *  : 자원의 검색과 로딩은 ResourceLoader의 영역.
 *   - 자원 검색에 prefix(classpath:, file:) 활용되고, prefix 에 따라 검색 시작 위치가 런타임에 결정됨.
 *   - 모든 컨테이너는 ResourceLoader의 구현체임.
 *
 */
@Slf4j
public class ResourceLoaderDesc {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext resourceLoader =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/resource/conf/resource-context.xml");
		
		ResourceVO vo = resourceLoader.getBean(ResourceVO.class);
		
//		Resource res1 = resourceLoader.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
//		Resource res2 = resourceLoader.getResource("classpath:kr/or/ddit/db/dbInfo.properties");
//		Resource res3 = resourceLoader.getResource("file:D:\\01.medias\\images\\cute7.JPG");
		
		Resource res1 = vo.getRes1();
		Resource res2 = vo.getRes2();
		Resource res3 = vo.getRes3();
		
		log.info("res1 : {}", res1.contentLength());
		log.info("res2 : {}", res2.contentLength());
		log.info("res3 : {}", res3.contentLength());
		
		DBInfo info = resourceLoader.getBean(DBInfo.class);
		log.info("db info : {}", info);
	}
}



