package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mAbsenCheckInData;

public class mAbsenCheckInDA {
	public mAbsenCheckInDA(SQLiteDatabase db) {
		mAbsenCheckInData dt = new mAbsenCheckInData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_IntId
				+ " TEXT PRIMARY KEY," + dt.Property_dtDateCheckIn
				+ " TEXT NULL," + dt.Property_dtDateCheckOut + " TEXT  NULL,"
				+ dt.Property_txtAcc + " TEXT  NULL,"
				+ dt.Property_txtBranchCode + " TEXT  NULL,"
				+ dt.Property_txtDeviceId + " TEXT  NULL,"
				+ dt.Property_txtLatitude + " TEXT  NULL,"
				+ dt.Property_txtLongitude + " TEXT  NULL,"
				+ dt.Property_txtNik + " TEXT  NULL,"
				+ dt.Property_txtOutletCode + " TEXT  NULL,"
				+ dt.Property_txtUserId + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mAbsenCheckin;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mAbsenCheckInData data) {
		mAbsenCheckInData dt = new mAbsenCheckInData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_IntId
				+ "," + dt.Property_dtDateCheckIn 
				+ ","+ dt.Property_dtDateCheckOut 
				+ "," + dt.Property_txtAcc 
				+ "," + dt.Property_txtBranchCode
				+ "," + dt.Property_txtDeviceId
				+ "," + dt.Property_txtLatitude
				+ "," + dt.Property_txtLongitude
				+ "," + dt.Property_txtNik
				+ "," + dt.Property_txtOutletCode
				+ "," + dt.Property_txtUserId
				+ ") " + "values('"
				+ String.valueOf(data.get_IntId()) + "','"
				+ String.valueOf(data.get_dtDateCheckIn()) + "','"
				+ String.valueOf(data.get_dtDateCheckOut()) + "','"
				+ String.valueOf(data.get_txtAcc()) + "','"
				+ String.valueOf(data.get_txtBranchCode()) + "','"
				+ String.valueOf(data.get_txtDeviceId()) + "','"
				+ String.valueOf(data.get_txtLatitude()) + "','"
				+ String.valueOf(data.get_txtLongitude()) + "','"
				+ String.valueOf(data.get_txtNik()) + "','"
				+ String.valueOf(data.get_txtOutletCode()) + "','"
				+ String.valueOf(data.get_txtUserId()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mAbsenCheckInData getData(SQLiteDatabase db, String id) {
		mAbsenCheckInData dt = new mAbsenCheckInData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_dtDateCheckIn , dt.Property_dtDateCheckOut 
				, dt.Property_IntId 
				, dt.Property_txtAcc 
				, dt.Property_txtBranchCode
				, dt.Property_txtDeviceId
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtNik
				, dt.Property_txtOutletCode
				, dt.Property_txtUserId},
				dt.Property_IntId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mAbsenCheckInData contact = new mAbsenCheckInData();
		if (cursor.getCount() > 0) {
			contact.set_dtDateCheckIn(cursor.getString(0));
			contact.set_dtDateCheckOut(cursor.getString(1));
			contact.set_IntId(cursor.getString(2));
			contact.set_txtAcc(cursor.getString(3));
			contact.set_txtBranchCode(cursor.getString(4));
			contact.set_txtDeviceId(cursor.getString(5));
			contact.set_txtLatitude(cursor.getString(6));
			contact.set_txtLongitude(cursor.getString(7));
			contact.set_txtNik(cursor.getString(8));
			contact.set_txtOutletCode(cursor.getString(9));
			contact.set_txtUserId(cursor.getString(10));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public List<mAbsenCheckInData> getDataAreaByCabId(SQLiteDatabase db, String Cabid) {
		List<mAbsenCheckInData> contactList = new ArrayList<mAbsenCheckInData>();
		mAbsenCheckInData dt = new mAbsenCheckInData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_dtDateCheckIn , dt.Property_dtDateCheckOut 
				, dt.Property_IntId 
				, dt.Property_txtAcc 
				, dt.Property_txtBranchCode
				, dt.Property_txtDeviceId
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtNik
				, dt.Property_txtOutletCode
				, dt.Property_txtUserId},
				dt.Property_txtBranchCode + "=?", new String[] { String.valueOf(Cabid) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mAbsenCheckInData contact = new mAbsenCheckInData();
		if (cursor.moveToFirst()) {
			do {
				contact = new mAbsenCheckInData();
				contact.set_dtDateCheckIn(cursor.getString(0));
				contact.set_dtDateCheckOut(cursor.getString(1));
				contact.set_IntId(cursor.getString(2));
				contact.set_txtAcc(cursor.getString(3));
				contact.set_txtBranchCode(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtLatitude(cursor.getString(6));
				contact.set_txtLongitude(cursor.getString(7));
				contact.set_txtNik(cursor.getString(8));
				contact.set_txtOutletCode(cursor.getString(9));
				contact.set_txtUserId(cursor.getString(10));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	// Getting All Contacts
	public List<mAbsenCheckInData> getAllData(SQLiteDatabase db) {
		List<mAbsenCheckInData> contactList = new ArrayList<mAbsenCheckInData>();
		// Select All Query
		mAbsenCheckInData dt = new mAbsenCheckInData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mAbsenCheckInData contact = new mAbsenCheckInData();
				contact.set_dtDateCheckIn(cursor.getString(0));
				contact.set_dtDateCheckOut(cursor.getString(1));
				contact.set_IntId(cursor.getString(2));
				contact.set_txtAcc(cursor.getString(3));
				contact.set_txtBranchCode(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtLatitude(cursor.getString(6));
				contact.set_txtLongitude(cursor.getString(7));
				contact.set_txtNik(cursor.getString(8));
				contact.set_txtOutletCode(cursor.getString(9));
				contact.set_txtUserId(cursor.getString(10));
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
		mAbsenCheckInData dt = new mAbsenCheckInData();
		db.delete(TABLE_CONTACTS, dt.Property_IntId + " = ?",
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
