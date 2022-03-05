package br.com.renanrramossi.shop.domain.entities;

public enum StatusEnum {

	SUCCESS("SUCCESS"),
	ERROR("ERROR"),
	PENDING("PENDING");

	private String status;

	StatusEnum(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "StatusEnum{" +
						"value='" + status + '\'' +
						'}';
	}
}