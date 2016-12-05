package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mTypeLeaveMobileData;

public class mTypeLeaveMobileDA {
	public mTypeLeaveMobileDA(SQLiteDatabase db) {
		mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intTipeLeave + " INTEGER PRIMARY KEY," 
				+ dt.Property_txtTipeLeaveName + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTypeLeaveMobile;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveData(SQLiteDatabase db, mTypeLeaveMobileData data) {
		mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.PropertyAll + ") " + "values('"
				+ String.valueOf(data.get_intTipeLeave()) + "','"
				+ String.valueOf(data.get_txtTipeLeaveName()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllData(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mTypeLeaveMobileData getData(SQLiteDatabase db, String id) {
		mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intTipeLeave, dt.Property_txtTipeLeaveName},
				dt.Property_intTipeLeave + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mTypeLeaveMobileData contact = new mTypeLeaveMobileData();
		if (cursor.getCount() > 0) {
			contact.set_intTipeLeave(String.valueOf( cursor.getString(0)));
			contact.set_txtTipeLeaveName(cursor.getString(1));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	
	// Getting All Contacts
	public List<mTypeLeaveMobileData> getAllData(SQLiteDatabase db) {
		List<mTypeLeaveMobileData> contactList = new ArrayList<mTypeLeaveMobileData>();
		// Select All Query
		mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
		String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				mTypeLeaveMobileData contact = new mTypeLeaveMobileData();
				contact.set_intTipeLeave(String.valueOf( cursor.getString(0)));
				contact.set_txtTipeLeaveName(cursor.getString(1));
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
		mTypeLeaveMobileData dt = new mTypeLeaveMobileData();
		db.delete(TABLE_CONTACTS, dt.Property_intTipeLeave + " = ?",
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
