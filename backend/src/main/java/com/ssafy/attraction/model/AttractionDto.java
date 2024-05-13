package com.ssafy.attraction.model;

public class AttractionDto {
	int attractionId;
	int attractionType;
	String title;
	String addr;
	String zipcode;
	String tel;
	String imgSmall;
	String imgBig;
	double lat;
	double lng;

	public int getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}

	public int getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(int attractionType) {
		this.attractionType = attractionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImgSmall() {
		return imgSmall;
	}

	public void setImgSmall(String imgSmall) {
		this.imgSmall = imgSmall;
	}

	public String getImgBig() {
		return imgBig;
	}

	public void setImgBig(String imgBig) {
		this.imgBig = imgBig;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "AttractionDto [attractionId=" + attractionId + ", attractionType=" + attractionType + ", title=" + title
				+ ", addr=" + addr + ", zipcode=" + zipcode + ", tel=" + tel + ", imgSmall=" + imgSmall + ", imgBig="
				+ imgBig + ", lat=" + lat + ", lng=" + lng + "]";
	}

}
