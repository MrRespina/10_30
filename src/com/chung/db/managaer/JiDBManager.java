package com.chung.db.managaer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 우리가 앞으로 사용할 jar파일 만드는 중 -> 웬만하면 변수명, 메소드명, ... 똑같이!!

public class JiDBManager {
	
	public static Connection connect() throws SQLException {
		String addr = "jdbc:oracle:thin:@localhost:1521:xe";
		return DriverManager.getConnection(addr, "pin", "sdj7524");
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			// C(insert), U, D 할 때는 필요 없음!! R(select) 때만 필요
			// insert하게 되면 아래 catch부분에서 NullPointerException이 발생하게 됨!
			// 익셉션이 뜨긴 하지만, 코드상 문제는 없기 때문에
			// error 메시지 표시하는 e.printStackTrace();를 꺼두기로!(에러 메시지 안본다)
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		
		try {
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}






