package com.respace.domain;

//CREATE TABLE IF NOT EXISTS `respace_v1`.`rs_asset` (
//		  `id` INT NOT NULL AUTO_INCREMENT,
//		  `name` VARCHAR(45) NULL,
//		  `url` VARCHAR(255) NOT NULL,
//		  `type` VARCHAR(45) NOT NULL,
//		  `reference_id` INT NULL,
//		  `etc` VARCHAR(300) NULL,
//		  PRIMARY KEY (`id`))
//		ENGINE = InnoDB;

public class RS_Asset {
	int id;					//아이디
	String name;			//어셋 이름
	String url;				//어셋 URL
	String type;			//어셋 타입 (작가, 공간, 상품)
	int reference_id;				//참조 아이디 (작가, 공간, 상품) 다 가능..
	String reference_category = "";	//참조 카테고리 (어디의 아이디인가..)
	int seq_no;				//순번
	String registered_date;	//등록 날짜
	String filename;
	String filetype;
	String filesize;
	String modified_date;
	String filepath;
	String code_status;		//상태
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getReference_id() {
		return reference_id;
	}
	public void setReference_id(int reference_id) {
		this.reference_id = reference_id;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	public String getRegistered_date() {
		return registered_date;
	}
	public void setRegistered_date(String registered_date) {
		this.registered_date = registered_date;
	}
	public String getCode_status() {
		return code_status;
	}
	public void setCode_status(String code_status) {
		this.code_status = code_status;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getReference_category() {
		return reference_category;
	}
	public void setReference_category(String reference_category) {
		this.reference_category = reference_category;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"url\":\""
				+ url + "\", \"type\":\"" + type + "\", \"reference_id\":\""
				+ reference_id + "\", \"reference_category\":\""
				+ reference_category + "\", \"seq_no\":\"" + seq_no
				+ "\", \"registered_date\":\"" + registered_date
				+ "\", \"filename\":\"" + filename + "\", \"filetype\":\""
				+ filetype + "\", \"filesize\":\"" + filesize
				+ "\", \"modified_date\":\"" + modified_date
				+ "\", \"filepath\":\"" + filepath + "\", \"code_status\":\""
				+ code_status + "\", \"etc\":\"" + etc
				+ "\", \"query_start\":\"" + query_start
				+ "\", \"query_number\":\"" + query_number + "\"}";
	}

	
}
