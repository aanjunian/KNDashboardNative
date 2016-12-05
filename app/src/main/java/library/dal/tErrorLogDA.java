package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tErrorLogData;

public class tErrorLogDA {
	public tErrorLogDA(SQLiteDatabase db) {
		tErrorLogData dt = new tErrorLogData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intId
				+ " INTEGER PRIMARY KEY," + dt.Property_dtDate
				+ " TEXT NULL," + dt.Property_UserId + " TEXT  NULL,"
				+ dt.Property_txtDeviceId + " TEXT  NULL,"
				+ dt.Property_Warning + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tErrorLog;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, tErrorLogData data) {
		tErrorLogData dt = new tErrorLogData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intId 
				+ "," + dt.Property_dtDate 
				+ ","+ dt.Property_UserId 
				+ "," + dt.Property_Warning
				+ "," + dt.Property_txtDeviceId 
				+ ") " + "values('"
				+ String.valueOf(data.get_intId()) + "','"
				+ String.valueOf(data.get_dtDate()) + "','"
				+ String.valueOf(data.get_UserId()) + "','"
				+ String.valueOf(data.get_Warning()) + "','"
				+ String.valueOf(data.get_txtDeviceId()) +"'"
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
	public tErrorLogData getData(SQLiteDatabase db, int id) {
		tErrorLogData dt = new tErrorLogData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intId, dt.Property_dtDate, dt.Property_UserId,
				dt.Property_Warning,dt.Property_txtDeviceId },
				dt.Property_intId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		tErrorLogData contact = new tErrorLogData();
		if (cursor.getCount() > 0) {
			contact.set_intId(Integer.valueOf( cursor.getString(0)));
			contact.set_dtDate(cursor.getString(1));
			contact.set_UserId(cursor.getString(2));
			contact.set_Warning(cursor.getString(3));
			contact.set_txtDeviceId(cursor.getString(4));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<tErrorLogData> getAllData(SQLiteDatabase db) {
		List<tErrorLogData> contactList = new ArrayList<tErrorLogData>();
		// Select All Query
		tErrorLogData dt = new tErrorLogData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				tErrorLogData contact = new tErrorLogData();
				contact.set_intId(Integer.valueOf(cursor.getString(0)));
				contact.set_dtDate(cursor.getString(1));
				contact.set_UserId(cursor.getString(2));
				contact.set_Warning(cursor.getString(3));
				contact.set_txtDeviceId(cursor.getString(4));
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
		tErrorLogData dt = new tErrorLogData();
		db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
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
