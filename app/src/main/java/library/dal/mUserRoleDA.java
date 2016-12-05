package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mUserRoleData;

public class mUserRoleDA {
	public mUserRoleDA(SQLiteDatabase db) {
		mUserRoleData dt = new mUserRoleData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "("
				+ dt.Property_intId + " TEXT PRIMARY KEY,"
				+ dt.Property_intRoleId + " TEXT NULL,"
				+ dt.Property_txtUserId + " TEXT  NULL,"
				+ dt.Property_txtRoleName + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mUserRole;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mUserRoleData data) {
		mUserRoleData dt = new mUserRoleData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intId
				+ "," + dt.Property_intRoleId 
				+ ","+ dt.Property_txtRoleName 
				+ "," + dt.Property_txtUserId 
				+ ") " + "values('"
				+ String.valueOf(data.get_intId()) + "','"
				+ String.valueOf(data.get_intRoleId()) + "','"
				+ String.valueOf(data.get_txtRoleName()) + "','"
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
	public mUserRoleData getData(SQLiteDatabase db, String id) {
		mUserRoleData dt = new mUserRoleData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intId , dt.Property_intRoleId 
				, dt.Property_txtUserId
				, dt.Property_txtRoleName},
				dt.Property_intId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mUserRoleData contact = new mUserRoleData();
		if (cursor.getCount() > 0) {
			contact.set_intId(cursor.getString(0));
			contact.set_intRoleId(cursor.getString(1));
			contact.set_txtUserId(cursor.getString(2));
			contact.set_txtRoleName(cursor.getString(3));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<mUserRoleData> getAllData(SQLiteDatabase db) {
		List<mUserRoleData> contactList = new ArrayList<mUserRoleData>();
		// Select All Query
		mUserRoleData dt = new mUserRoleData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mUserRoleData contact = new mUserRoleData();
				contact.set_intId(cursor.getString(0));
				contact.set_intRoleId(cursor.getString(1));
				contact.set_txtUserId(cursor.getString(2));
				contact.set_txtRoleName(cursor.getString(3));
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
		mUserRoleData dt = new mUserRoleData();
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
