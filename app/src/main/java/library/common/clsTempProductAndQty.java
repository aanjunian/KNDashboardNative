package library.common;

import java.io.Serializable;

public class clsTempProductAndQty implements Serializable{
	private String intProductCode;
	private int getIdComponentQty;
	public synchronized int getGetIdComponentQty() {
		return getIdComponentQty;
	}
	public synchronized void setGetIdComponentQty(int getIdComponentQty) {
		this.getIdComponentQty = getIdComponentQty;
	}
	public synchronized String getIntProductCode() {
		return intProductCode;
	}
	public synchronized void setIntProductCode(String intProductCode) {
		this.intProductCode = intProductCode;
	}
	private String txtProductCode;
	private String txtProductName;
	private String txtQty;
	private String txtNoMo;
	private String dtDate;
	public synchronized String getTxtNoMo() {
		return txtNoMo;
	}
	public synchronized void setTxtNoMo(String txtNoMo) {
		this.txtNoMo = txtNoMo;
	}
	public synchronized String getDtDate() {
		return dtDate;
	}
	public synchronized void setDtDate(String dtDate) {
		this.dtDate = dtDate;
	}
	public synchronized String getTxtProductCode() {
		return txtProductCode;
	}
	public synchronized void setTxtProductCode(String txtProductCode) {
		this.txtProductCode = txtProductCode;
	}
	public synchronized String getTxtProductName() {
		return txtProductName;
	}
	public synchronized void setTxtProductName(String txtProductName) {
		this.txtProductName = txtProductName;
	}
	public synchronized String getTxtQty() {
		return txtQty;
	}
	public synchronized void setTxtQty(String txtQty) {
		this.txtQty = txtQty;
	}
}
