package com.chung.oct273.reservation;

import java.util.Date;

public class Reservation {
	private int r_no;
	private String r_name;
	private Date r_time;
	private String r_time2;
	private String r_phone;
	private String r_location;
	// 생성자 : ctrl + space + enter
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	// 생성자 오버로딩 : ctrl + shift + a
	public Reservation(int r_no, String r_name, Date r_time, String r_phone, String r_location) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_time = r_time;
		this.r_phone = r_phone;
		this.r_location = r_location;
	}
	public Reservation(int r_no, String r_name, String r_time2, String r_phone, String r_location) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_time2 = r_time2;
		this.r_phone = r_phone;
		this.r_location = r_location;
	}
	// getters and setters : ctrl + shift + z
	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public Date getR_time() {
		return r_time;
	}

	public void setR_time(Date r_time) {
		this.r_time = r_time;
	}

	public String getR_phone() {
		return r_phone;
	}

	public void setR_phone(String r_phone) {
		this.r_phone = r_phone;
	}

	public String getR_location() {
		return r_location;
	}

	public void setR_location(String r_location) {
		this.r_location = r_location;
	}
	
	public String getR_time2() {
		return r_time2;
	}
	
	public void setR_time2(String r_time2) {
		this.r_time2 = r_time2;
	}
}
