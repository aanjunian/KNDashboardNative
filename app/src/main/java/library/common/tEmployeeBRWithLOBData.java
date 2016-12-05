package library.common;

public class tEmployeeBRWithLOBData {
	
	public synchronized String get_IntEmployeeDetailId() {
		return _IntEmployeeDetailId;
	}
	public synchronized void set_IntEmployeeDetailId(String _IntEmployeeDetailId) {
		this._IntEmployeeDetailId = _IntEmployeeDetailId;
	}
	public synchronized String get_IntEmployeeId() {
		return _IntEmployeeId;
	}
	public synchronized void set_IntEmployeeId(String _IntEmployeeId) {
		this._IntEmployeeId = _IntEmployeeId;
	}
	public synchronized String get_IntlobId() {
		return _IntlobId;
	}
	public synchronized void set_IntlobId(String _IntlobId) {
		this._IntlobId = _IntlobId;
	}
	public synchronized String get_TxtLobName() {
		return _TxtLobName;
	}
	public synchronized void set_TxtLobName(String _TxtLobName) {
		this._TxtLobName = _TxtLobName;
	}
	private String _IntEmployeeDetailId;
	private String _IntEmployeeId;
	private String _IntlobId;
	private String _TxtLobName;
	
	public String Property_IntEmployeeDetailId="IntEmployeeDetailId";
	public String Property_IntEmployeeId="IntEmployeeId";
	public String Property_IntlobId="IntlobId";
	public String Property_TxtLobName="TxtLobName";
	
	
	public String Property_ListOftEmployeeBRWithLOBData="ListOftEmployeeBRWithLOBData";
	public String Property_All=Property_IntEmployeeDetailId+","+Property_IntEmployeeId+","+Property_IntlobId+","+Property_TxtLobName;
}
