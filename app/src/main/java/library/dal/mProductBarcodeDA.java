package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mProductBarcodeData;

public class mProductBarcodeDA {
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mProductBarcode;

	// Contacts Table Columns names

	public mProductBarcodeDA(SQLiteDatabase db) {
		mProductBarcodeData dt=new mProductBarcodeData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_txtProductCode + " TEXT PRIMARY KEY," 
				+ dt.Property_intSubmit + " TEXT NULL,"
				+ dt.Property_intSync + " TEXT NULL,"
				+ dt.Property_intProductCode + " TEXT NULL,"
				+ dt.Property_txtBarcode + " TEXT NULL,"
				+ dt.Property_txtProductName + " TEXT NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		// Create tables again
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDatamProductBarcodeData(SQLiteDatabase db,mProductBarcodeData data) {
		mProductBarcodeData dt=new mProductBarcodeData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.PropertyAll+") "+
			"values('"	+String.valueOf(data.get_intProductCode())+"','"
				+String.valueOf(data.get_intSubmit())+"','"
				+String.valueOf(data.get_intSync())+"','"
				+String.valueOf(data.get_txtBarcode())+"','"
				+String.valueOf(data.get_txtProductCode())+"','"
				+String.valueOf(data.get_txtProductName())+"')");
	}
	public void UpdateDataItem(SQLiteDatabase db,mProductBarcodeData data) {
		mProductBarcodeData dt=new mProductBarcodeData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("Update "+TABLE_CONTACTS+" set  "
				+dt.Property_txtBarcode+"="
				+String.valueOf(data.get_txtBarcode())
				+" Where " + dt.Property_intProductCode +"='"+ data.get_intProductCode()+"'");
	}
	
	public void UpdateDataItemForSubmit(SQLiteDatabase db,String dataid) {
		mProductBarcodeData dt=new mProductBarcodeData();
		db.execSQL("Update "+TABLE_CONTACTS+" set  "
				+dt.Property_intSubmit+"=1"
				+" Where " + dt.Property_intProductCode +"='"+ dataid+"'");
	}
	
	// Getting single contact
	public mProductBarcodeData getData(SQLiteDatabase db,String id) {
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intProductCode+"='"+id+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		mProductBarcodeData contact = null;
		if(cursor.getCount()>0){
			contact = new mProductBarcodeData();
			if (cursor != null)
				cursor.moveToFirst();
			contact.set_intProductCode(cursor.getString(0));
			contact.set_intSubmit(cursor.getString(1));
			contact.set_intSync(cursor.getString(2));
			contact.set_txtBarcode(cursor.getString(3));
			contact.set_txtProductCode(cursor.getString(4));
			contact.set_txtProductName(cursor.getString(5));
			
		}
		// return contact
		cursor.close();
		return contact;
	}
	
	public boolean CheckDataPushData(SQLiteDatabase db) {		
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT 1 FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1 AND "+dt.Property_intSync+"=0";
		Cursor cursor = db.rawQuery(selectQuery, null);
		boolean result=false;
		if(cursor.getCount()>0){
			result=true;
		}
		cursor.close();
		// return contact list
		return result;
	}
	
	// Getting All Contacts
	public List<mProductBarcodeData> getAllDataToPushData(SQLiteDatabase db) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1 AND "+dt.Property_intSync+"=0";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mProductBarcodeData> getAllData(SQLiteDatabase db) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();;
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mProductBarcodeData> getAllDataByBarcode(SQLiteDatabase db,String txtBarcode) {
		List<mProductBarcodeData> contactList = null;
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtBarcode+"='"+txtBarcode+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	
	public List<mProductBarcodeData> getAllDataByProductCode(SQLiteDatabase db,String txtProductCode) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String txtParam="";
		if(txtProductCode.equals("")){
			txtProductCode=dt.Property_txtProductCode;
		}else{
			txtProductCode="'"+txtProductCode+"%'";
		}
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtProductCode+" like "+txtProductCode+" AND "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mProductBarcodeData> getAllDataSubmit(SQLiteDatabase db) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();;
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mProductBarcodeData> getAllDataNotSync(SQLiteDatabase db) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();;
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync +"=0";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<mProductBarcodeData> getAllDataByProductName(SQLiteDatabase db,String txtProductName) {
		List<mProductBarcodeData> contactList = new ArrayList<mProductBarcodeData>();;
		// Select All Query
		mProductBarcodeData dt=new mProductBarcodeData();
		String selectQuery = "SELECT  "+dt.PropertyAll+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtProductName +" like '%"+txtProductName+"%'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<mProductBarcodeData>();
			do {
				mProductBarcodeData contact = new mProductBarcodeData();
				contact.set_intProductCode(cursor.getString(0));
				contact.set_intSubmit(cursor.getString(1));
				contact.set_intSync(cursor.getString(2));
				contact.set_txtBarcode(cursor.getString(3));
				contact.set_txtProductCode(cursor.getString(4));
				contact.set_txtProductName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	// Deleting single contact
	public void deleteContact(SQLiteDatabase db,String id) {		
		mProductBarcodeData dt = new mProductBarcodeData();
		db.delete(TABLE_CONTACTS, dt.Property_intProductCode + " = ?",
				new String[] { String.valueOf(id) });
	}
	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
	}
	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		// return count
		int index=cursor.getCount();
		cursor.close();
		return index;
	}
}
