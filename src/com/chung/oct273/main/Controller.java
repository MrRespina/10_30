package com.chung.oct273.main;

import java.sql.Date;
import java.util.ArrayList;

import com.chung.oct273.reservation.Reservation;
import com.chung.oct273.restaurant.Restaurant;

// 식당예약프로그램 - MVC 패턴 + JDBC(DB활용)

public class Controller {
	public static void main(String[] args) {
		int menu = 0;
		String result = null; // return 이 String 일 때.
		String res[] = null;
		Reservation rsv = null; // Reservation으로 return 받은 경우 사용
		Restaurant rst = null; // Restaurant로 return 받은 경우 사용
		Restaurant rst2[] = null; // Restaurant가 여러개 필요할떄 사용(배열)
		ArrayList<Reservation> rsv2 = null; // Restaurant가 여러개 필요할떄 사용(ArrayList)

		System.out.println("<<예약 프로그램>>");
		while (true) {
			try {
				menu = View.showMainMenu();
				if (menu == 9) {
					System.out.println("프로그램을 종료합니다.");
					break;
				} else if (menu == 1) {
					rsv = View.showRsvMenu(); // DAO로 이동
					result = DAO.book(rsv); // View로 이동
					View.printResult(result);
				} else if (menu == 2) {
					rst = View.showRstMenu();
					result = DAO.registerRst(rst);
					View.printResult(result);
				} else if (menu == 3) {
					res = DAO.showBook();
					View.showAllRsv(res);
				} else if (menu == 4) {
					res = DAO.showRst();
					View.showAllRst(res);
				} else if (menu == 5) {
					int num = View.inputSeat();
					rst2 = DAO.showRst(num);
					View.showConditionalRst(num, rst2);
				} else if (menu == 6) {
					String name = View.inputName();
					rsv2 = DAO.showRsv(name);
					View.showConditionalRsv(rsv2);
				} else if (menu == 7) {
					int num = View.inputRvNumber();
					java.util.Date value = View.updateRvDate();
					result = DAO.changeRsv(num, value);
					View.printResult(result);
				} else if (menu == 8) {
					int num = View.deleteRsv();
					result=DAO.removeRsv(num);
					View.printResult(result);
				} else {
					System.out.println("1~9 까지의 숫자를 입력해주세요 !!!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
