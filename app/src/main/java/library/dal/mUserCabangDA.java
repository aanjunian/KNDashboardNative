package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mUserCabangData;

public class mUserCabangDA {
	public mUserCabangDA(SQLiteDatabase db) {
		mUserCabangData dt = new mUserCabangData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intId
				+ " TEXT PRIMARY KEY," + dt.Property_txtCabangId
				+ " TEXT NULL," + dt.Property_txtCabangName 
				+ " TEXT  NULL,"  + dt.Property_intRoleId + " TEXT  NULL,"  
				+ dt.Property_txtNik + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mUserCabang;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mUserCabangData data) {
		mUserCabangData dt = new mUserCabangData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intId
				+ "," + dt.Property_txtCabangId 
				+ ","+ dt.Property_txtCabangName 
				+ "," + dt.Property_txtNik
				+ "," + dt.Property_intRoleId
				+ ") " + "values('"
				+ String.valueOf(data.get_intId()) + "','"
				+ String.valueOf(data.get_txtCabangId()) + "','"
				+ String.valueOf(data.get_txtCabangName()) + "','"
				+ String.valueOf(data.get_txtNik()) 
				+ String.valueOf(data.get_intRoleId())
				+ "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mUserCabangData getData(SQLiteDatabase db, String id) {
		mUserCabangData dt = new mUserCabangData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intId , dt.Property_txtCabangId 
				, dt.Property_txtCabangName 
				, dt.Property_txtNik, dt.Property_intRoleId},
				dt.Property_intId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mUserCabangData contact = new mUserCabangData();
		if (cursor.getCount() > 0) {
			contact.set_intId(cursor.getString(0));
			contact.set_txtCabangId(cursor.getString(1));
			contact.set_txtCabangName(cursor.getString(2));
			contact.set_txtNik(cursor.getString(3));
			contact.set_intRoleId(cursor.getString(4));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<mUserCabangData> getAllData(SQLiteDatabase db) {
		List<mUserCabangData> contactList = new ArrayList<mUserCabangData>();
		// Select All Query
		mUserCabangData dt = new mUserCabangData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mUserCabangData contact = new mUserCabangData();
				contact.set_intId(cursor.getString(0));
				contact.set_txtCabangId(cursor.getString(1));
				contact.set_txtCabangName(cursor.getString(2));
				contact.set_txtNik(cursor.getString(3));
				contact.set_intRoleId(cursor.getString(4));
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
		mUserCabangData dt = new mUserCabangData();
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
