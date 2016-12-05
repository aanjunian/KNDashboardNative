package library.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import library.common.tUserLoginData;


public class tUserLoginDA {

	// All Static variables
	// Database Version
	
	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tUserLogin;

	// Contacts Table Columns names

	public tUserLoginDA(SQLiteDatabase db) {
		tUserLoginData dt=new tUserLoginData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_intId + " INTEGER PRIMARY KEY,"
				+ dt.Property_txtUserId + " TEXT NULL,"
				+ dt.Property_txtRoleId + " TEXT NULL,"
				+ dt.Property_txtRoleName + " TEXT NULL,"
				+ dt.Property_txtUserName + " TEXT NULL,"
				+ dt.Property_DtLastLogin + " TEXT NULL,"
                + dt.Property_txtCabang + " TEXT NULL,"
                + dt.Property_txtEmail + " TEXT NULL,"
                + dt.Property_txtEmpId + " TEXT NULL,"
                + dt.Property_txtRegion + " TEXT NULL,"
				+ dt.Property_txtCabangIdMenu + " TEXT NULL,"
				+ dt.Property_txtCabangMenu + " TEXT NULL,"
				+ dt.Property_txtRegionMenu + " TEXT NULL"
				+ ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
	}
	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
	}
	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDatatUserLoginData(SQLiteDatabase db,tUserLoginData data) {
		tUserLoginData dt=new tUserLoginData();
		String strVal=null;
		if (data.get_intId()!=0){
			strVal=String.valueOf( data.get_intId());
		}

        String query = "INSERT OR REPLACE into "+TABLE_CONTACTS+" ("
				+dt.Property_intId+","
                +dt.Property_txtUserId+","
                +dt.Property_txtRoleId+","
                +dt.Property_txtRoleName+","
                +dt.Property_txtUserName+","
                +dt.Property_DtLastLogin+","
                +dt.Property_txtCabang+","
                +dt.Property_txtEmail+","
                +dt.Property_txtEmpId+","
				+dt.Property_txtRegion+","
				+dt.Property_txtCabangIdMenu+","
				+dt.Property_txtCabangMenu+","
				+dt.Property_txtRegionMenu+") "+
                "values("	+ strVal +",'"
                +String.valueOf(data.get_txtUserId())+"','"
                +String.valueOf(data.get_txtRoleId())+"','"
                +String.valueOf(data.get_txtRoleName())+"','"
                +String.valueOf(data.get_txtUserName())+"','"
                +String.valueOf(data.get_dtLastLogin())+"','"
                +String.valueOf(data.get_txtCabang())+"','"
                +String.valueOf(data.get_txtEmail())+"','"
                +String.valueOf(data.get_txtEmpId())+"','"
				+String.valueOf(data.get_txtRegion())+"','"
				+String.valueOf(data.get_txtCabangIdMenu())+"','"
				+String.valueOf(data.get_txtCabangMenu())+"','"
				+String.valueOf(data.get_txtRegionMenu())+"')";

		db.execSQL(query);
	}

	// Getting single contact
	public tUserLoginData getData(SQLiteDatabase db,int id) {

		tUserLoginData dt=new tUserLoginData();
		tUserLoginData contact=null;
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
						dt.Property_txtUserId, dt.Property_txtRoleId, dt.Property_txtRoleName,dt.Property_txtUserName
						,dt.Property_DtLastLogin,dt.Property_txtRegion,dt.Property_txtEmail,dt.Property_txtEmpId,dt.Property_txtRegion,dt.Property_txtCabangIdMenu,dt.Property_txtCabangMenu, dt.Property_txtRegionMenu}, dt.Property_intId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		if(cursor.getCount()>0){
			contact = new tUserLoginData(Integer.parseInt(cursor.getString(0)),
					(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
					cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11), cursor.getString(12));
			 return contact;
		}
		cursor.close();
		return contact;
	}

	public tUserLoginData CheckDataLogin(SQLiteDatabase db,String DateLogin) {

		tUserLoginData dt=new tUserLoginData();
//		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
//						dt.Property_txtUserId, dt.Property_txtRoleId, dt.Property_txtRoleName, dt.Property_txtPassword,dt.Property_txtUserName,
//						dt.Property_txtName, dt.Property_txtPathImage, dt.Property_TxtEmail, dt.Property_TxtEmpId,dt.Property_DtLastLogin,
//						dt.Property_TxtDeviceId,dt.Property_DtCheckIn,dt.Property_DtCheckOut,dt.Property_DtLogOut,dt.Property_TxtCab,dt.Property_txtDataId, dt.Property_txtOutletCode, dt.Property_txtOutletName, dt.Property_txtBranchCode}, dt.Property_DtLastLogin + "=?",
//				new String[] { String.valueOf(DateLogin) }, null, null, null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		tUserLoginData contact = new tUserLoginData(Integer.parseInt(cursor.getString(0)),
//				(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
//				cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20));
//		// return contact
//		cursor.close();

		tUserLoginData contact = null;
		return contact;
	}

	// Getting All Contacts
	public List<tUserLoginData> getAllData(SQLiteDatabase db) {
		List<tUserLoginData> contactList = null;
		// Select All Query
		tUserLoginData dt=new tUserLoginData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList = new ArrayList<tUserLoginData>();
			do {
				tUserLoginData contact = new tUserLoginData();
				contact.set_intId(Integer.parseInt(cursor.getString(0)));
				contact.set_txtUserId((cursor.getString(1)));
				contact.set_txtRoleId(cursor.getString(2));
				contact.set_txtRoleName(cursor.getString(3));
				contact.set_txtUserName(cursor.getString(5));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	public boolean CheckLoginNow(SQLiteDatabase db) throws ParseException {
		// Select All Query
		tUserLoginData dt=new tUserLoginData();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		String selectQuery = "SELECT  strftime('%Y-%m-%d',"+dt.Property_DtLastLogin+") as "+dt.Property_DtLastLogin+" FROM " + TABLE_CONTACTS +"  LIMIT 1";

		Cursor cursor = db.rawQuery(selectQuery, null);
		boolean result=false;
		if (cursor.moveToFirst()) {
			do {
				String datetime = cursor.getString(0);
				if(datetime!=null){
					if(dateFormat.format(cal.getTime()).compareTo(datetime)==0){
						result=true;
						break;
					}
				}
			} while (cursor.moveToNext());
		}


		cursor.close();
		// return contact list
		return result;
	}
	public List<tUserLoginData> getUserLoginNow(SQLiteDatabase db) {
		List<tUserLoginData> contactList = new ArrayList<tUserLoginData>();
		// Select All Query
		tUserLoginData dt=new tUserLoginData();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where date("+dt.Property_DtLastLogin+") =date('"+dateFormat.format(cal.getTime())+" 00:00:00')";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tUserLoginData contact = new tUserLoginData();
				contact.set_intId(Integer.parseInt(cursor.getString(0)));
				contact.set_txtUserId((cursor.getString(1)));
				contact.set_txtRoleId(cursor.getString(2));
				contact.set_txtRoleName(cursor.getString(3));
				contact.set_txtUserName(cursor.getString(4));
				contact.set_dtLastLogin(cursor.getString(5));
				contact.set_txtCabang(cursor.getString(6));
				contact.set_txtEmail(cursor.getString(7));
				contact.set_txtEmpId(cursor.getString(8));
				contact.set_txtRegion(cursor.getString(9));
				contact.set_txtCabangIdMenu(cursor.getString(10));
				contact.set_txtCabangMenu(cursor.getString(11));
				contact.set_txtRegionMenu(cursor.getString(12));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}
	
	// Updating single contact
	/*
	public int updateContact(SQLiteDatabase db,tUserLoginData contact) {
		tUserLoginData dt = new tUserLoginData();
		ContentValues values = new ContentValues();
		values.put(dt.Property_txtUserId, contact.get_txtUserId());
		values.put(dt.Property_txtRoleId, contact.get_txtRoleId());
		values.put(dt.Property_txtRoleName, contact.get_txtRoleName());
		values.put(dt.Property_txtPassword, contact.get_txtPassword());
		values.put(dt.Property_txtPathImage, contact.get_txtPathImage());
		// updating row
		return db.update(TABLE_CONTACTS, values, dt.Property_intId + " = ?",
				new String[] { String.valueOf(contact.get_intId()) });
	}
	*/

	public int updateDataById(SQLiteDatabase db, tUserLoginData data, int id) {
		tUserLoginData dt = new tUserLoginData();

		ContentValues values = new ContentValues();
		values.put(dt.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
		values.put(dt.Property_txtRoleName, String.valueOf(data.get_txtRoleName()));
		values.put(dt.Property_txtCabangIdMenu, String.valueOf(data.get_txtCabangIdMenu()));
		values.put(dt.Property_txtCabangMenu, String.valueOf(data.get_txtCabangMenu()));
		values.put(dt.Property_txtRegionMenu, String.valueOf(data.get_txtRegionMenu()));


		// updating row
		return db.update(TABLE_CONTACTS, values, dt.Property_intId + " = ? ",
				new String[] { String.valueOf(id) });
	}

	// Deleting single contact
	public void deleteContact(SQLiteDatabase db,int id) {
		
		tUserLoginData dt = new tUserLoginData();
		db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	
	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
		
		Cursor cursor = db.rawQuery(countQuery, null);
		// return count
		return cursor.getCount();
	}

}