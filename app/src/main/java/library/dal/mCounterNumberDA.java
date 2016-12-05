package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mCounterNumberData;

public class mCounterNumberDA {
	public mCounterNumberDA(SQLiteDatabase db) {
		mCounterNumberData dt = new mCounterNumberData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intId
				+ " INTEGER PRIMARY KEY," + dt.Property_txtDeskripsi
				+ " TEXT NULL," + dt.Property_txtName + " TEXT NULL,"
				+ dt.Property_txtValue + " TEXT NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	
	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mCountNumber;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mCounterNumberData data) {
		mCounterNumberData dt = new mCounterNumberData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intId + "," + dt.Property_txtDeskripsi + ","
				+ dt.Property_txtName + "," + dt.Property_txtValue+ ") " + "values("
				+ data.get_intId() + ",'"
				+ String.valueOf(data.get_txtDeskripsi()) + "','"
				+ String.valueOf(data.get_txtName()) + "','"
				+ String.valueOf(data.get_txtValue()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mCounterNumberData getData(SQLiteDatabase db, int id) {
		mCounterNumberData dt = new mCounterNumberData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intId, dt.Property_txtDeskripsi, dt.Property_txtName,
				dt.Property_txtValue},
				dt.Property_intId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		mCounterNumberData contact = null;
		if (cursor != null)
			cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			contact = new mCounterNumberData();
			contact = new mCounterNumberData(Integer.valueOf(cursor.getString(0)), String.valueOf(cursor.getString(1)), 
					String.valueOf(cursor.getString(2)), cursor.getString(3));
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	
	// Getting All Contacts
	public List<mCounterNumberData> getAllData(SQLiteDatabase db) {
		List<mCounterNumberData> contactList = new ArrayList<mCounterNumberData>();
		// Select All Query
		mCounterNumberData dt = new mCounterNumberData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS +" ORDER BY "+dt.Property_intId+" ASC";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				mCounterNumberData contact = new mCounterNumberData();
				contact.set_intId(Integer.valueOf(cursor.getString(0)));
				contact.set_txtDeskripsi(String.valueOf(cursor.getString(1)));
				contact.set_txtName(String.valueOf(cursor.getString(2)));
				contact.set_txtValue(cursor.getString(3));
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
		mCounterNumberData dt = new mCounterNumberData();
		db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
				new String[] { String.valueOf(id) });
		// db.close();
	}
	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
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
