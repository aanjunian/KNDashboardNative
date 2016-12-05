package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mMenuData;

public class mMenuDA {
	public mMenuDA(SQLiteDatabase db) {
		mMenuData dt = new mMenuData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_CONTACTS + "(" + dt.Property_intId
				+ " INTEGER PRIMARY KEY," + dt.Property_IntOrder
				+ " INTEGER NOT NULL," + dt.Property_IntParentID + " INTEGER NOT NULL,"
				+ dt.Property_TxtDescription + " TEXT NOT NULL,"
				+ dt.Property_TxtLink + " TEXT NOT NULL,"
				+ dt.Property_API_IntMenuID + " TEXT NOT NULL,"
				+ dt.Property_intVisible + " TEXT NOT NULL,"
				+ dt.Property_TxtIcon + " TEXT NOT NULL,"
				+ dt.Property_TxtMenuName+ " TEXT NOT NULL" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}


	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mMenu;

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDataMConfig(SQLiteDatabase db, mMenuData data) {
		mMenuData dt = new mMenuData();
		String valId =null;
		if(data.get_intId()!=0){
			valId=String.valueOf(data.get_intId());
		}
		db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
				+ dt.Property_intId + "," + dt.Property_IntOrder + ","
				+ dt.Property_IntParentID + "," + dt.Property_TxtDescription+ "," + dt.Property_TxtLink
				+ "," + dt.Property_intVisible + "," + dt.Property_API_IntMenuID
				+ "," + dt.Property_TxtIcon + "," + dt.Property_TxtMenuName + ") " + "values("
				+ valId + ","
				+ String.valueOf(data.get_IntOrder()) + ","
				+ String.valueOf(data.get_IntParentID()) + ",'"
				+ String.valueOf(data.get_TxtDescription()) + "','"
				+ String.valueOf(data.get_TxtLink()) + "','"
				+ String.valueOf(data.get_intVisible()) + "','"
				+ String.valueOf(data.get_IntMenuID()) + "','"
				+ String.valueOf(data.get_TxtIcon()) + "','"
				+ String.valueOf(data.get_TxtMenuName()) + "')");
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	public void DeleteAllDataMConfig(SQLiteDatabase db) {
		db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		// db.insert(TABLE_CONTACTS, null, values);
		// db.close(); // Closing database connection
	}
	// Getting single contact
	public mMenuData getDataByName(SQLiteDatabase db, String Name) {
		mMenuData dt = new mMenuData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
						dt.Property_intId, dt.Property_IntOrder, dt.Property_IntParentID,
						dt.Property_TxtDescription, dt.Property_TxtLink, dt.Property_TxtMenuName, dt.Property_intVisible,dt.Property_API_IntMenuID, dt.Property_TxtIcon },
				dt.Property_TxtDescription + "=?", new String[] { String.valueOf(Name) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mMenuData contact = new mMenuData();
		if (cursor.getCount() > 0) {
			contact = new mMenuData(Long.valueOf(cursor.getString(0)), Long.valueOf(cursor.getString(1)),
					Long.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getString(7));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public mMenuData getData(SQLiteDatabase db, int id) {
		mMenuData dt = new mMenuData();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
						dt.Property_intId, dt.Property_IntOrder, dt.Property_IntParentID,
						dt.Property_TxtDescription, dt.Property_TxtLink, dt.Property_TxtMenuName, dt.Property_intVisible,dt.Property_API_IntMenuID },
				dt.Property_intId + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mMenuData contact = new mMenuData();
		if (cursor.getCount() > 0) {
			contact = new mMenuData(Long.valueOf(cursor.getString(0)), Long.valueOf(cursor.getString(1)),
					Long.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}
	public mMenuData getDataByNamaMenu(SQLiteDatabase db, String txtnmMenu) {
		mMenuData dt = new mMenuData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
						dt.Property_intId, dt.Property_IntOrder, dt.Property_IntParentID,
						dt.Property_TxtDescription, dt.Property_TxtLink, dt.Property_TxtMenuName, dt.Property_intVisible, dt.Property_API_IntMenuID },
				dt.Property_TxtDescription + "=?", new String[] { String.valueOf(txtnmMenu) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mMenuData contact = new mMenuData();
		if (cursor.getCount() > 0) {
			contact = new mMenuData(Long.valueOf(cursor.getString(0)), Long.valueOf(cursor.getString(1)),
					Long.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	public mMenuData getDataByNamaMenu2(SQLiteDatabase db, String txtnmMenu) {
		mMenuData dt = new mMenuData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
						dt.Property_intId, dt.Property_IntOrder, dt.Property_IntParentID,
						dt.Property_TxtDescription, dt.Property_TxtLink, dt.Property_TxtMenuName, dt.Property_intVisible, dt.Property_API_IntMenuID },
				dt.Property_TxtMenuName + "=?", new String[] { String.valueOf(txtnmMenu) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		mMenuData contact = new mMenuData();
		if (cursor.getCount() > 0) {
			contact = new mMenuData(Long.valueOf(cursor.getString(0)), Long.valueOf(cursor.getString(1)),
					Long.valueOf(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
			// return contact
		} else {
			contact = null;
		}
		cursor.close();
		return contact;
	}

	// Getting single contact
	public List<mMenuData> getDatabyParentId(SQLiteDatabase db, int IntParentID) {
		List<mMenuData> contactList = new ArrayList<mMenuData>();
		mMenuData dt = new mMenuData();
		Cursor cursor = db.rawQuery("SELECT  " + dt.Property_All + " FROM "+ TABLE_CONTACTS
				+" WHERE "+dt.Property_IntParentID+"='"+String.valueOf(IntParentID)+"'",null);
		if (cursor.moveToFirst()) {
			do {
				mMenuData contact = new mMenuData();
				contact.set_intId(Long.valueOf(cursor.getString(0)));
				contact.set_IntOrder(Long.valueOf(cursor.getString(1)));
				contact.set_IntParentID(Long.valueOf(cursor.getString(2)));
				contact.set_TxtDescription(cursor.getString(3));
				contact.set_TxtLink(cursor.getString(4));
				contact.set_TxtMenuName(cursor.getString(5));
				contact.set_intVisible(cursor.getString(6));
				contact.set_TxtIcon(cursor.getString(7));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	public List<mMenuData> GetMenuPushData(SQLiteDatabase db) {
		List<mMenuData> contactList = new ArrayList<mMenuData>();
		mMenuData dt = new mMenuData();
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
						dt.Property_intId, dt.Property_IntOrder, dt.Property_IntParentID,
						dt.Property_TxtDescription, dt.Property_TxtLink, dt.Property_TxtMenuName,dt.Property_intVisible },
				dt.Property_TxtDescription + "=?", new String[] { "mnUploadDataMobile" },
				null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				mMenuData contact = new mMenuData();
				contact.set_intId(Long.valueOf(cursor.getString(0)));
				contact.set_IntOrder(Long.valueOf(cursor.getString(1)));
				contact.set_IntParentID(Long.valueOf(cursor.getString(2)));
				contact.set_TxtDescription(cursor.getString(3));
				contact.set_TxtLink(cursor.getString(4));
				contact.set_TxtMenuName(cursor.getString(5));
				contact.set_intVisible(cursor.getString(6));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	public List<mMenuData> getDataActivebyParentId(SQLiteDatabase db, int IntParentID) {
		List<mMenuData> contactList = new ArrayList<mMenuData>();
		mMenuData dt = new mMenuData();
		Cursor cursor = db.rawQuery("SELECT  " + dt.Property_All + " FROM"+ TABLE_CONTACTS
				+" WHERE "+dt.Property_IntParentID+"='"+String.valueOf(IntParentID)+"' AND "+dt.Property_intVisible+"='1'",null);
		if (cursor.moveToFirst()) {
			do {
				mMenuData contact = new mMenuData();
				contact.set_intId(Long.valueOf(cursor.getString(0)));
				contact.set_IntOrder(Long.valueOf(cursor.getString(1)));
				contact.set_IntParentID(Long.valueOf(cursor.getString(2)));
				contact.set_TxtDescription(cursor.getString(3));
				contact.set_TxtLink(cursor.getString(4));
				contact.set_TxtMenuName(cursor.getString(5));
				contact.set_intVisible(cursor.getString(6));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	public List<mMenuData> getDatabyName(SQLiteDatabase db, String txtName) {
		List<mMenuData> contactList = new ArrayList<mMenuData>();
		mMenuData dt = new mMenuData();
		Cursor cursor = db.rawQuery("SELECT  " + dt.Property_All + " FROM"+ TABLE_CONTACTS
				+" WHERE "+dt.Property_TxtDescription+"='"+String.valueOf(txtName)+"'",null);
		if (cursor.moveToFirst()) {
			do {
				mMenuData contact = new mMenuData();
				contact.set_intId(Long.valueOf(cursor.getString(0)));
				contact.set_IntOrder(Long.valueOf(cursor.getString(1)));
				contact.set_IntParentID(Long.valueOf(cursor.getString(2)));
				contact.set_TxtDescription(cursor.getString(3));
				contact.set_TxtLink(cursor.getString(4));
				contact.set_TxtMenuName(cursor.getString(5));
				contact.set_intVisible(cursor.getString(6));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return contactList;
	}
	// Getting All Contacts
	public List<mMenuData> getAllData(SQLiteDatabase db) {
		List<mMenuData> contactList = new ArrayList<mMenuData>();
		// Select All Query
		mMenuData dt = new mMenuData();
		String selectQuery = "SELECT  " + dt.Property_All + " FROM "
				+ TABLE_CONTACTS +" ORDER BY "+dt.Property_IntOrder+" ASC";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				mMenuData contact = new mMenuData();
				contact.set_intId(Long.valueOf(cursor.getString(0)));
				contact.set_IntOrder(Long.valueOf(cursor.getString(1)));
				contact.set_IntParentID(Long.valueOf(cursor.getString(2)));
				contact.set_TxtDescription(cursor.getString(3));
				contact.set_TxtLink(cursor.getString(4));
				contact.set_TxtMenuName(cursor.getString(5));
				contact.set_intVisible(cursor.getString(6));
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
		mMenuData dt = new mMenuData();
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

	public void InsertDefaultMconfig(SQLiteDatabase db) {
		String txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  1,'1','1','mnAbsenKBN','com.kalbenutritionals.app.kalbespgmobile.AbsenFragment','1','', 'ic_location', 'Absen';";
		db.execSQL(txtQuery);

		txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  2,'2','1','mnDownloadData','com.kalbenutritionals.app.kalbespgmobile.DownloadDataFragment','2','', 'ic_cloud_download', 'Download Data';";
		db.execSQL(txtQuery);

		txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  3,'3','3','mnPushDataData','com.kalbenutritionals.app.kalbespgmobile','1','', 'ic_location', 'Push Data';";
		db.execSQL(txtQuery);

		txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  4,'1','2','mnReso','com.kalbenutritionals.app.kalbespgmobile.FragmentViewReso','1','', 'ic_assignment', 'Reso';";
		db.execSQL(txtQuery);

		txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  5,'2','2','mnActivity','com.kalbenutritionals.app.kalbespgmobile.FragmentViewActivity','2','', 'ic_history', 'Activity';";
		db.execSQL(txtQuery);

		txtQuery = "insert into mMenu(intId,intOrder,intParentID,TxtDescription,TxtLink, intMenuID, intVisible, TxtIcon, TxtMenuName )"
				+ "select  6,'3','2','mnCustomerBase','com.kalbenutritionals.app.kalbespgmobile.FragmentViewCustomerBase','3','', 'ic_person', 'Customer Base';";
		db.execSQL(txtQuery);
	}
}
