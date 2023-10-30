package com.chung.oct273.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.chung.oct273.reservation.Reservation;
import com.chung.oct273.restaurant.Restaurant;

public class View {
	public static int showMainMenu() {
		Scanner k = new Scanner(System.in);
		System.out.println("=-=-=-=-=-=-=-=-");
		System.out.println("1. 예약하기");
		System.out.println("2. 매장등록");
		System.out.println("3. 전체 예약 확인");
		System.out.println("4. 전체 매장 조회");
		System.out.println("5. 매장찾기");
		System.out.println("6. 예약찾기");
		System.out.println("7. 예약정보수정");
		System.out.println("8. 예약취소");
		System.out.println("9. 프로그램 종료");
		System.out.println("=-=-=-=-=-=-=-=-");
		System.out.print("번호 입력 > ");
		return k.nextInt();
	}

	public static void printResult(String result) {
		System.out.println(result);
	}

	// 1. 예약하기 (직접 입력, 날짜는 연-월-일/시:분)
	public static Reservation showRsvMenu() throws ParseException {
		Scanner k = new Scanner(System.in);
		System.out.print("예약자 > ");
		String name = k.next();
		System.out.print("예약 날짜(YYYY-MM-DD/HH:mm) > ");
		String when = k.next();
		// String으로 받은 내용을 Date로 : parse
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
		Date when2 = sdf.parse(when);
		System.out.print("연락처 > ");
		String phone = k.next();
		System.out.print("지점명 > ");
		String location = k.next();

		// View에서 만든 내용 return해서 Controller 거쳐서 DAO(Model)로 이동해야만 함
		// return이 필요한데, Java는 리턴값이 하나만 가능
		// 근데 우리는 지금 4개를 return 시켜야 함! => JavaBean(네개를 세트로 하나 포장을 해서)
		// 포장 자체를 return 시키면 된다!
		// JavaBean 객체 하나 꺼냄
		Reservation r = new Reservation(0, name, when2, phone, location);
		return r;
	}

	// 2. 매장 등록
	public static Restaurant showRstMenu() {

		Scanner k = new Scanner(System.in);

		System.out.print("지점명 : ");
		String location = k.nextLine();

		System.out.print("지점장 : ");
		String owner = k.nextLine();

		System.out.print("좌석수 : ");
		int seat = k.nextInt();

		return new Restaurant(location, owner, seat);

	}

	// 3. 전체 예약 확인 (날짜 연-월-일 - 요일 오전/오후 시:분), String[] 배열에 완성된채로 넣어놓은것 View에 출력만.
	public static void showAllRsv(String[] rs) throws SQLException {

		for (int i = 0; i < rs.length; i++) {

			System.out.println(rs[i]);

		}

	}

	// 4. 전체 매장 조회 [위치(좌석수) - 지점장 이름] ex) [서초(115) - 박진호]
	public static void showAllRst(String[] rs) throws SQLException {

		for (int i = 0; i < rs.length; i++) {

			System.out.println(rs[i]);

		}

	}
	
	// 5. 매장 찾기 (최소 좌석 수를 입력 > 좌석수 이상의 매장 정보 출력)
	public static int inputSeat() {
		
		Scanner k = new Scanner(System.in);
		System.out.println("* 입력한 좌석보다 많은 매장의 정보를 출력합니다.*");
		System.out.print("좌석 수를 입력해주세요 :");
		int num = k.nextInt();
		return num;
		
	}
	
	// 5-2 출력
	public static void showConditionalRst(int num,Restaurant[] rs) {
		
		int cnt = rs.length;
		System.out.println(cnt);
		System.out.println(num+"석보다 많은 좌석수를 가진 지점들 : ");
		
		for(int i=0; i < cnt ; i++) {
			
			System.out.println("=====================");
			System.out.println("지점 명 : "+rs[i].getR_location());
			System.out.println("소유주 : "+rs[i].getR_owner());
			System.out.println("좌석 수 : "+rs[i].getR_seat());
			
		}
		
	}

	// 6. 예약 찾기 (예약자 이름으로 예약 찾기.)
	public static String inputName() {
		
		Scanner k = new Scanner(System.in);
		System.out.print("예약된 손님의 이름을 입력해주세요 :");
		String name = k.nextLine();
		return name;
		
	}
	
	// 6-2 출력
	public static void showConditionalRsv(ArrayList<Reservation> ar) {
		
		for(int i=0;i<ar.size();i++) {
			System.out.println("====================");
			System.out.println("성명 : "+ar.get(i).getR_name());
			System.out.println("장소 : "+ar.get(i).getR_location());
			System.out.println("연락처 : "+ar.get(i).getR_phone());
			System.out.println("예약 시간 : "+ar.get(i).getR_time2());
		}
		System.out.println("===================");
	}

	// 7. 예약 정보 수정 (예약 번호를 입력하면, 연락처를 변경할 수 있게.)
	public static int inputRvNumber() {
		
		Scanner k = new Scanner(System.in);
		System.out.print("수정할 예약의 예약번호를 입력해주세요 :");
		int num = k.nextInt();
		return num;
		
	}
	
	public static Date updateRvDate() throws ParseException {
		
		Scanner k = new Scanner(System.in);
		System.out.print("수정할 예약의 시간을 입력해주세요(YYYY-MM-DD/HH:mm) :");
		String date = k.nextLine();	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
		Date out = sdf.parse(date);
		
		return out;

	}
	

	// 8. 예약 취소 (예약 번호를 입력하면, 취소할 수 있게.)
	public static int deleteRsv() {
		
		Scanner k = new Scanner(System.in);
		System.out.print("삭제할 예약의 번호를 입력해주세요 :");
		int num = k.nextInt();	
		
		return num;
		
	}
	
}
