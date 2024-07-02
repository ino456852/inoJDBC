package com.kh.jdbc.day04.psmt.common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USERNAME = "INOJDBC";
//	private static final String PASSWORD = "INOJDBC";
	
	private static final String FILE_NAME = "resources/dev.properties";	// 파일 값을 넣어둔 static 상수 선언
	private static Properties prop;
	private static Connection conn;
	

	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {	// 사용할 곳에 던지기
		prop = new Properties();																		// 그곳에 트라이 캐치가 있어야함
		Reader reader = new FileReader(FILE_NAME);
		prop.load(reader);	// 파일 불러오기
		String driverName 	= prop.getProperty("driverName");
		String url 			= prop.getProperty("url");
		String user 		= prop.getProperty("user");
		String password 	= prop.getProperty("password");
		if(conn == null || conn.isClosed()){	// Dao에서 conn이 닫혀있을때 conn.isclosed()
			Class.forName(driverName);											
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn; // 사용할 곳에 리턴
	}
}
