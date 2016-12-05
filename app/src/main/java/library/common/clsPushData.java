package library.common;

import java.util.HashMap;

public class clsPushData {
	private dataJson dtdataJson;
	private HashMap<String, String> FileUpload;
	public dataJson getDtdataJson() {
		return dtdataJson;
	}
	public void setDtdataJson(dataJson dtdataJson) {
		this.dtdataJson = dtdataJson;
	}
	public HashMap<String, String> getFileUpload() {
		return FileUpload;
	}
	public void setFileUpload(HashMap<String, String> fileUpload) {
		FileUpload = fileUpload;
	}
}
