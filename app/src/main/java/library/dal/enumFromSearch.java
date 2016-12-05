package library.dal;

public enum enumFromSearch {
	 GRN  (1),  
	 Sales	(2),
	 StockOpnam	(3)
	 ; 
	enumFromSearch(int idFromSearch) {
		this.idFromSearch = idFromSearch;
	}
	public int getidFromSearch() {
       return this.idFromSearch;
   }
	private  final int idFromSearch;
}
