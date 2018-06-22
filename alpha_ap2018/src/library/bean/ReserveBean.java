package library.bean;

import java.io.Serializable;

public class ReserveBean implements Serializable {

	private int reserveId; 		  	 // 予約ID
	private String userId;		         // ユーザーID
	private String reserveDate;	  	 // 予約日
	private String label;	             // 識別ラベル
	private boolean reserveCheckFlag;   // 予約非表示フラグ

	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isReserveCheckFlag() {
		return reserveCheckFlag;
	}
	public void setReserveCheckFlag(boolean reserveCheckFlag) {
		this.reserveCheckFlag = reserveCheckFlag;
	}



}
