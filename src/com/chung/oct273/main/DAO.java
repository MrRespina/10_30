package com.chung.oct273.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.chung.db.managaer.ChungDBManager;
import com.chung.db.managaer.JiDBManager;
import com.chung.oct273.reservation.Reservation;
import com.chung.oct273.restaurant.Restaurant;

// Model의 다른 말 : DAO(DAO라는 말을 더 많이 사용)
// DAO / DTO
//		MVC 패턴
//		DAO (Data Access Object)
//			: Model 중에서도 DB관련한 작업을 전담하는 클래스
//		DTO (Data Transfer/Temporary Object)
//			: DAO의 작업결과를 표현해주는 클래스

// Oracle DB + DB연결 : ojdbc8.jar, DBManager.jar
//	프로젝트 우클릭 -> build path -> configure build paht
//	-> 상단 libraries 탭 -> 우측 add external JARs
//  -> 필요한 jar파일 찾아 넣고 -> apply -> apply and close
///////////////////////////////////////////
// 날짜 문제
// java.util.Date - 주력(Spring에서 이걸 원함)
// java.sql.Date  - JDBC에서는 이걸 원함

// java.util.Date로 받아온 날짜를 -> java.sql.Date로 바꿔야
//		: new Date(클래스명.get날짜관련멤버변수명().getTime());

// java.sql.Date -> java.util.Date : 알아서 바꿔줌(신경안써도 됨)
public class DAO {

	// 1. 예약하기 - C(insert)
	public static String book(Reservation rsv) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JiDBManager.connect();

			String sql = "insert into oct27_reservation values(" + "oct27_reservation_seq.nextval, " + "?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			// parameter로 가져온 하리보 껍질포장 뜯어서 r_name부분 가져오겠다
			pstmt.setString(1, rsv.getR_name());
			// new Date(클래스명.get날짜관련멤버변수명().getTime());
			pstmt.setDate(2, new Date(rsv.getR_time().getTime()));
			pstmt.setString(3, rsv.getR_phone());
			pstmt.setString(4, rsv.getR_location());

			if (pstmt.executeUpdate() == 1) {
				return "예약성공"; // Controller 거쳐서 View로 보내줘야 하기 때문
			}
			return ""; // Java문법상 필요해서 억제로 넣은 것!
		} catch (Exception e) {
			e.printStackTrace();
			return "예약실패";
		} finally {
			JiDBManager.close(con, pstmt, null);
		}
	}

	// 2. 매장등록 - C(insert)
	public static String registerRst(Restaurant rst) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JiDBManager.connect();
			String sql = "insert into oct27_restaurant values(?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rst.getR_location());
			pstmt.setString(2, rst.getR_owner());
			pstmt.setInt(3, rst.getR_seat());

			if (pstmt.executeUpdate() == 1) {
				return "매장 등록 성공";
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "매장 등록 실패";
		} finally {
			JiDBManager.close(conn, pstmt, null);
		}

	}

	// 3. 전체 예약 확인?
	public static String[] showBook() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String[] result = null;

		try {
			con = JiDBManager.connect();
			sql = "SELECT r_no,r_name,TO_CHAR(r_time, 'YYYY-MM-DD AM HH12:MI') as r_time2,r_location FROM oct27_reservation";
			// beforeFist() 문으로 커서를 옮기기위해 처음에 선언해둠.
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last(); // 마지막 row로 간다.
			int total = rs.getRow(); // row count를 추출한다.
			rs.beforeFirst(); // rs 초기화

			int cnt = 0;
			result = new String[total];

			while (rs.next()) {
				result[cnt] = rs.getInt("r_no")+")"+rs.getString("r_name") + "님 예약 " + rs.getString("r_time2") + " "
						+ rs.getString("r_location") + "점" + "\n";
				cnt++;
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약 정보를 받아오지 못했습니다.");
			return null;
		} finally {
			JiDBManager.close(con, pstmt, null);
		}

	}

	// 4. 전체 매장 조회?
	public static String[] showRst() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String[] result = null;

		try {
			con = JiDBManager.connect();
			sql = "SELECT * FROM OCT27_RESTAURANT";
			// beforeFist() 문으로 커서를 옮기기위해 처음에 선언해둠.
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last(); // 마지막 row로 간다.
			int total = rs.getRow(); // row count를 추출한다.
			rs.beforeFirst(); // rs 초기화
			int cnt = 0;
			result = new String[total];

			while (rs.next()) {
				result[cnt] = "[" + rs.getString("r_location") + "(" + rs.getInt("r_seat") + ") - "
						+ rs.getString("r_owner") + "]";
				cnt++;
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("매장 정보를 받아오지 못했습니다.");
			return null;
		} finally {
			JiDBManager.close(con, pstmt, null);
		}
	}

	// 5. 받은 좌석수보다 많은 좌석을 가진 매장의 정보를 출력.
	public static Restaurant[] showRst(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Restaurant[] res = null;

		try {
			con = JiDBManager.connect();
			sql = "SELECT * FROM OCT27_RESTAURANT WHERE r_seat >= ?";
			// beforeFist() 문으로 커서를 옮기기위해 처음에 선언해둠.
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.last(); // 마지막 row로 간다.
			int total = rs.getRow(); // row count를 추출한다.
			rs.beforeFirst(); // rs 초기화

			res = new Restaurant[total];

			int cnt = 0;
			while (rs.next()) {
				String loc = rs.getString("r_location");
				String own = rs.getString("r_owner");
				int st = rs.getInt("r_seat");
				res[cnt] = new Restaurant(loc, own, st);
				cnt++;
			}

			return res;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("매장 정보를 받아오지 못했습니다.");
			return null;
		} finally {
			JiDBManager.close(con, pstmt, null);
		}
	}

	// 6. 받은 손님의 이름과 일치하는 예약정보를 출력
	public static ArrayList<Reservation> showRsv(String name) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Reservation res = null;
		ArrayList<Reservation> alrs = new ArrayList<Reservation>();

		try {
			con = JiDBManager.connect();
			sql = "SELECT r_no,r_name,r_phone,r_location,to_char(r_time, 'YYYY-MM-DD AM HH12:MI') "
					+ "as r_time2 FROM oct27_reservation WHERE r_name = ?";
			// beforeFist() 문으로 커서를 옮기기위해 처음에 선언해둠.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				res = new Reservation(rs.getInt("r_no"), rs.getString("r_name"), rs.getString("r_time2"),
						rs.getString("r_phone"), rs.getString("r_location"));
				alrs.add(res);
			}
			return alrs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("매장 정보를 받아오지 못했습니다.");
			return null;
		} finally {
			JiDBManager.close(con, pstmt, null);
		}
	}

	// 7. 번호를 입력받아 예약 일자를 수정
	public static String changeRsv(int num, java.util.Date value) throws ParseException {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = JiDBManager.connect();
			sql = "UPDATE oct27_reservation SET r_time = ? WHERE r_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new Date(value.getTime()));
			pstmt.setInt(2, num);
			pstmt.execute();

			return num + "번쨰의 예약의 예약시간을 " + value + "로 변경하였습니다.";

		} catch (Exception e) {
			e.printStackTrace();
			return "예약 변경 실패 !";
		} finally {
			JiDBManager.close(con, pstmt, null);
		}

	}

	// 8. 번호를 입력받아 해당 예약 번호에 해당하는것 삭제
	public static String removeRsv(int num){

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = JiDBManager.connect();
			sql = "DELETE OCT27_RESERVATION WHERE r_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.execute();

			return num+"번 예약 삭제 완료";

		} catch (Exception e) {
			e.printStackTrace();
			return "예약 삭제 실패 !";
		} finally {
			JiDBManager.close(con, pstmt, null);
		}

	}

}
