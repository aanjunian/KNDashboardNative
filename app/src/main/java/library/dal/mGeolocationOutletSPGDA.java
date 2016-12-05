package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mGeolocationOutletSPGData;

public class mGeolocationOutletSPGDA {
	public mGeolocationOutletSPGDA(SQLiteDatabase db) {
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_IntId
				+ " TEXT PRIMARY KEY," + dt.Property_txtAcc
				+ " TEXT NULL," + dt.Property_txtBranchCode + " TEXT  NULL,"
				+ dt.Property_txtLatitude + " TEXT  NULL,"
				+ dt.Property_txtLongitude + " TEXT  NULL,"
				+ dt.Property_txtOutletCode + " TEXT  NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mGeolocationOutletSPG;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mGeolocationOutletSPGData data) {
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_IntId
				+ "," + dt.Property_txtAcc 
				+ ","+ dt.Property_txtBranchCode 
				+ "," + dt.Property_txtLatitude 
				+ "," + dt.Property_txtLongitude
				+ "," + dt.Property_txtOutletCode
				+ ") " + "values('"
				+ String.valueOf(data.get_intId()) + "','"
				+ String.valueOf(data.get_txtAcc()) + "','"
				+ String.valueOf(data.get_txtBranchCode()) + "','"
				+ String.valueOf(data.get_txtLatitude()) + "','"
				+ String.valueOf(data.get_txtLongitude()) + "','"
				+ String.valueOf(data.get_txtOutletCode()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mGeolocationOutletSPGData getData(SQLiteDatabase db, String id) {
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_IntId  
				, dt.Property_txtAcc
				, dt.Property_txtBranchCode 
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtOutletCode},
				dt.Property_IntId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mGeolocationOutletSPGData contact = new mGeolocationOutletSPGData();
		if (cursor.getCount() > 0) {
			contact.set_intId(cursor.getString(0));
			contact.set_txtAcc(cursor.getString(1));
			contact.set_txtBranchCode(cursor.getString(2));
			contact.set_txtLatitude(cursor.getString(3));
			contact.set_txtLongitude(cursor.getString(4));
			contact.set_txtOutletCode(cursor.getString(5));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public List<mGeolocationOutletSPGData> getDataAreaByCabId(SQLiteDatabase db, String Cabid) {
		List<mGeolocationOutletSPGData> contactList = new ArrayList<mGeolocationOutletSPGData>();
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_IntId  
				, dt.Property_txtAcc
				, dt.Property_txtBranchCode 
				, dt.Property_txtLatitude
				, dt.Property_txtLongitude
				, dt.Property_txtOutletCode},
				dt.Property_txtBranchCode + "=?", new String[] { String.valueOf(Cabid) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mGeolocationOutletSPGData contact = new mGeolocationOutletSPGData();
		if (cursor.moveToFirst()) {
			do {
				contact = new mGeolocationOutletSPGData();
				contact.set_intId(cursor.getString(0));
				contact.set_txtAcc(cursor.getString(1));
				contact.set_txtBranchCode(cursor.getString(2));
				contact.set_txtLatitude(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtOutletCode(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	// Getting All Contacts
	public List<mGeolocationOutletSPGData> SearchData(SQLiteDatabase db,String txtBranchCode,String txtOutletCode) {
		List<mGeolocationOutletSPGData> contactList = new ArrayList<mGeolocationOutletSPGData>();
		// Select All Query
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS +" Where "+dt.Property_txtBranchCode +"= '"+txtBranchCode+"' and "+dt.Property_txtOutletCode+"='"+dt.Property_txtOutletCode+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mGeolocationOutletSPGData contact = new mGeolocationOutletSPGData();
				contact.set_intId(cursor.getString(0));
				contact.set_txtAcc(cursor.getString(1));
				contact.set_txtBranchCode(cursor.getString(2));
				contact.set_txtLatitude(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtOutletCode(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mGeolocationOutletSPGData> getAllData(SQLiteDatabase db) {
		List<mGeolocationOutletSPGData> contactList = new ArrayList<mGeolocationOutletSPGData>();
		// Select All Query
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mGeolocationOutletSPGData contact = new mGeolocationOutletSPGData();
				contact.set_intId(cursor.getString(0));
				contact.set_txtAcc(cursor.getString(1));
				contact.set_txtBranchCode(cursor.getString(2));
				contact.set_txtLatitude(cursor.getString(3));
				contact.set_txtLongitude(cursor.getString(4));
				contact.set_txtOutletCode(cursor.getString(5));
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
		mGeolocationOutletSPGData dt = new mGeolocationOutletSPGData();
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
