package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mPriceInOutletData;

public class mPriceInOutletDA {
	public mPriceInOutletDA(SQLiteDatabase db) {
		mPriceInOutletData dt = new mPriceInOutletData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intIdItemPrice
				+ " TEXT PRIMARY KEY," + dt.Property_intProductCode
				+ " TEXT NULL," + dt.Property_txtBranchCode + " TEXT  NULL,"
				+ dt.Property_txtOutletCode + " TEXT  NULL,"
				+ dt.Property_txtOutletName + " TEXT  NULL,"
				+ dt.Property_txtProductName + " TEXT  NULL,"
				+ dt.Property_decPriceHJD + " TEXT  NULL"
				+ ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	public List<mPriceInOutletData> getDataBytxtOutletCodeByintProductCode(SQLiteDatabase db, String txtOutletCode,String intProductCode) {
		mPriceInOutletData dt = new mPriceInOutletData();
		String selectQuery = " SELECT "+ dt.Property_decPriceHJD + "," +
									     dt.Property_intIdItemPrice + "," +
							             dt.Property_intProductCode + "," +
							             dt.Property_txtBranchCode + "," +
							             dt.Property_txtOutletCode + "," +
							             dt.Property_txtOutletName + "," +
				                         dt.Property_txtProductName+
							 " FROM " + TABLE_CONTACTS +
							 " WHERE "+dt.Property_txtOutletCode+"='" + txtOutletCode + "' " +
							 " AND "+dt.Property_intProductCode+"='" + intProductCode + "'" ;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		List<mPriceInOutletData> listData=filldata(cursor);
		cursor.close();
		return listData;
	}
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mPriceInOutlet;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveData(SQLiteDatabase db, mPriceInOutletData data) {
		mPriceInOutletData dt = new mPriceInOutletData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_All
				+ ") " + "values('"
				+ String.valueOf(data.get_decPriceHJD()) + "','"
				+ String.valueOf(data.get_intIdItemPrice()) + "','"
				+ String.valueOf(data.get_intProductCode()) + "','"
				+ String.valueOf(data.get_txtBranchCode()) + "','"
				+ String.valueOf(data.get_txtOutletCode()) + "','"
				+ String.valueOf(data.get_txtOutletName()) + "','"
				+ String.valueOf(data.get_txtProductName()) + "'"
				+ ")");
	}
	public void DeleteAllData(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
	}
	// Getting single contact
	public mPriceInOutletData getData(SQLiteDatabase db, String id) {
		mPriceInOutletData dt = new mPriceInOutletData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_decPriceHJD , dt.Property_intIdItemPrice 
				, dt.Property_intProductCode
				, dt.Property_txtBranchCode
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				, dt.Property_txtProductName
				},
				dt.Property_intIdItemPrice + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
			List<mPriceInOutletData> listData=filldata(cursor);
			mPriceInOutletData contact = new mPriceInOutletData();
			if(listData.size()==0){
				contact=null;
			}else{
				contact=listData.get(0);
			}
		cursor.close();
		return contact;
	}
	public List<mPriceInOutletData> getDataByintProductCode(SQLiteDatabase db, String id) {
		mPriceInOutletData dt = new mPriceInOutletData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_decPriceHJD , dt.Property_intIdItemPrice 
				, dt.Property_intProductCode
				, dt.Property_txtBranchCode
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				, dt.Property_txtProductName
				},
				dt.Property_intProductCode + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
			List<mPriceInOutletData> listData=filldata(cursor);
		cursor.close();
		return listData;
	}
	private List<mPriceInOutletData> filldata(Cursor cursor){
		List<mPriceInOutletData> listData=new ArrayList<mPriceInOutletData>();
		if (cursor != null){
			if (cursor.moveToFirst()) {
				do {
					mPriceInOutletData contact = new mPriceInOutletData();
					contact.set_decPriceHJD(cursor.getString(0));
					contact.set_intIdItemPrice(cursor.getString(1));
					contact.set_intProductCode(cursor.getString(2));
					contact.set_txtBranchCode(cursor.getString(3));
					contact.set_txtOutletCode(cursor.getString(4));
					contact.set_txtOutletName(cursor.getString(5));
					contact.set_txtProductName(cursor.getString(6));
					// Adding contact to list
					listData.add(contact);
				} while (cursor.moveToNext());
			}
		}
		return listData;
	}
	public List<mPriceInOutletData> getDataAreaByCabId(SQLiteDatabase db, String Cabid) {
		List<mPriceInOutletData> contactList = new ArrayList<mPriceInOutletData>();
		mPriceInOutletData dt = new mPriceInOutletData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_decPriceHJD 
				, dt.Property_intIdItemPrice
				, dt.Property_txtBranchCode
				, dt.Property_intProductCode
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				},
				dt.Property_txtBranchCode + "=?", new String[] { String.valueOf(Cabid) },
				null, null, null, null);
		contactList=filldata(cursor);
		cursor.close();
		return contactList;
	}
	// Getting All Contacts
	public List<mPriceInOutletData> getAllData(SQLiteDatabase db) {
		List<mPriceInOutletData> contactList = new ArrayList<mPriceInOutletData>();
		// Select All Query
		mPriceInOutletData dt = new mPriceInOutletData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		contactList=filldata(cursor);
		cursor.close();
		// return contact list
		return contactList;
	}

	// Deleting single contact
	public void deleteContact(SQLiteDatabase db, int id) {
		mPriceInOutletData dt = new mPriceInOutletData();
		db.delete(TABLE_CONTACTS, dt.Property_intIdItemPrice + " = ?",
				new String[] { String.valueOf(id) });
		// db.close();
	}

	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		int countData = cursor.getCount();
		cursor.close();
		// return count
		return countData;
	}
}
