package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mEmployeeAreaData;

public class mEmployeeAreaDA {

	public mEmployeeAreaDA(SQLiteDatabase db) {
		mEmployeeAreaData dt = new mEmployeeAreaData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intID
				+ " TEXT PRIMARY KEY," + dt.Property_intBranchId
				+ " TEXT NULL," + dt.Property_intChannelId + " TEXT  NULL,"
				+ dt.Property_intEmployeeId + " TEXT  NULL,"
				+ dt.Property_intOutletId + " TEXT  NULL,"
				+ dt.Property_intRayonId + " TEXT  NULL,"
				+ dt.Property_intRegionId + " TEXT  NULL,"
				+ dt.Property_txtBranchCode + " TEXT  NULL,"
				+ dt.Property_txtBranchName + " TEXT  NULL,"
				+ dt.Property_txtName + " TEXT  NULL,"
				+ dt.Property_txtNIK + " TEXT  NULL,"
				+ dt.Property_txtLatitude + " TEXT  NULL,"
				+ dt.Property_txtLongitude + " TEXT  NULL,"
				+ dt.Property_txtOutletCode + " TEXT  NULL,"
				+ dt.Property_txtOutletName + " TEXT  NULL,"
				+ dt.Property_txtRayonCode + " TEXT  NULL,"
				+ dt.Property_txtRayonName + " TEXT  NULL,"
				+ dt.Property_txtRegionName + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mEmployeeArea;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mEmployeeAreaData data) {
		mEmployeeAreaData dt = new mEmployeeAreaData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intID
				+ "," + dt.Property_intBranchId 
				+ ","+ dt.Property_intChannelId 
				+ "," + dt.Property_intEmployeeId 
				+ "," + dt.Property_intOutletId
				+ "," + dt.Property_intRayonId
				+ "," + dt.Property_intRegionId
				+ "," + dt.Property_txtBranchCode
				+ "," + dt.Property_txtBranchName
				+ "," + dt.Property_txtName
				+ "," + dt.Property_txtNIK
				+ "," + dt.Property_txtLatitude
				+ "," + dt.Property_txtLongitude
				+ "," + dt.Property_txtOutletCode
				+ "," + dt.Property_txtOutletName
				+ "," + dt.Property_txtRayonCode
				+ "," + dt.Property_txtRayonName
				+ "," + dt.Property_txtRegionName 
				+ ") " + "values('"
				+ String.valueOf(data.get_intID()) + "','"
				+ String.valueOf(data.get_intBranchId()) + "','"
				+ String.valueOf(data.get_intChannelId()) + "','"
				+ String.valueOf(data.get_intEmployeeId()) + "','"
				+ String.valueOf(data.get_intOutletId()) + "','"
				+ String.valueOf(data.get_intRayonId()) + "','"
				+ String.valueOf(data.get_intRegionId()) + "','"
				+ String.valueOf(data.get_txtBranchCode()) + "','"
				+ String.valueOf(data.get_txtBranchName()) + "','"
				+ String.valueOf(data.get_txtName()) + "','"
				+ String.valueOf(data.get_txtNIK()) + "','"
				+ String.valueOf(data.get_txtLatitude()) + "','"
				+ String.valueOf(data.get_txtLongitude()) + "','"
				+ String.valueOf(data.get_txtOutletCode()) + "','"
				+ String.valueOf(data.get_txtOutletName()) + "','"
				+ String.valueOf(data.get_txtRayonCode()) + "','"
				+ String.valueOf(data.get_txtRayonName()) + "','"
				+ String.valueOf(data.get_txtRegionName()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mEmployeeAreaData getData(SQLiteDatabase db, int id) {
		mEmployeeAreaData dt = new mEmployeeAreaData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intID , dt.Property_intBranchId 
				, dt.Property_intChannelId 
				, dt.Property_intEmployeeId 
				, dt.Property_intOutletId
				, dt.Property_intRayonId
				, dt.Property_intRegionId
				, dt.Property_txtBranchCode
				, dt.Property_txtBranchName
				, dt.Property_txtName
				, dt.Property_txtNIK
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				, dt.Property_txtRayonCode
				, dt.Property_txtRayonName
				, dt.Property_txtRegionName},
				dt.Property_intID + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mEmployeeAreaData contact = new mEmployeeAreaData();
		if (cursor.getCount() > 0) {
			contact.set_intID(cursor.getString(0));
			contact.set_intBranchId(cursor.getString(1));
			contact.set_intChannelId(cursor.getString(2));
			contact.set_intEmployeeId(cursor.getString(3));
			contact.set_intOutletId(cursor.getString(4));
			contact.set_intRayonId(cursor.getString(5));
			contact.set_intRegionId(cursor.getString(6));
			contact.set_txtBranchCode(cursor.getString(7));
			contact.set_txtBranchName(cursor.getString(8));
			contact.set_txtName(cursor.getString(9));
			contact.set_txtNIK(cursor.getString(10));
			contact.set_txtLatitude(cursor.getString(11));
			contact.set_txtLongitude(cursor.getString(12));
			contact.set_txtOutletCode(cursor.getString(13));
			contact.set_txtOutletName(cursor.getString(14));
			contact.set_txtRayonCode(cursor.getString(15));
			contact.set_txtRayonName(cursor.getString(16));
			contact.set_txtRegionName(cursor.getString(17));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public mEmployeeAreaData getDataByOutlet(SQLiteDatabase db, String idOutlet) {
		mEmployeeAreaData dt = new mEmployeeAreaData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intID , dt.Property_intBranchId 
				, dt.Property_intChannelId 
				, dt.Property_intEmployeeId 
				, dt.Property_intOutletId
				, dt.Property_intRayonId
				, dt.Property_intRegionId
				, dt.Property_txtBranchCode
				, dt.Property_txtBranchName
				, dt.Property_txtName
				, dt.Property_txtNIK
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				, dt.Property_txtRayonCode
				, dt.Property_txtRayonName
				, dt.Property_txtRegionName},
				dt.Property_txtOutletCode + "=?", new String[] { String.valueOf(idOutlet) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mEmployeeAreaData contact = new mEmployeeAreaData();
		if (cursor.getCount() > 0) {
			contact.set_intID(cursor.getString(0));
			contact.set_intBranchId(cursor.getString(1));
			contact.set_intChannelId(cursor.getString(2));
			contact.set_intEmployeeId(cursor.getString(3));
			contact.set_intOutletId(cursor.getString(4));
			contact.set_intRayonId(cursor.getString(5));
			contact.set_intRegionId(cursor.getString(6));
			contact.set_txtBranchCode(cursor.getString(7));
			contact.set_txtBranchName(cursor.getString(8));
			contact.set_txtName(cursor.getString(9));
			contact.set_txtNIK(cursor.getString(10));
			contact.set_txtLatitude(cursor.getString(11));
			contact.set_txtLongitude(cursor.getString(12));
			contact.set_txtOutletCode(cursor.getString(13));
			contact.set_txtOutletName(cursor.getString(14));
			contact.set_txtRayonCode(cursor.getString(15));
			contact.set_txtRayonName(cursor.getString(16));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public List<mEmployeeAreaData> getDataAreaByCabId(SQLiteDatabase db, String Cabid) {
		List<mEmployeeAreaData> contactList = new ArrayList<mEmployeeAreaData>();
		mEmployeeAreaData dt = new mEmployeeAreaData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intID , dt.Property_intBranchId 
				, dt.Property_intChannelId 
				, dt.Property_intEmployeeId 
				, dt.Property_intOutletId
				, dt.Property_intRayonId
				, dt.Property_intRegionId
				, dt.Property_txtBranchCode
				, dt.Property_txtBranchName
				, dt.Property_txtName
				, dt.Property_txtNIK
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtOutletCode
				, dt.Property_txtOutletName
				, dt.Property_txtRayonCode
				, dt.Property_txtRayonName
				, dt.Property_txtRegionName},
				dt.Property_txtBranchCode + "=?", new String[] { String.valueOf(Cabid) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mEmployeeAreaData contact = new mEmployeeAreaData();
		if (cursor.moveToFirst()) {
			do {
				contact = new mEmployeeAreaData();
				contact.set_intID(cursor.getString(0));
				contact.set_intBranchId(cursor.getString(1));
				contact.set_intChannelId(cursor.getString(2));
				contact.set_intEmployeeId(cursor.getString(3));
				contact.set_intOutletId(cursor.getString(4));
				contact.set_intRayonId(cursor.getString(5));
				contact.set_intRegionId(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtBranchName(cursor.getString(8));
				contact.set_txtName(cursor.getString(9));
				contact.set_txtNIK(cursor.getString(10));
				contact.set_txtLatitude(cursor.getString(11));
				contact.set_txtLongitude(cursor.getString(12));
				contact.set_txtOutletCode(cursor.getString(13));
				contact.set_txtOutletName(cursor.getString(14));
				contact.set_txtRayonCode(cursor.getString(15));
				contact.set_txtRayonName(cursor.getString(16));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	// Getting All Contacts
	public List<mEmployeeAreaData> getAllData(SQLiteDatabase db) {
		List<mEmployeeAreaData> contactList = new ArrayList<mEmployeeAreaData>();
		// Select All Query
		mEmployeeAreaData dt = new mEmployeeAreaData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mEmployeeAreaData contact = new mEmployeeAreaData();
				contact.set_intID(cursor.getString(0));
				contact.set_intBranchId(cursor.getString(1));
				contact.set_intChannelId(cursor.getString(2));
				contact.set_intEmployeeId(cursor.getString(3));
				contact.set_intOutletId(cursor.getString(4));
				contact.set_intRayonId(cursor.getString(5));
				contact.set_intRegionId(cursor.getString(6));
				contact.set_txtBranchCode(cursor.getString(7));
				contact.set_txtBranchName(cursor.getString(8));
				contact.set_txtName(cursor.getString(9));
				contact.set_txtNIK(cursor.getString(10));
				contact.set_txtLatitude(cursor.getString(11));
				contact.set_txtLongitude(cursor.getString(12));
				contact.set_txtOutletCode(cursor.getString(13));
				contact.set_txtOutletName(cursor.getString(14));
				contact.set_txtRayonCode(cursor.getString(15));
				contact.set_txtRayonName(cursor.getString(16));
				contact.set_txtRegionName(cursor.getString(17));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Deleting single contact
	public void deleteContact(SQLiteDatabase db, int id) {
		mEmployeeAreaData dt = new mEmployeeAreaData();
		db.delete(TABLE_CONTACTS, dt.Property_intID + " = ?",
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
