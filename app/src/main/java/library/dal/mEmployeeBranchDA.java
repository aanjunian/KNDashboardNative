package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mEmployeeBranchData;

public class mEmployeeBranchDA {

	public mEmployeeBranchDA(SQLiteDatabase db) {
		mEmployeeBranchData dt = new mEmployeeBranchData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intID
				+ " TEXT PRIMARY KEY," + dt.Property_txtBranchCode
				+ " TEXT NULL," + dt.Property_EmpId + " TEXT  NULL,"
				+ dt.Property_txtBranchName + " TEXT  NULL,"
				+ dt.Property_txtNIK + " TEXT  NULL,"
				+ dt.Property_txtName + " TEXT  NULL" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mEmployeeBranch;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mEmployeeBranchData data) {
		mEmployeeBranchData dt = new mEmployeeBranchData();
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intID 
				+ "," + dt.Property_EmpId 
				+ ","+ dt.Property_txtBranchCode 
				+ "," + dt.Property_txtBranchName 
				+ "," + dt.Property_txtName
				+ "," + dt.Property_txtNIK + ") " + "values('"
				+ String.valueOf(data.get_intID()) + "','"
				+ String.valueOf(data.get_EmpId()) + "','"
				+ String.valueOf(data.get_txtBranchCode()) + "','"
				+ String.valueOf(data.get_txtBranchName()) + "','"
				+ String.valueOf(data.get_txtName()) + "','"
				+ String.valueOf(data.get_txtNIK()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mEmployeeBranchData getData(SQLiteDatabase db, int id) {
		mEmployeeBranchData dt = new mEmployeeBranchData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intID, dt.Property_EmpId, dt.Property_txtBranchCode,
				dt.Property_txtBranchName, dt.Property_txtName , dt.Property_txtNIK },
				dt.Property_intID + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mEmployeeBranchData contact = new mEmployeeBranchData();
		if (cursor.getCount() > 0) {
			contact.set_intID(cursor.getString(0));
			contact.set_EmpId(cursor.getString(1));
			contact.set_txtBranchCode(cursor.getString(2));
			contact.set_txtBranchName(cursor.getString(3));
			contact.set_txtName(cursor.getString(4));
			contact.set_txtNIK(cursor.getString(5));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<mEmployeeBranchData> getAllData(SQLiteDatabase db) {
		List<mEmployeeBranchData> contactList = new ArrayList<mEmployeeBranchData>();
		// Select All Query
		mEmployeeBranchData dt = new mEmployeeBranchData();
		String selectQuery = "SELECT  " + dt.Property_ALL + " FROM "
				+ TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mEmployeeBranchData contact = new mEmployeeBranchData();
				contact.set_EmpId(cursor.getString(0));
				contact.set_intID(cursor.getString(1));
				contact.set_txtBranchCode(cursor.getString(2));
				contact.set_txtBranchName(cursor.getString(3));
				contact.set_txtName(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mEmployeeBranchData> getAllDataBranchByCabId(SQLiteDatabase db,String CabId) {
		List<mEmployeeBranchData> contactList = new ArrayList<mEmployeeBranchData>();
		// Select All Query
		mEmployeeBranchData dt = new mEmployeeBranchData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
				dt.Property_intID, dt.Property_EmpId, dt.Property_txtBranchCode,
				dt.Property_txtBranchName, dt.Property_txtName , dt.Property_txtNIK },
				dt.Property_txtBranchCode + "=?", new String[] { String.valueOf(CabId) },
				null, null, null, null);
		// looping through all rows and adding to list
		
		if (cursor.moveToFirst()) {
			do {
				mEmployeeBranchData contact = new mEmployeeBranchData();
				contact.set_EmpId(cursor.getString(0));
				contact.set_intID(cursor.getString(1));
				contact.set_txtBranchCode(cursor.getString(2));
				contact.set_txtBranchName(cursor.getString(3));
				contact.set_txtName(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
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
		mEmployeeBranchData dt = new mEmployeeBranchData();
		db.delete(TABLE_CONTACTS, dt.Property_intID + " = ?",
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
