package com.respace.domain;

//CREATE TABLE IF NOT EXISTS `respace_v1`.`rs_product` (
//		  `id` INT NOT NULL AUTO_INCREMENT,
//		  `id_artist` INT NOT NULL,
//		  `url` VARCHAR(255) NULL,
//		  `produce_number` INT NULL,
//		  `cond_period_start` VARCHAR(45) NULL COMMENT '임차 조건 시작',
//		  `cond_period_end` VARCHAR(45) NULL COMMENT '임차 조건 종료',
//		  `cond_location` VARCHAR(45) NULL,
//		  `price` INT NULL COMMENT '아이템 가격',
//		  `history` VARCHAR(255) NULL COMMENT '상품 이력',
//		  `review` TEXT NULL,
//		  `code_mood` VARCHAR(45) NULL COMMENT '제품/ 작품의 분위기 (code item)',
//		  `code_category` VARCHAR(45) NULL COMMENT '상품 분류 (code item)',
//		  `code_status` VARCHAR(45) NULL COMMENT '상품의 상태 (code item)',
//		  `size` VARCHAR(45) NULL COMMENT '000 x 000 (mm)',
//		  `for_sale_ox` INT NULL,
//		  PRIMARY KEY (`id`, `id_artist`),
//		  INDEX `fk_rs_item_rs_object1_idx` (`id_artist` ASC),
//		  CONSTRAINT `fk_rs_item_rs_object1`
//		    FOREIGN KEY (`id_artist`)
//		    REFERENCES `respace_v1`.`rs_artist` (`id`)
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB
//		COMMENT = '상품 정보';

public class Product {
	int id;							//아이디
	int id_artist;					//작가 아이디
	String url;						//URL
	int produce_number;				//제작 수량
	String cond_period_start;		//임차 조건 시작
	String cond_period_end;			//임차 조건 종료
	String cond_location;			//임차 위치
	int price;						//가격
	String history;					//상품 이력
	String review;					//상품 리뷰
	String code_mood;				//분위기
	String code_category;			//카테고리
	String code_status;				//상태
	String size;					//크기 000 x 000
	int for_sale_ox;				//판매 여부 (0, 1)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_artist() {
		return id_artist;
	}
	public void setId_artist(int id_artist) {
		this.id_artist = id_artist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getProduce_number() {
		return produce_number;
	}
	public void setProduce_number(int produce_number) {
		this.produce_number = produce_number;
	}
	public String getCond_period_start() {
		return cond_period_start;
	}
	public void setCond_period_start(String cond_period_start) {
		this.cond_period_start = cond_period_start;
	}
	public String getCond_period_end() {
		return cond_period_end;
	}
	public void setCond_period_end(String cond_period_end) {
		this.cond_period_end = cond_period_end;
	}
	public String getCond_location() {
		return cond_location;
	}
	public void setCond_location(String cond_location) {
		this.cond_location = cond_location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getCode_mood() {
		return code_mood;
	}
	public void setCode_mood(String code_mood) {
		this.code_mood = code_mood;
	}
	public String getCode_category() {
		return code_category;
	}
	public void setCode_category(String code_category) {
		this.code_category = code_category;
	}
	public String getCode_status() {
		return code_status;
	}
	public void setCode_status(String code_status) {
		this.code_status = code_status;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getFor_sale_ox() {
		return for_sale_ox;
	}
	public void setFor_sale_ox(int for_sale_ox) {
		this.for_sale_ox = for_sale_ox;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", id_artist=" + id_artist + ", url="
				+ url + ", produce_number=" + produce_number
				+ ", cond_period_start=" + cond_period_start
				+ ", cond_period_end=" + cond_period_end + ", cond_location="
				+ cond_location + ", price=" + price + ", history=" + history
				+ ", review=" + review + ", code_mood=" + code_mood
				+ ", code_category=" + code_category + ", code_status="
				+ code_status + ", size=" + size + ", for_sale_ox="
				+ for_sale_ox + "]";
	}
	
	
	
	
}
