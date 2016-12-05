package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mNotificationData;

public class mNotificationDA {

	public mNotificationDA(SQLiteDatabase db) {
		mNotificationData dt = new mNotificationData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_txtDataId
				+ " TEXT PRIMARY KEY," + dt.Property_dtPublishEnd
				+ " TEXT NULL," + dt.Property_dtPublishStart + " TEXT  NULL,"
				+ dt.Property_dtStatus + " TEXT  NULL,"
				+ dt.Property_intSync + " TEXT  NULL,"
				+ dt.Property_txtDescription + " TEXT  NULL,"
				+ dt.Property_txtImage + " TEXT  NULL,"
				+ dt.Property_txtNotifId + " TEXT  NULL,"
				+ dt.Property_txtStatus + " TEXT  NULL,"
				+ dt.Property_txtTitle + " TEXT  NULL,"
				+ dt.Property_intSuccessDLFile + " TEXT  NULL,"
				+ dt.Property_dtUpdated + " TEXT  NULL,"
				+ dt.Property_txtLinkImage + " TEXT  NULL,"
				+ dt.Property_txtUserID + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mNotification;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mNotificationData data) {
		mNotificationData dt = new mNotificationData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_txtDataId
				+ "," + dt.Property_dtPublishEnd 
				+ ","+ dt.Property_dtPublishStart 
				+ "," + dt.Property_dtStatus 
				+ "," + dt.Property_intSync
				+ "," + dt.Property_txtDescription
				+ "," + dt.Property_txtImage
				+ "," + dt.Property_txtNotifId
				+ "," + dt.Property_txtStatus
				+ "," + dt.Property_txtTitle
				+ "," + dt.Property_intSuccessDLFile
				+ "," + dt.Property_txtLinkImage
				+ "," + dt.Property_dtUpdated
				+ "," + dt.Property_txtUserID
				+ ") " + "values('"
				+ String.valueOf(data.get_txtDataId()) + "','"
				+ String.valueOf(data.get_dtPublishEnd()) + "','"
				+ String.valueOf(data.get_dtPublishStart()) + "','"
				+ String.valueOf(data.get_dtStatus()) + "','"
				+ String.valueOf(data.get_intSync()) + "','"
				+ String.valueOf(data.get_txtDescription()) + "','"
				+ String.valueOf(data.get_txtImage()) + "','"
				+ String.valueOf(data.get_txtNotifId()) + "','"
				+ String.valueOf(data.get_txtStatus()) + "','"
				+ String.valueOf(data.get_txtTitle()) + "','"
				+ String.valueOf(data.get_intSuccessDLFile()) + "','"
				+ String.valueOf(data.get_txtLinkImage()) + "','"
				+ String.valueOf(data.get_dtUpdated()) + "','"
				+ String.valueOf(data.get_txtUserID()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mNotificationData getData(SQLiteDatabase db, String id) {
		mNotificationData dt = new mNotificationData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_txtDataId , dt.Property_dtPublishEnd 
				, dt.Property_dtPublishStart 
				, dt.Property_dtStatus
				, dt.Property_intSync
				, dt.Property_txtDescription
				, dt.Property_txtImage
				, dt.Property_txtNotifId
				, dt.Property_txtStatus
				, dt.Property_txtTitle
				, dt.Property_txtUserID
				, dt.Property_txtLinkImage
				, dt.Property_intSuccessDLFile
				},
				dt.Property_txtNotifId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mNotificationData contact = new mNotificationData();
		if (cursor.getCount() > 0) {
			contact.set_txtDataId(cursor.getString(0));
			contact.set_dtPublishEnd(cursor.getString(1));
			contact.set_dtPublishStart(cursor.getString(2));
			contact.set_dtStatus(cursor.getString(3));
			contact.set_intSync(cursor.getString(4));
			contact.set_txtDescription(cursor.getString(5));
			contact.set_txtImage(cursor.getString(6));
			contact.set_txtNotifId(cursor.getString(7));
			contact.set_txtStatus(cursor.getString(8));
			contact.set_txtTitle(cursor.getString(9));
			contact.set_txtUserID(cursor.getString(10));
			contact.set_txtLinkImage(cursor.getString(11));
			contact.set_intSuccessDLFile(cursor.getString(12));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<mNotificationData> getAllData(SQLiteDatabase db) {
		List<mNotificationData> contactList = new ArrayList<mNotificationData>();
		// Select All Query
		mNotificationData dt = new mNotificationData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mNotificationData contact = new mNotificationData();
				contact.set_txtDataId(cursor.getString(0));
				contact.set_dtPublishEnd(cursor.getString(1));
				contact.set_dtPublishStart(cursor.getString(2));
				contact.set_dtStatus(cursor.getString(3));
				contact.set_intSync(cursor.getString(4));
				contact.set_txtDescription(cursor.getString(5));
				contact.set_txtImage(cursor.getString(6));
				contact.set_txtNotifId(cursor.getString(7));
				contact.set_txtStatus(cursor.getString(8));
				contact.set_txtTitle(cursor.getString(9));
				contact.set_txtUserID(cursor.getString(10));
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
		mNotificationData dt = new mNotificationData();
		db.delete(TABLE_CONTACTS, dt.Property_txtDataId + " = ?",
				new String[] { String.valueOf(id) });
		// db.close();
	}

	// Getting contacts Count
	public boolean UpdateDataStatusRead(SQLiteDatabase db,mNotificationData data) {
		String countQuery = "UPDATE " + TABLE_CONTACTS +" SET "+data.Property_intSync+"='0',"+data.Property_txtStatus+"='"+data.get_txtStatus() +"',"+
				data.Property_dtUpdated+"='"+data.get_dtUpdated()+"' WHERE "+data.Property_txtNotifId+"='"+data.get_txtNotifId()+"'";
		db.rawQuery(countQuery, null);
		// return count
		return true;
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
