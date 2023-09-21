package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * A a1 = new A();
 * A a2 = new A();
 * 1. A 클래스가 로딩(한번만 이루어짐) - garbage collection 대상이 되지 않음
 * 2. instance 생성(heap memory - GC 대상)
 * 3. a1 에 생성된 인스턴스의 참조 주소 할당
 * 
 * factory Object[Method] pattern
 * 	: 인스턴스 생성에 대한 책임을 지는 factory 객체의 운영
 *
 */
public class ConnectionFactory {
	
	private static BasicDataSource dataSource;
	
	static {
		Properties dbInfo = new Properties();
//		classpath resource 형태 자원 읽기
//		kr/or/ddit/db/dbInfo.properties
		try(
				InputStream is = ConnectionFactory.class.getResourceAsStream("dbInfo.properties");				
		) {
			dbInfo.load(is);
			String driverClassName = dbInfo.getProperty("driverClassName");
//			Class.forName(driverClassName);
			String url = dbInfo.getProperty("url");
			String user = dbInfo.getProperty("user");
			String password = dbInfo.getProperty("password");
			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			
			int initialSize = Integer.parseInt(dbInfo.getProperty("initialSize"));
			int maxIdle = Integer.parseInt(dbInfo.getProperty("maxIdle"));
			int maxTotal = Integer.parseInt(dbInfo.getProperty("maxTotal"));
			long maxWait = Long.parseLong(dbInfo.getProperty("maxWait"));
			
			dataSource.setInitialSize(initialSize);
			dataSource.setMaxTotal(maxTotal);
			dataSource.setMaxWaitMillis(maxWait);
			dataSource.setMaxIdle(maxIdle);
			
		} catch (IOException e) {
			// unchecked 처리하지않아도 컴파일에러가 나지 않음
			// checked 처리하지 않으면 컴파일에러남
			// checked를 unchecked로 바꿔서 던져줘야함
			throw new RuntimeException(e);	// 매개변수로 원본 예외 유지시켜줘야함
		}
	}	// 일반 코드블럭 - 생성자 이후 실행(인스턴스 생성될 때 마다 반복 실행(2번))
			//<-> static 코드블럭 - 인스턴스 생성 전 실행(1번)
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
