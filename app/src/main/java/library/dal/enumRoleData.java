package library.dal;

public enum enumRoleData {
	 BR  (116),  
	 SPGMOBILE	(114)
	 /*
	 BR  (114),  
	 SPGMOBILE	(113)
	 */
	 ; 
	enumRoleData(int idConfigData) {
		this.idConfigData = idConfigData;
	}
	public int getidConfigData() {
        return this.idConfigData;
    }
	private  final int idConfigData;
	
}
