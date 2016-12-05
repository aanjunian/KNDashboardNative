package library.dal;

public enum enumStatus {
	 Add  (1),  
	 EDIT	(2),
	 VIEW	(3),
	 ; 
	enumStatus(int idConfigData) {
		this.idConfigData = idConfigData;
	}
	public int getIdenumStatus() {
        return this.idConfigData;
    }
	private  final int idConfigData;
}
