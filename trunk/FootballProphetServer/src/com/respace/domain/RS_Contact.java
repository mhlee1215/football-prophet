package com.respace.domain;

//`id` INT NOT NULL AUTO_INCREMENT,
//`id_item` INT NOT NULL,
//`id_space` INT NOT NULL,
//`id_artist` INT NOT NULL,
//`status` VARCHAR(45) NULL COMMENT '현재 상태 (진행/완료)',
//`contact_period_start` VARCHAR(45) NULL COMMENT '계약 기간 시작',
//`contact_period_end` VARCHAR(45) NULL COMMENT '계약 종료 시점',
//`contact_status` VARCHAR(45) NULL COMMENT '계약의 상태',
//`etc` TEXT NULL COMMENT '비고',

public class RS_Contact {
	int id;					//아이디
	int id_product;			//상품 아이디
	int id_space;			//공간 아이디
	int id_artist;			//작가 아이디
	String status;			//상태
	String period_start;	//계약기간 (시작)
	String period_end;		//계약기간 (종료)
	String etc;				//비고
	
	private int query_start = 0;
	private int query_number = 0;
	
	
	public int getQuery_start() {
		return query_start;
	}
	public void setQuery_start(int query_start) {
		this.query_start = query_start;
	}
	public int getQuery_number() {
		return query_number;
	}
	public void setQuery_number(int query_number) {
		this.query_number = query_number;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public int getId_space() {
		return id_space;
	}
	public void setId_space(int id_space) {
		this.id_space = id_space;
	}
	public int getId_artist() {
		return id_artist;
	}
	public void setId_artist(int id_artist) {
		this.id_artist = id_artist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPeriod_start() {
		return period_start;
	}
	public void setPeriod_start(String period_start) {
		this.period_start = period_start;
	}
	public String getPeriod_end() {
		return period_end;
	}
	public void setPeriod_end(String period_end) {
		this.period_end = period_end;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	@Override
	public String toString() {
		return "RS_Contact [id=" + id + ", id_product=" + id_product
				+ ", id_space=" + id_space + ", id_artist=" + id_artist
				+ ", status=" + status + ", period_start=" + period_start
				+ ", period_end=" + period_end + ", etc=" + etc
				+ ", query_start=" + query_start + ", query_number="
				+ query_number + "]";
	}
}
