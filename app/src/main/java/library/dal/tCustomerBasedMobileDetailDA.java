package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tCustomerBasedMobileDetailData;

public class tCustomerBasedMobileDetailDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_NAME = new clsHardCode().txtTable_tCustomerBasedMobileDetail;

	// Contacts Table Columns names
	public tCustomerBasedMobileDetailDA(SQLiteDatabase db) {
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
										+ dt.Property_intTrCustomerIdDetail + " TEXT PRIMARY KEY,"
										+ dt.Property_intTrCustomerId + " TEXT NULL,"
										+ dt.Property_txtNamaDepan + " TEXT NULL,"
										+ dt.Property_intNo + " TEXT NULL,"
										+ dt.Property_intPIC + " TEXT NULL,"
										+ dt.Property_bitActive + " TEXT NULL,"
										+ dt.Property_dtInserted + " TEXT NULL,"
										+ dt.Property_dtUpdated + " TEXT NULL,"
										+ dt.Property_txtInsertedBy + " TEXT NULL,"
										+ dt.Property_txtUpdatedBy + " TEXT NULL" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}
				
	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		// Create tables again
	}
				
	/**
	* All CRUD(Create, Read, Update, Delete) Operations
	**/
				
	// Adding new contact
	public void SaveDatatCustomerBasedMobileDetailData(SQLiteDatabase db,tCustomerBasedMobileDetailData data) {
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		db.execSQL("INSERT OR REPLACE into "+TABLE_NAME+" ("+dt.Property_ALL+") "+
				   "values('"	+String.valueOf(data.get_intTrCustomerIdDetail())+"','"
								+String.valueOf(data.get_intTrCustomerId())+"','"
								+String.valueOf(data.get_txtNamaDepan())+"','"
								+String.valueOf(data.get_intNo())+"','"
								+String.valueOf(data.get_intPIC())+"','"
								+String.valueOf(data.get_bitActive())+"','"
								+String.valueOf(data.get_dtInserted())+"','"
								+String.valueOf(data.get_dtUpdated())+"','"
								+String.valueOf(data.get_txtInsertedBy())+"','"
								+String.valueOf(data.get_txtUpdatedBy())+"')"
					);
	}
				
	// Getting single contact
	public tCustomerBasedMobileDetailData getData(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		String[] tableColumns = new String[] { 
												dt.Property_intTrCustomerIdDetail,
												dt.Property_intTrCustomerId,
												dt.Property_txtNamaDepan, 
												dt.Property_intNo, 
												dt.Property_intPIC,
												dt.Property_bitActive,
												dt.Property_dtInserted,
												dt.Property_dtUpdated,
												dt.Property_txtInsertedBy,
												dt.Property_txtUpdatedBy
											};
		String whereClause = dt.Property_intTrCustomerIdDetail + "=?";
		String[] whereArgs = new String[] {
											String.valueOf(id)
										  };
		String groupBy = null;
		String havingBy = null;
		String orderBy = dt.Property_intTrCustomerId;
					
		Cursor cursor = db.query(TABLE_NAME,
								 tableColumns,
								 whereClause,
								 whereArgs, 
								 groupBy, 
								 havingBy, 
								 orderBy);
		if (cursor != null)
		cursor.moveToFirst();
		tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();
		contact.set_intTrCustomerIdDetail(cursor.getString(0));
		contact.set_intTrCustomerId(cursor.getString(1));
		contact.set_txtNamaDepan(cursor.getString(2));
		contact.set_intNo(cursor.getString(3));
		contact.set_intPIC(cursor.getString(4));
		contact.set_bitActive(cursor.getString(5));
		contact.set_dtInserted(cursor.getString(6));
		contact.set_dtUpdated(cursor.getString(7));
		contact.set_txtInsertedBy(cursor.getString(8));
		contact.set_txtUpdatedBy(cursor.getString(9));
		// return contact
		cursor.close();
		return contact;
	}
				
	// Getting All Contacts
	public List<tCustomerBasedMobileDetailData> getAllData(SQLiteDatabase db) {
		List<tCustomerBasedMobileDetailData> contactList = new ArrayList<tCustomerBasedMobileDetailData>();
		// Select All Query
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();
				contact.set_intTrCustomerIdDetail(cursor.getString(0));
				contact.set_intTrCustomerId(cursor.getString(1));
				contact.set_txtNamaDepan(cursor.getString(2));
				contact.set_intNo(cursor.getString(3));
				contact.set_intPIC(cursor.getString(4));
				contact.set_bitActive(cursor.getString(5));
				contact.set_dtInserted(cursor.getString(6));
				contact.set_dtUpdated(cursor.getString(7));
				contact.set_txtInsertedBy(cursor.getString(8));
				contact.set_txtUpdatedBy(cursor.getString(9));
				contactList.add(contact);
				} while (cursor.moveToNext());
		}
			cursor.close();
			// return contact list
			return contactList;
	}

	public List<tCustomerBasedMobileDetailData> getAllDataByHeaderId(SQLiteDatabase db, String id) {
		List<tCustomerBasedMobileDetailData> contactList = new ArrayList<tCustomerBasedMobileDetailData>();
		// Select All Query
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME + " WHERE " + dt.Property_intTrCustomerId + "='" + id + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();
				contact.set_intTrCustomerIdDetail(cursor.getString(0));
				contact.set_intTrCustomerId(cursor.getString(1));
				contact.set_txtNamaDepan(cursor.getString(2));
				contact.set_intNo(cursor.getString(3));
				contact.set_intPIC(cursor.getString(4));
				contact.set_bitActive(cursor.getString(5));
				contact.set_dtInserted(cursor.getString(6));
				contact.set_dtUpdated(cursor.getString(7));
				contact.set_txtInsertedBy(cursor.getString(8));
				contact.set_txtUpdatedBy(cursor.getString(9));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
				
	public List<tCustomerBasedMobileDetailData> getPushData(SQLiteDatabase db) {
		List<tCustomerBasedMobileDetailData> contactList = null;
		// Select All Query
		tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCustomerBasedMobileDetailData>();
			do {
				tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();
				contact.set_intTrCustomerIdDetail(cursor.getString(0));
				contact.set_intTrCustomerId(cursor.getString(1));
				contact.set_txtNamaDepan(cursor.getString(2));
				contact.set_intNo(cursor.getString(3));
				contact.set_intPIC(cursor.getString(4));
				contact.set_bitActive(cursor.getString(5));
				contact.set_dtInserted(cursor.getString(6));
				contact.set_dtUpdated(cursor.getString(7));
				contact.set_txtInsertedBy(cursor.getString(8));
				contact.set_txtUpdatedBy(cursor.getString(9));
                contactList.add(contact);
				} while (cursor.moveToNext());
		}
			cursor.close();
			// return contact list
			return contactList;
	}
				
				
				
				// Deleting single contact
	public void deleteByID(SQLiteDatabase db,String id) {		
		tCustomerBasedMobileDetailData dt = new tCustomerBasedMobileDetailData();
		String whereClause =dt.Property_intTrCustomerIdDetail + " = ?";
		String[] whereArgs = new String[] {
											String.valueOf(id)
										  };
					
		db.delete(TABLE_NAME, whereClause,whereArgs);
	}

	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_NAME);
	}

	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}

    public tCustomerBasedMobileDetailData getAllDataByHeaderIdandintPIC(SQLiteDatabase db, String id) {
        tCustomerBasedMobileDetailData dt=new tCustomerBasedMobileDetailData();
        String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME + " WHERE " + dt.Property_intTrCustomerId + "='" + id + "' AND " + dt.Property_intPIC + "='1'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        tCustomerBasedMobileDetailData contact = new tCustomerBasedMobileDetailData();

        if (cursor.moveToFirst()) {
                contact.set_intTrCustomerIdDetail(cursor.getString(0));
                contact.set_intTrCustomerId(cursor.getString(1));
                contact.set_txtNamaDepan(cursor.getString(2));
                contact.set_intNo(cursor.getString(3));
                contact.set_intPIC(cursor.getString(4));
                contact.set_bitActive(cursor.getString(5));
                contact.set_dtInserted(cursor.getString(6));
                contact.set_dtUpdated(cursor.getString(7));
                contact.set_txtInsertedBy(cursor.getString(8));
                contact.set_txtUpdatedBy(cursor.getString(9));

        }
        cursor.close();
        return contact;
    }
}
