package library.bean;

import java.io.Serializable;

public class RentalBean implements Serializable {

	private int rentalId;			//貸出ID
	private String label;			//識別ラベル
	private String userId;			//ユーザーID
	private String rentalDate;		//貸出日
	private boolean extendFlag;	//延長フラグ
	private boolean returnFlag;	//返却フラグ

	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public boolean isExtendFlag() {
		return extendFlag;
	}
	public void setExtendFlag(boolean extendFlag) {
		this.extendFlag = extendFlag;
	}
	public boolean isReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(boolean returnFlag) {
		this.returnFlag = returnFlag;
	}

}
