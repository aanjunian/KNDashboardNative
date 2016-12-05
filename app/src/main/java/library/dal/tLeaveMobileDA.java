package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tLeaveMobileData;

public class tLeaveMobileDA {
	public tLeaveMobileDA(SQLiteDatabase db) {
		tLeaveMobileData dt = new tLeaveMobileData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intLeaveId
				+ " INTEGER PRIMARY KEY," + dt.Property_intLeaveIdSync
				+ " TEXT NULL," + dt.Property_dtLeave + " TEXT  NULL,"
				+ dt.Property_intSubmit + " TEXT  NULL,"
				+ dt.Property_txtAlasan + " TEXT  NULL,"
				+ dt.Property_txtDeviceId + " TEXT  NULL,"
				+ dt.Property_txtTypeAlasan + " TEXT  NULL,"
				+ dt.Property_txtTypeAlasanName + " TEXT  NULL,"
				+ dt.Property_txtUserId + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tLeaveMobile;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, tLeaveMobileData data) {
		tLeaveMobileData dt = new tLeaveMobileData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.PropertyAll + ") " + "values('"
				+ String.valueOf(data.get_dtLeave()) + "','"
				+ String.valueOf(data.get_intLeaveId()) + "','"
				+ String.valueOf(data.get_intLeaveIdSync()) + "','"
				+ String.valueOf(data.get_intSubmit()) + "','"
				+ String.valueOf(data.get_txtAlasan()) + "','"
				+ String.valueOf(data.get_txtDeviceId()) + "','"
				+ String.valueOf(data.get_txtTypeAlasan()) + "','"
				+ String.valueOf(data.get_txtUserId()) + "','"
				+ String.valueOf(data.get_txtTypeAlasanName()) +"'"
				+ ")");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public tLeaveMobileData getData(SQLiteDatabase db, String id) {
		tLeaveMobileData dt = new tLeaveMobileData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_dtLeave, dt.Property_intLeaveId, dt.Property_intLeaveIdSync,
				dt.Property_intSubmit,dt.Property_txtAlasan,dt.Property_txtDeviceId,dt.Property_txtTypeAlasan,
				dt.Property_txtUserId,dt.Property_txtTypeAlasanName},
				dt.Property_intLeaveId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		tLeaveMobileData contact = new tLeaveMobileData();
		if (cursor.getCount() > 0) {
			contact.set_dtLeave(String.valueOf( cursor.getString(0)));
			contact.set_intLeaveId(cursor.getString(1));
			contact.set_intLeaveIdSync(cursor.getString(2));
			contact.set_intSubmit(cursor.getString(3));
			contact.set_txtAlasan(cursor.getString(4));
			contact.set_txtDeviceId(cursor.getString(5));
			contact.set_txtTypeAlasan(cursor.getString(6));
			contact.set_txtUserId(cursor.getString(7));
			contact.set_txtTypeAlasanName(cursor.getString(8));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<tLeaveMobileData> getDataNew(SQLiteDatabase db) {
		List<tLeaveMobileData> contactList = new ArrayList<tLeaveMobileData>();
		// Select All Query
		tLeaveMobileData dt = new tLeaveMobileData();
		String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " 
				+ TABLE_CONTACTS +" WHERE " +dt.Property_intSubmit +" ='0'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tLeaveMobileData contact = new tLeaveMobileData();
				contact.set_dtLeave(String.valueOf( cursor.getString(0)));
				contact.set_intLeaveId(cursor.getString(1));
				contact.set_intLeaveIdSync(cursor.getString(2));
				contact.set_intSubmit(cursor.getString(3));
				contact.set_txtAlasan(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtTypeAlasan(cursor.getString(6));
				contact.set_txtUserId(cursor.getString(7));
				contact.set_txtTypeAlasanName(cursor.getString(8));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<tLeaveMobileData> getAllDataPushData(SQLiteDatabase db) {
		List<tLeaveMobileData> contactList = null;
		// Select All Query
		tLeaveMobileData dt = new tLeaveMobileData();
		String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " 
				+ TABLE_CONTACTS +" WHERE " +dt.Property_intLeaveIdSync +" ='0' And "+dt.Property_intSubmit+"=1" ;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tLeaveMobileData>();
			do {
				tLeaveMobileData contact = new tLeaveMobileData();
				contact.set_dtLeave(String.valueOf( cursor.getString(0)));
				contact.set_intLeaveId(cursor.getString(1));
				contact.set_intLeaveIdSync(cursor.getString(2));
				contact.set_intSubmit(cursor.getString(3));
				contact.set_txtAlasan(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtTypeAlasan(cursor.getString(6));
				contact.set_txtUserId(cursor.getString(7));
				contact.set_txtTypeAlasanName(cursor.getString(8));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	// Getting All Contacts
	public List<tLeaveMobileData> getAllData(SQLiteDatabase db) {
		List<tLeaveMobileData> contactList = new ArrayList<tLeaveMobileData>();
		// Select All Query
		tLeaveMobileData dt = new tLeaveMobileData();
		String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tLeaveMobileData contact = new tLeaveMobileData();
				contact.set_dtLeave(String.valueOf( cursor.getString(0)));
				contact.set_intLeaveId(cursor.getString(1));
				contact.set_intLeaveIdSync(cursor.getString(2));
				contact.set_intSubmit(cursor.getString(3));
				contact.set_txtAlasan(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtTypeAlasan(cursor.getString(6));
				contact.set_txtUserId(cursor.getString(7));
				contact.set_txtTypeAlasanName(cursor.getString(8));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	
	public List<tLeaveMobileData> getAllDataNew(SQLiteDatabase db) {
		List<tLeaveMobileData> contactList = new ArrayList<tLeaveMobileData>();
		// Select All Query
		tLeaveMobileData dt = new tLeaveMobileData();
		String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
				+ TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tLeaveMobileData contact = new tLeaveMobileData();
				contact.set_dtLeave(String.valueOf( cursor.getString(0)));
				contact.set_intLeaveId(cursor.getString(1));
				contact.set_intLeaveIdSync(cursor.getString(2));
				contact.set_intSubmit(cursor.getString(3));
				contact.set_txtAlasan(cursor.getString(4));
				contact.set_txtDeviceId(cursor.getString(5));
				contact.set_txtTypeAlasan(cursor.getString(6));
				contact.set_txtUserId(cursor.getString(7));
				contact.set_txtTypeAlasanName(cursor.getString(8));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Deleting single contact
	public void deleteContact(SQLiteDatabase db, String id) {
		tLeaveMobileData dt = new tLeaveMobileData();
		db.delete(TABLE_CONTACTS, dt.Property_intLeaveIdSync + " = ?",
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
