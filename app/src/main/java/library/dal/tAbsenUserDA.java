package library.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tAbsenUserData;

public class tAbsenUserDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tAbsenUser;

	// Contacts Table Columns names

	public tAbsenUserDA(SQLiteDatabase db) {
		tAbsenUserData dt=new tAbsenUserData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_intId + " INTEGER PRIMARY KEY,"
				+ dt.Property_dtDateCheckIn + " TEXT NULL,"
				+ dt.Property_dtDateCheckOut + " TEXT NULL,"
				+ dt.Property_intSubmit + " TEXT NULL,"
				+ dt.Property_intSync + " TEXT NULL,"
				+ dt.Property_txtAbsen + " TEXT NULL,"
				+ dt.Property_txtAccuracy + " TEXT NULL,"
				+ dt.Property_txtBranchCode + " TEXT NULL,"
				+ dt.Property_txtBranchName + " TEXT NULL,"
				+ dt.Property_txtLatitude + " TEXT NULL,"
				+ dt.Property_txtLongitude + " TEXT NULL,"
				+ dt.Property_txtOutletCode + " TEXT NULL,"
				+ dt.Property_txtOutletName + " TEXT NULL,"
				+ dt.Property_txtDeviceId + " TEXT NULL,"
				+ dt.Property_txtImg1 + " BLOB NULL,"
				+ dt.Property_txtImg2 + " BLOB NULL,"
				+ dt.Property_txtUserId + " TEXT NULL" + ")";
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
	public void SaveDatatAbsenUserData(SQLiteDatabase db,tAbsenUserData data) {
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		data.get_txtImg1().compress(Bitmap.CompressFormat.JPEG, 100, out);
//		byte[] photo = out.toByteArray();
//		data.get_txtImg2().compress(Bitmap.CompressFormat.JPEG, 100, out);
//		byte[] photo2 = out.toByteArray();

		tAbsenUserData dt=new tAbsenUserData();
		ContentValues cv = new ContentValues();
		cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
		cv.put(dt.Property_dtDateCheckIn, String.valueOf(data.get_dtDateCheckIn()));
		cv.put(dt.Property_dtDateCheckOut, String.valueOf(data.get_dtDateCheckOut()));
		cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
		cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
		cv.put(dt.Property_txtAbsen, String.valueOf(data.get_txtAbsen()));
		cv.put(dt.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
		cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
		cv.put(dt.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
		cv.put(dt.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
		cv.put(dt.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
		cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
		cv.put(dt.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
		cv.put(dt.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
		cv.put(dt.Property_txtImg1, data.get_txtImg1());
		cv.put(dt.Property_txtImg2, data.get_txtImg2());
		cv.put(dt.Property_txtUserId, String.valueOf(data.get_txtUserId()));
		db.replace(TABLE_CONTACTS, null, cv);

//		db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intId+",'"
//				+dt.Property_dtDateCheckIn+"','"
//				+dt.Property_dtDateCheckOut+"','"
//				+dt.Property_intSubmit+"','"
//				+dt.Property_intSync+"','"
//				+dt.Property_txtAbsen+"','"
//				+dt.Property_txtAccuracy+"','"
//				+dt.Property_txtBranchCode+"','"
//				+dt.Property_txtBranchName+"','"
//				+dt.Property_txtLatitude+"','"
//				+dt.Property_txtLongitude+"','"
//				+dt.Property_txtOutletCode+"','"
//				+dt.Property_txtOutletName+"','"
//				+dt.Property_txtDeviceId+"','"
//				+dt.Property_txtImg1+"','"
//				+dt.Property_txtImg2+"','"
//				+dt.Property_txtUserId+"') "+
//				"values('"	+String.valueOf(data.get_intId())+"','"
//				+String.valueOf(data.get_dtDateCheckIn())+"','"
//				+String.valueOf(data.get_dtDateCheckOut())+"','"
//				+String.valueOf(data.get_intSubmit())+"','"
//				+String.valueOf(data.get_intSync())+"','"
//				+String.valueOf(data.get_txtAbsen())+"','"
//				+String.valueOf(data.get_txtAccuracy())+"','"
//				+String.valueOf(data.get_txtBranchCode())+"','"
//				+String.valueOf(data.get_txtBranchName())+"','"
//				+String.valueOf(data.get_txtLatitude())+"','"
//				+String.valueOf(data.get_txtLongitude())+"','"
//				+String.valueOf(data.get_txtOutletCode())+"','"
//				+String.valueOf(data.get_txtOutletName())+"','"
//				+String.valueOf(data.get_txtDeviceId())+"','"
//				+photo+"','"
//				+String.valueOf(data.get_txtImg2())+"','"
//				+String.valueOf(data.get_txtUserId())+"')");

	}
	public void SaveDataSubmit(SQLiteDatabase db,String IdData){
		tAbsenUserData dt=new tAbsenUserData();
		db.execSQL("UPDATE "+TABLE_CONTACTS+" SET "+dt.Property_intSubmit+"=1 WHERE "+dt.Property_intId+"="+IdData);
	}
	// Getting single contact
	public tAbsenUserData getData(SQLiteDatabase db,int id) {
		tAbsenUserData dt=new tAbsenUserData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
						dt.Property_dtDateCheckIn,dt.Property_intSubmit, dt.Property_intSync, dt.Property_txtAbsen
						,dt.Property_txtAccuracy
						,dt.Property_txtBranchCode
						,dt.Property_txtBranchName
						,dt.Property_txtLatitude
						,dt.Property_txtLongitude
						,dt.Property_txtOutletCode
						,dt.Property_txtOutletName
						,dt.Property_txtDeviceId
						,dt.Property_txtUserId
						,dt.Property_txtImg1
						,dt.Property_txtImg2
						,dt.Property_dtDateCheckOut}, dt.Property_intId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		tAbsenUserData contact = new tAbsenUserData();
		contact.set_intId(cursor.getString(0));
		contact.set_dtDateCheckIn(cursor.getString(1));
		contact.set_intSubmit(cursor.getString(2));
		contact.set_intSync(cursor.getString(3));
		contact.set_txtAbsen(cursor.getString(4));
		contact.set_txtAccuracy(cursor.getString(5));
		contact.set_txtBranchCode(cursor.getString(6));
		contact.set_txtBranchName(cursor.getString(7));
		contact.set_txtLatitude(cursor.getString(8));
		contact.set_txtLongitude(cursor.getString(9));
		contact.set_txtOutletCode(cursor.getString(10));
		contact.set_txtOutletName(cursor.getString(11));
		contact.set_txtDeviceId(cursor.getString(12));
		contact.set_txtUserId(cursor.getString(13));
//			byte[] blob1 = cursor.getBlob(Integer.parseInt(cursor.getString(14)));
//			Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
//			contact.set_txtImg1(String.valueOf(bmp1));
//			byte[] blob2 = cursor.getBlob(Integer.parseInt(cursor.getString(15)));
//			Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
//			contact.set_txtImg2(String.valueOf(bmp2));
//			contact.set_txtImg1(cursor.getString(14));
//			contact.set_txtImg2(cursor.getString(16));
//			contact.set_dtDateCheckOut(cursor.getString(16));
		// return contact
		cursor.close();
		return contact;
	}

	public tAbsenUserData getDataCheckInByOutletAndBranch(SQLiteDatabase db,String txtBranchCode, String txtOutletCode) {
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_dtDateCheckOut+" in ('null','') AND "+dt.Property_txtBranchCode+"='"+txtBranchCode+"' AND "+dt.Property_txtOutletCode+"='"+txtOutletCode+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		tAbsenUserData contact = null;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				contact=new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
//					contact.set_txtImg1(cursor.getString(15));
//					contact.set_txtImg2(cursor.getString(16));
				// Adding contact to list
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contact;
	}

	public tAbsenUserData getDataCheckInActive(SQLiteDatabase db) {
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_dtDateCheckOut+" in ('null','') ";

		Cursor cursor = db.rawQuery(selectQuery, null);
		tAbsenUserData contact = null;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				contact=new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
//				byte[] blob1 = cursor.getBlob(15);
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
				contact.set_txtImg1(cursor.getBlob(15));
//				byte[] blob2 = cursor.getBlob((16));
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
				contact.set_txtImg2(cursor.getBlob(16));
				// Adding contact to list
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contact;
	}

	// Getting All Contacts
	public List<tAbsenUserData> getAllData(SQLiteDatabase db) {
		List<tAbsenUserData> contactList = new ArrayList<tAbsenUserData>();
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tAbsenUserData contact = new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
//					byte[] blob1 = cursor.getBlob(Integer.parseInt(cursor.getString(15)));
//					Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
//					contact.set_txtImg1(String.valueOf(bmp1));
//					byte[] blob2 = cursor.getBlob(Integer.parseInt(cursor.getString(16)));
//					Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
//					contact.set_txtImg2(String.valueOf(bmp2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public List<tAbsenUserData> getAllDataToPushData(SQLiteDatabase db) {
		List<tAbsenUserData> contactList = null;
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync+"=0 And "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tAbsenUserData>();
			do {
				tAbsenUserData contact = new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
//					byte[] blob1 = cursor.getBlob(Integer.parseInt(cursor.getString(15)));
//					Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
//					contact.set_txtImg1(String.valueOf(bmp1));
//					byte[] blob2 = cursor.getBlob(Integer.parseInt(cursor.getString(16)));
//					Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
//					contact.set_txtImg2(String.valueOf(bmp2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	public List<tAbsenUserData> getAllDataActive(SQLiteDatabase db) {
		List<tAbsenUserData> contactList = null;
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE " + dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tAbsenUserData>();
			do {
				tAbsenUserData contact = new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
				byte[] blob1 = cursor.getBlob(15);
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
				contact.set_txtImg1(blob1);
				byte[] blob2 = cursor.getBlob(16);
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
				contact.set_txtImg2(blob2);
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public List<tAbsenUserData> getAllDataByOutletCode(SQLiteDatabase db,String OutletCode) {
		List<tAbsenUserData> contactList = null;
		// Select All Query
		tAbsenUserData dt=new tAbsenUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtOutletCode+"='"+OutletCode+"' AND "+dt.Property_intSubmit+"=1";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tAbsenUserData>();
			do {
				tAbsenUserData contact = new tAbsenUserData();
				contact.set_intId(cursor.getString(0));
				contact.set_dtDateCheckIn(cursor.getString(1));
				contact.set_intSubmit(cursor.getString(2));
				contact.set_intSync(cursor.getString(3));
				contact.set_txtAbsen(cursor.getString(4));
				contact.set_txtAccuracy(cursor.getString(5));
				contact.set_txtBranchCode(cursor.getString(6));
				contact.set_txtBranchName(cursor.getString(7));
				contact.set_txtLatitude(cursor.getString(8));
				contact.set_txtLongitude(cursor.getString(9));
				contact.set_txtOutletCode(cursor.getString(10));
				contact.set_txtOutletName(cursor.getString(11));
				contact.set_txtDeviceId(cursor.getString(12));
				contact.set_txtUserId(cursor.getString(13));
				contact.set_dtDateCheckOut(cursor.getString(14));
//					byte[] blob1 = cursor.getBlob(Integer.parseInt(cursor.getString(15)));
//					Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
//					contact.set_txtImg1(String.valueOf(bmp1));
//					byte[] blob2 = cursor.getBlob(Integer.parseInt(cursor.getString(16)));
//					Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
//					contact.set_txtImg2(String.valueOf(bmp2));
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
		tAbsenUserData dt = new tAbsenUserData();
		db.delete(TABLE_CONTACTS, dt.Property_txtAbsen + " = ?",
				new String[] { String.valueOf(id) });
	}

	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
	}
	// Getting contacts Count
	public int getContactsCountSubmit(SQLiteDatabase db) {
		tAbsenUserData dt = new tAbsenUserData();
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSubmit +" = 1";
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}
	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}
}
