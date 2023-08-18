package com.ict.ajax.exam;

public class JSON_VO {
	private String city;
	private long totalcount, firstcount, sendcount ;
	private double firstpersent, sendpersent;
	public JSON_VO(String city, long totalcount, long firstcount, long sendcount, double firstpersent,
			double sendpersent) {
		super();
		this.city = city;
		this.totalcount = totalcount;
		this.firstcount = firstcount;
		this.sendcount = sendcount;
		this.firstpersent = firstpersent;
		this.sendpersent = sendpersent;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
	}
	public long getFirstcount() {
		return firstcount;
	}
	public void setFirstcount(long firstcount) {
		this.firstcount = firstcount;
	}
	public long getSendcount() {
		return sendcount;
	}
	public void setSendcount(long sendcount) {
		this.sendcount = sendcount;
	}
	public double getFirstpersent() {
		return firstpersent;
	}
	public void setFirstpersent(double firstpersent) {
		this.firstpersent = firstpersent;
	}
	public double getSendpersent() {
		return sendpersent;
	}
	public void setSendpersent(double sendpersent) {
		this.sendpersent = sendpersent;
	}
	
}
