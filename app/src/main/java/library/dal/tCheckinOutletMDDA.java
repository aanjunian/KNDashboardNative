package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tCheckinOutletMDData;

public class tCheckinOutletMDDA {
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tCheckinOutletMD;

	// Contacts Table Columns names

	public tCheckinOutletMDDA(SQLiteDatabase db) {
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_txtDataId + " TEXT PRIMARY KEY," 
				+ dt.Property_dtDate + " TEXT NULL,"
				+ dt.Property_intSync + " TEXT NULL,"
				+ dt.Property_txtAcc + " TEXT NULL,"
				+ dt.Property_txtAddress + " TEXT NULL,"
				+ dt.Property_txtBranchCode + " TEXT NULL,"
				+ dt.Property_txtLatitude + " TEXT NULL,"
				+ dt.Property_txtLongitude + " TEXT NULL,"
				+ dt.Property_txtName + " TEXT NULL,"
				+ dt.Property_txtNoTelp + " TEXT NULL,"
				+ dt.Property_txtRegion + " TEXT NULL"
				+ ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		// Create tables again
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDatatCheckinOutletMDData(SQLiteDatabase db,tCheckinOutletMDData data) {
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_dtDate+",'"
				+dt.Property_intSync+"','"
				+dt.Property_txtAcc+"','"
				+dt.Property_txtAddress+"','"
				+dt.Property_txtBranchCode+"','"
				+dt.Property_txtDataId+"','"
				+dt.Property_txtLatitude+"','"
				+dt.Property_txtLongitude+"','"
				+dt.Property_txtName+"','"
				+dt.Property_txtNoTelp+"','"
				+dt.Property_txtRegion+"') "+
			"values('"	+String.valueOf(data.get_dtDate())+"','"
				+String.valueOf(data.get_intSync())+"','"
				+String.valueOf(data.get_txtAcc())+"','"
				+String.valueOf(data.get_txtAddress())+"','"
				+String.valueOf(data.get_txtBranchCode())+"','"
				+String.valueOf(data.get_txtDataId())+"','"
				+String.valueOf(data.get_txtLatitude())+"','"
				+String.valueOf(data.get_txtLongitude())+"','"
				+String.valueOf(data.get_txtName())+"','"
				+String.valueOf(data.get_txtNoTelp())+"','"
				+String.valueOf(data.get_txtRegion())+"')");
		
	}

	// Getting single contact
	public tCheckinOutletMDData getData(SQLiteDatabase db,String id) {
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_dtDate,
				dt.Property_intSync,dt.Property_txtAcc, dt.Property_txtAddress, dt.Property_txtBranchCode
				,dt.Property_txtDataId
				,dt.Property_txtLatitude
				,dt.Property_txtLongitude
				,dt.Property_txtName
				,dt.Property_txtNoTelp
				,dt.Property_txtRegion}, dt.Property_txtDataId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		tCheckinOutletMDData contact = new tCheckinOutletMDData();
		contact.set_dtDate(cursor.getString(0));
		contact.set_intSync(cursor.getString(1));
		contact.set_txtAcc(cursor.getString(2));
		contact.set_txtAddress(cursor.getString(3));
		contact.set_txtBranchCode(cursor.getString(4));
		contact.set_txtDataId(cursor.getString(5));
		contact.set_txtLatitude(cursor.getString(6));
		contact.set_txtLongitude(cursor.getString(7));
		contact.set_txtName(cursor.getString(8));
		contact.set_txtNoTelp(cursor.getString(9));
		contact.set_txtRegion(cursor.getString(10));
		// return contact
		cursor.close();
		return contact;
	}
	
	public tCheckinOutletMDData getAllDataToPushData(SQLiteDatabase db) {
		// Select All Query
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_intSync+"='0'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		tCheckinOutletMDData contact = null;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				contact=new tCheckinOutletMDData();
				contact.set_txtDataId(cursor.getString(0));
				contact.set_txtName(cursor.getString(1));
				contact.set_txtAddress(cursor.getString(2));
				contact.set_txtNoTelp(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtLatitude(cursor.getString(5));
				contact.set_txtAcc(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtRegion(cursor.getString(8));
				contact.set_dtDate(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				// Adding contact to list
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contact;
	}
	
	// Getting All Contacts
	public List<tCheckinOutletMDData> getAllData(SQLiteDatabase db) {
		List<tCheckinOutletMDData> contactList = new ArrayList<tCheckinOutletMDData>();
		// Select All Query
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tCheckinOutletMDData contact = new tCheckinOutletMDData();
				contact.set_txtDataId(cursor.getString(0));
				contact.set_txtName(cursor.getString(1));
				contact.set_txtAddress(cursor.getString(2));
				contact.set_txtNoTelp(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtLatitude(cursor.getString(5));
				contact.set_txtAcc(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtRegion(cursor.getString(8));
				contact.set_dtDate(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public List<tCheckinOutletMDData> getAllDataActive(SQLiteDatabase db) {
		List<tCheckinOutletMDData> contactList = null;
		// Select All Query
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync+" not in('-1')";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCheckinOutletMDData>();
			do {
				tCheckinOutletMDData contact = new tCheckinOutletMDData();
				contact.set_txtDataId(cursor.getString(0));
				contact.set_txtName(cursor.getString(1));
				contact.set_txtAddress(cursor.getString(2));
				contact.set_txtNoTelp(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtLatitude(cursor.getString(5));
				contact.set_txtAcc(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtRegion(cursor.getString(8));
				contact.set_dtDate(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	// Getting All Contacts
	public List<tCheckinOutletMDData> getAllDataByOutletCode(SQLiteDatabase db,String txtBranchCode) {
		List<tCheckinOutletMDData> contactList = null;
		// Select All Query
		tCheckinOutletMDData dt=new tCheckinOutletMDData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtBranchCode+"='"+txtBranchCode+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCheckinOutletMDData>();
			do {
				tCheckinOutletMDData contact = new tCheckinOutletMDData();
				contact.set_txtDataId(cursor.getString(0));
				contact.set_txtName(cursor.getString(1));
				contact.set_txtAddress(cursor.getString(2));
				contact.set_txtNoTelp(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtLatitude(cursor.getString(5));
				contact.set_txtAcc(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtRegion(cursor.getString(8));
				contact.set_dtDate(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	// Deleting single contact
	public void deleteContact(SQLiteDatabase db,String id) {		
		tCheckinOutletMDData dt = new tCheckinOutletMDData();
		db.delete(TABLE_CONTACTS, dt.Property_txtDataId + " = ?",
				new String[] { String.valueOf(id) });
	}

	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
	}
	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}
}
