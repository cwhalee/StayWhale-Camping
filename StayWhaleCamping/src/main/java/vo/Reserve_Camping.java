package vo;

public class Reserve_Camping {
	
	private int reserve_num;
	public int getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(int reserve_num) {
		this.reserve_num = reserve_num;
	}
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFacility_num_c() {
		return facility_num_c;
	}
	public void setFacility_num_c(String facility_num_c) {
		this.facility_num_c = facility_num_c;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getDaysBetween() {
		return daysBetween;
	}
	public void setDaysBetween(String daysBetween) {
		this.daysBetween = daysBetween;
	}
	private String reserve_date;
	private String expire_date;
	private String user_id;
	private String facility_num_c;
	private String payment_method;
	private String daysBetween;
	
	
	
}