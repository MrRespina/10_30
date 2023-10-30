package com.chung.oct272.main;

// Controller : 높은 직급의 개발자
//		상황을 판단 >> M을 소환하거나 V를 소환하거나

// 교통정리 : M과 V의 작업은 무조건 C를 거친다(V와 M이 다이렉트로 만날일 X)
//		V	<->		C	<->		M
public class C {
	// 프로젝트에는 main메소드가 하나만!
	public static void main(String[] args) {
		// V에서 만든 메소드 실행 할 수 있도록!! >> return 된 메소드는 변수에 담을 수 있어
		// 변수에 값을 담아야 그 변수를 통해서 M으로 값 이동이 가능!
		int x = V.getX();
		int y = V.getY(); // M이동
		
		// M에서 계산한 메소드 적용할 수 있도록 꺼내줌
		int add = M.add(x, y); // V로 보내서 출력할 수 있도록
		
		V.printResult(add);
	}
}
