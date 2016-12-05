package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mProductBrandHeaderData;

public class mProductBrandHeaderDA {
	public mProductBrandHeaderDA(SQLiteDatabase db) {
		mProductBrandHeaderData dt = new mProductBrandHeaderData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intmProductUmbrandId
				+ " TEXT PRIMARY KEY," + dt.Property_txtAliasName
				+ " TEXT NULL," + dt.Property_txtProductBrandCode + " TEXT  NULL,"
				+ dt.Property_txtProductBrandName + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mProductBrandHeaderData;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mProductBrandHeaderData data) {
		mProductBrandHeaderData dt = new mProductBrandHeaderData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intmProductUmbrandId
				+ "," + dt.Property_txtAliasName
				+ ","+ dt.Property_txtProductBrandCode 
				+ "," + dt.Property_txtProductBrandName
				+ ") " + "values('"
				+ String.valueOf(data.get_intmProductUmbrandId()) + "','"
				+ String.valueOf(data.get_txtAliasName()) + "','"
				+ String.valueOf(data.get_txtProductBrandCode()) + "','"
				+ String.valueOf(data.get_txtProductBrandName()) + "'"+ ")");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mProductBrandHeaderData getData(SQLiteDatabase db, String id) {
		mProductBrandHeaderData dt = new mProductBrandHeaderData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intmProductUmbrandId , dt.Property_txtAliasName 
				, dt.Property_txtProductBrandCode
				, dt.Property_txtProductBrandName
				},
				dt.Property_intmProductUmbrandId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mProductBrandHeaderData contact = new mProductBrandHeaderData();
		if (cursor.getCount() > 0) {
			contact.set_intmProductUmbrandId(cursor.getString(0));
			contact.set_txtAliasName(cursor.getString(1));
			contact.set_txtProductBrandCode(cursor.getString(2));
			contact.set_txtProductBrandName(cursor.getString(3));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<mProductBrandHeaderData> getAllData(SQLiteDatabase db) {
		List<mProductBrandHeaderData> contactList = new ArrayList<mProductBrandHeaderData>();
		// Select All Query
		mProductBrandHeaderData dt = new mProductBrandHeaderData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS +" ORDER BY "+dt.Property_txtProductBrandName+" ";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mProductBrandHeaderData contact = new mProductBrandHeaderData();
				contact.set_intmProductUmbrandId(cursor.getString(0));
				contact.set_txtAliasName(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
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
		mProductBrandHeaderData dt = new mProductBrandHeaderData();
		db.delete(TABLE_CONTACTS, dt.Property_intmProductUmbrandId + " = ?",
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
