package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lotto {
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 배열 생성
		int lotto[] = new int[6];

		// 랜덤값 반환
		System.out.println("Lotto 선택 숫자는 : ");

		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = (int) (Math.random() * 45) + 1;

			for (int j = 0; j < i; j++) {
				if (lotto[i] == lotto[j]) {
					i--;
					break;
				}
			}

		}
			for(int i = 0; i < lotto.length; i++) {
				System.out.println(lotto[i] + " ");
		} 
	
	}
}
