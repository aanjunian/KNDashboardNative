package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tSalesProductHeaderData;

public class tSalesProductHeaderDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesProductHeader;

	// Contacts Table Columns names

	public tSalesProductHeaderDA(SQLiteDatabase db) {
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_intId + " TEXT PRIMARY KEY," 
				+ dt.Property_txtDate + " TEXT NULL,"
				+ dt.Property_OutletCode + " TEXT NULL,"
				+ dt.Property_OutletName + " TEXT NULL,"
				+ dt.Property_txtKeterangan + " TEXT NULL,"
				+ dt.Property_intSumItem + " TEXT NULL,"
				+ dt.Property_intSumAmount + " TEXT NULL,"
				+ dt.Property_UserId + " TEXT NULL,"
				+ dt.Property_intSubmit + " TEXT NULL,"
				+ dt.Property_intSync + " TEXT NULL,"
				+ dt.Property_txtBranchCode + " TEXT NULL,"
				+ dt.Property_txtBranchName + " TEXT NULL,"
				+ dt.Property_intIdAbsenUser + " TEXT NULL,"
				+ dt.Property_txtNIK + " TEXT NULL" + ")";
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
	public void SaveDatatSalesProductHeaderData(SQLiteDatabase db,tSalesProductHeaderData data) {
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intId+","
				+dt.Property_OutletCode+","
				+dt.Property_OutletName+","
				+dt.Property_txtDate+","
				+dt.Property_txtKeterangan+","
				+dt.Property_intSumAmount+","
				+dt.Property_intSumItem+","
				+dt.Property_UserId+","
				+dt.Property_intSubmit+","
				+dt.Property_intSync+","
				+dt.Property_txtBranchCode+","
				+dt.Property_txtBranchName+","
				+dt.Property_intIdAbsenUser+","
				+dt.Property_txtNIK+") "+
			"values('"	+String.valueOf(data.get_intId())+"','"
				+String.valueOf(data.get_OutletCode())+"','"
				+String.valueOf(data.get_OutletName())+"','"
				+String.valueOf(data.get_dtDate())+"','"
				+String.valueOf(data.get_txtKeterangan())+"','"
				+String.valueOf(data.get_intSumAmount())+"','"
				+String.valueOf(data.get_intSumItem())+"','"
				+String.valueOf(data.get_UserId())+"','"
				+String.valueOf(data.get_intSubmit())+"','"
				+String.valueOf(data.get_intSync())+"','"
				+String.valueOf(data.get_txtBranchCode())+"','"
				+String.valueOf(data.get_txtBranchName())+"','"
				+String.valueOf(data.get_intIdAbsenUser())+"','"
				+String.valueOf(data.get_txtNIK())+"')");
	}
	public void UpdateDataItem(SQLiteDatabase db,tSalesProductHeaderData data) {
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("Update "+TABLE_CONTACTS+" set  "
				+dt.Property_intSumAmount+"="
				+String.valueOf(data.get_intSumAmount())
				+dt.Property_intSumItem+"="
				+String.valueOf(data.get_intSumItem())
				+" Where " + dt.Property_intId +"='"+ data.get_intId()+"'");
	}
	
	public void UpdateDataItemForSubmit(SQLiteDatabase db,String dataid) {
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		db.execSQL("Update "+TABLE_CONTACTS+" set  "
				+dt.Property_intSubmit+"=1"
				+" Where " + dt.Property_intId +"='"+ dataid+"'");
	}
	
	// Getting single contact
	public tSalesProductHeaderData getData(SQLiteDatabase db,String id) {
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
				dt.Property_OutletCode,dt.Property_OutletName, 
				dt.Property_txtDate,dt.Property_txtKeterangan ,dt.Property_txtNIK
				,dt.Property_intSumAmount,dt.Property_intSumItem,dt.Property_UserId, dt.Property_intSubmit
				,dt.Property_intSync , dt.Property_txtBranchCode , dt.Property_txtBranchName,dt.Property_intIdAbsenUser}, dt.Property_intId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		tSalesProductHeaderData contact = null;
		if(cursor.getCount()>0){
			contact = new tSalesProductHeaderData();
			if (cursor != null)
				cursor.moveToFirst();
			contact.set_intId(cursor.getString(0));
			contact.set_OutletCode(cursor.getString(1));
			contact.set_OutletName(cursor.getString(2));
			contact.set_dtDate(cursor.getString(3));
			contact.set_txtKeterangan(cursor.getString(4));
			contact.set_txtNIK(cursor.getString(5));
			contact.set_intSumAmount(cursor.getString(6));
			contact.set_intSumItem(cursor.getString(7));
			contact.set_UserId(cursor.getString(8));
			contact.set_intSubmit(cursor.getString(9));
			contact.set_intSync(cursor.getString(10));
			contact.set_txtBranchCode(cursor.getString(11));
			contact.set_txtBranchName(cursor.getString(12));
			contact.set_intIdAbsenUser(cursor.getString(13));
		}
		// return contact
		cursor.close();
		return contact;
	}
	
	public boolean CheckDataPushData(SQLiteDatabase db) {		
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
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
	public List<tSalesProductHeaderData> getAllDataToPushData(SQLiteDatabase db) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1 AND "+dt.Property_intSync+"=0";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<tSalesProductHeaderData> getAllData(SQLiteDatabase db) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}


	public List<tSalesProductHeaderData> getAllDataByOulet(SQLiteDatabase db,String txtOutletCode) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_OutletCode+"='"+txtOutletCode+"' AND "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<tSalesProductHeaderData> getAllDataSubmit(SQLiteDatabase db) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<tSalesProductHeaderData> getAllDataNotSync(SQLiteDatabase db) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync +"=0";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public List<tSalesProductHeaderData> getAllDataByIdAbsen(SQLiteDatabase db,String Id) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intIdAbsenUser +"='"+Id+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	//get data by int sync
	public List<tSalesProductHeaderData> getAllDataByIntSyc(SQLiteDatabase db,String int_sync) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync +"='"+int_sync+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
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
		tSalesProductHeaderData dt = new tSalesProductHeaderData();
		db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
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

	public List<tSalesProductHeaderData> getLastData(SQLiteDatabase db) {
		List<tSalesProductHeaderData> contactList = null;
		// Select All Query
		tSalesProductHeaderData dt=new tSalesProductHeaderData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToLast()) {
			contactList=new ArrayList<tSalesProductHeaderData>();
			do {
				tSalesProductHeaderData contact = new tSalesProductHeaderData();
				contact.set_intId(cursor.getString(0));
				contact.set_OutletCode(cursor.getString(1));
				contact.set_OutletName(cursor.getString(2));
				contact.set_dtDate(cursor.getString(3));
				contact.set_txtKeterangan(cursor.getString(4));
				contact.set_txtNIK(cursor.getString(5));
				contact.set_intSumAmount(cursor.getString(6));
				contact.set_intSumItem(cursor.getString(7));
				contact.set_UserId(cursor.getString(8));
				contact.set_intSubmit(cursor.getString(9));
				contact.set_intSync(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtBranchName(cursor.getString(12));
				contact.set_intIdAbsenUser(cursor.getString(13));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
}
