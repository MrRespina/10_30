package com.chung.oct272.main;

import java.util.Scanner;

// View : 디자이너(front-end)
//		직접적으로 사용자 눈에 보임 >> 입/출력

public class V { // [V]에서 리턴받은 값은 [C]를 거쳐서 [M]으로
	// 콘솔창을 인터넷창이다 라고 가정!!
	
	// 여기서 만든 메소드가 실제로 실행이 되기 위해서는 Controller로 보내져야만!!
	
	// x값 입력받는 메소드
	public static int getX() {
		Scanner k = new Scanner(System.in);
		System.out.print("x > ");
		int x = k.nextInt();
		return x; // C로 보내면 C가 M쪽으로 X값 보내서 처리할 수 있도록
	}
	
	// y값 입력받는 메소드
	public static int getY() {
		System.out.print("y > ");
		return new Scanner(System.in).nextInt();
	}
	
	// z값 출력하는 메소드
	//		M에서 계산한 값 C를 통해 받아와서 파라미터로 쓸 것
	public static void printResult(int z) {
		System.out.println(z); // 실제로 실행되기 위해서는 C로 보내야
	}
}









