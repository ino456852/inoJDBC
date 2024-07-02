package com.kh.jdbc.day04.psmt.common;

public class Singleton {
//	static 이면서 Singleton타입의 멤버변수 필요
	private static Singleton instance;
	
	// static이면서 public 이고 리턴타입이 Singleton인 메소드 필요
	// 메소드 안에서는 if문으로 널체크 후 널이면 객체 생성
	// 널이 아니면 그대로 리턴
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
