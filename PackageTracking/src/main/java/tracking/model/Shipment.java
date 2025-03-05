package tracking.model;

public class Shipment {
	
	private int userID,itemID;
	private String tracking_num,title,courier_name,date,time,status_shipment;
	

	public String getTracking_num() {
		return tracking_num;
	}

	public void setTracking_num(String tracking_num) {
		this.tracking_num = tracking_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourier_name() {
		return courier_name;
	}

	public void setCourier_name(String courier_name) {
		this.courier_name = courier_name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus_shipment() {
		return status_shipment;
	}

	public void setStatus_shipment(String status_shipment) {
		this.status_shipment = status_shipment;
	}

}
