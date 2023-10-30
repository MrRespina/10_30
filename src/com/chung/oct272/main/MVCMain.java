package com.chung.oct272.main;

import java.util.Scanner;

// 프로그램 개발
//				협업
//				유지보수

// 프로젝트
//			고객
//			PM(Project Manager)
//			PL(Project Leader)
//			개발자 A : Back-end
//			개발자 B : Front-end
//			디자이너 A
//			디자이너 B
//			...

// MVC Pattern
//			파일을 나눠서 작업, 파일 하나 당 하나의 역할만
//			M - Model : 실제 비즈니스 로직(계산, DB, ...)
//			V - View  : 실제 사용자가 눈으로 보는 파일(입력/출력)
//			C - Controller : 상황 판단해서 M을 소환 or V를 소환

// 인터넷 구글창이 보이게 하는 				  : V
// 네이버를 클릭해서 실제로 페이지가 이동하게 하는 : M
// 이 V와 M이 맡은 역할이 실행되게 하는 부분이   : C

public class MVCMain {
	public static void main(String[] args) {
		// x, y값 입력받아서
		// 변수 하나 만들어서 x + y값 넣고
		// syso로 그 값이 뭔지 콘솔에 출력
		Scanner k = new Scanner(System.in);
		System.out.print("x : ");
		int x = k.nextInt();
		System.out.print("y : ");
		int y = k.nextInt();
		int z = x + y;
		System.out.println(z);
	}
}











