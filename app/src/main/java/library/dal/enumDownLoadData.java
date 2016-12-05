package library.dal;

public enum enumDownLoadData {
	 All			(1),
	 EmployeeArea  (2),  
	 EmployeeBranch	(3),  
	 EmployeeSalesProduct   (4),
	 Notifikasi   (5),
	 Transaction   (6),
	 Activity   (7),
	 Absen   (8),
	 CustomerBase   (9),
	 Brand (10),
	 TypeLeave (11),
	 TransactionLeave (12),
	 BrosurMobile (13),
	 CategoryBrosurMobile (14),
	 LOBBRMobile (15),
	 AllBrosurMobile (16)
	 ; 
	 enumDownLoadData(int idConfigData) {
		this.idConfigData = idConfigData;
	}
	public int getidConfigData() {
       return this.idConfigData;
   }
	private  final int idConfigData;
}