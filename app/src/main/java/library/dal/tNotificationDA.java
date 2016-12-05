package library.dal;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tNotificationData;

public class tNotificationDA {
	public tNotificationDA(SQLiteDatabase db) {
		tNotificationData dt = new tNotificationData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
		+ TABLE_CONTACTS + "(" + dt.Property_intIndex + " INT NOT NULL,"
		+ dt.Property_guiID + " TEXT PRIMARY KEY,"
		+ dt.Property_tPublishStart + " TEXT NULL," 
		+ dt.Property_dtPublishEnd + " TEXT NULL,"
		+ dt.Property_bitActive + " TEXT NULL,"
		+ dt.Property_txtTitle + " TEXT NULL,"
		+ dt.Property_txtDescription + " TEXT NULL," 
		+ dt.Property_txtImage + " TEXT NULL,"
		+ dt.Property_txtLink + " TEXT NULL,"
		+ dt.Property_intPriority + " TEXT NULL,"
		+ dt.Property_txtOutlet + " TEXT NULL,"
		+ dt.Property_txtOutletName + " TEXT NULL," 
		+ dt.Property_txtBranchCode + " TEXT NULL,"
		+ dt.Property_txtStatus + " TEXT NULL,"
		+ dt.Property_dtUpdate + " TEXT NULL,"
		+ dt.Property_intSubmit + " TEXT NULL,"
		+ dt.Property_intSync + " TEXT NULL)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}
	
	// All Static variables

		// Contacts table name
		private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tNotification;
		
		// Upgrading database
		public void DropTable(SQLiteDatabase db) {
			// Drop older table if existed
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		}
		// Adding new contact
		public void SaveDataMConfig(SQLiteDatabase db, tNotificationData data) {
			tNotificationData dt = new tNotificationData();
			db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
					+ dt.PropertyAll 
					+ ") " + "values('"
					+ String.valueOf(data.get_intIndex()) + "','"
					+ String.valueOf(data.get_guiID()) + "','"
					+ String.valueOf(data.get_tPublishStart()) + "','"
					+ String.valueOf(data.get_dtPublishEnd()) + "','"
					+ String.valueOf(data.get_bitActive()) + "','"
					+ String.valueOf(data.get_txtTitle()) + "','"
					+ String.valueOf(data.get_txtDescription()) + "','"
					+ String.valueOf(data.get_txtImage()) + "','"
					+ String.valueOf(data.get_txtLink()) + "','"
					+ String.valueOf(data.get_txtOutlet()) + "','"
					+ String.valueOf(data.get_txtOutletName()) + "','"
					+ String.valueOf(data.get_txtBranchCode()) + "','"
					+ String.valueOf(data.get_txtStatus()) + "','"
					+ String.valueOf(data.get_dtUpdate()) + "','"
					+ String.valueOf(data.get_intSubmit()) + "','"
					+ String.valueOf(data.get_intSync()) + "','"
					+ String.valueOf(data.get_intPriority()) + "')");
		}
		public void DeleteAllData(SQLiteDatabase db) {
			db.execSQL("DELETE FROM " + TABLE_CONTACTS );
		}
		// Getting single contact
		public tNotificationData getData(SQLiteDatabase db, String id) {
			tNotificationData dt = new tNotificationData();
			String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " 
					+ TABLE_CONTACTS+" WHERE "+dt.Property_guiID+"='"+id+"'";;
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor != null)
				cursor.moveToFirst();
			tNotificationData contact = new tNotificationData();
			if (cursor.getCount() > 0) {
				contact.set_intIndex(cursor.getString(0));
				contact.set_guiID(cursor.getString(1));
				contact.set_tPublishStart(cursor.getString(2));
				contact.set_dtPublishEnd(cursor.getString(3));
				contact.set_bitActive(cursor.getString(4));
				contact.set_txtTitle(cursor.getString(5));
				contact.set_txtDescription(cursor.getString(6));
				contact.set_txtImage(cursor.getString(7));
				contact.set_txtLink(cursor.getString(8));
				contact.set_txtOutlet(cursor.getString(9));
				contact.set_txtOutletName(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtStatus(cursor.getString(12));
				contact.set_dtUpdate(cursor.getString(13));
				contact.set_intSubmit(cursor.getString(14));
				contact.set_intSync(cursor.getString(15));
				contact.set_intPriority(cursor.getString(16));
				// return contact
			} else {
				contact = null;
			}
			cursor.close();
			return contact;
		}
		
		public List<tNotificationData> getAllDataWillAlert(SQLiteDatabase db,String Status) {
			List<tNotificationData> contactList = null;
			// Select All Query
			tNotificationData dt = new tNotificationData();
			String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " 
					+ TABLE_CONTACTS +" WHERE "+dt.Property_txtStatus+"='"+Status+"'";
			Cursor cursor = db.rawQuery(selectQuery, null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				contactList=new ArrayList<tNotificationData>();
				do {
					tNotificationData contact = new tNotificationData();
					contact.set_intIndex(cursor.getString(0));
					contact.set_guiID(cursor.getString(1));
					contact.set_tPublishStart(cursor.getString(2));
					contact.set_dtPublishEnd(cursor.getString(3));
					contact.set_bitActive(cursor.getString(4));
					contact.set_txtTitle(cursor.getString(5));
					contact.set_txtDescription(cursor.getString(6));
					contact.set_txtImage(cursor.getString(7));
					contact.set_txtLink(cursor.getString(8));
					contact.set_txtOutlet(cursor.getString(9));
					contact.set_txtOutletName(cursor.getString(10));
					contact.set_txtBranchCode(cursor.getString(11));
					contact.set_txtStatus(cursor.getString(12));
					contact.set_dtUpdate(cursor.getString(13));
					contact.set_intSubmit(cursor.getString(14));
					contact.set_intSync(cursor.getString(15));
					contact.set_intPriority(cursor.getString(16));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}
			cursor.close();
			// return contact list
			return contactList;
		}
		
		public List<tNotificationData> getAllData(SQLiteDatabase db) {
			List<tNotificationData> contactList = null;
			// Select All Query
			tNotificationData dt = new tNotificationData();
			String selectQuery = "SELECT  " + dt.PropertyAll + " FROM " 
					+ TABLE_CONTACTS;
			Cursor cursor = db.rawQuery(selectQuery, null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				contactList=new ArrayList<tNotificationData>();
				do {
					tNotificationData contact = new tNotificationData();
					contact.set_intIndex(cursor.getString(0));
					contact.set_guiID(cursor.getString(1));
					contact.set_tPublishStart(cursor.getString(2));
					contact.set_dtPublishEnd(cursor.getString(3));
					contact.set_bitActive(cursor.getString(4));
					contact.set_txtTitle(cursor.getString(5));
					contact.set_txtDescription(cursor.getString(6));
					contact.set_txtImage(cursor.getString(7));
					contact.set_txtLink(cursor.getString(8));
					contact.set_txtOutlet(cursor.getString(9));
					contact.set_txtOutletName(cursor.getString(10));
					contact.set_txtBranchCode(cursor.getString(11));
					contact.set_txtStatus(cursor.getString(12));
					contact.set_dtUpdate(cursor.getString(13));
					contact.set_intSubmit(cursor.getString(14));
					contact.set_intSync(cursor.getString(15));
					contact.set_intPriority(cursor.getString(16));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}
			cursor.close();
			// return contact list
			return contactList;
		}
		
		// Getting All Contacts
		public tNotificationData getDataStatusN(SQLiteDatabase db,String status) {
			tNotificationData dt = new tNotificationData();
			Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
					dt.Property_intIndex,dt.Property_guiID, dt.Property_tPublishStart, dt.Property_dtPublishEnd,
					dt.Property_bitActive,dt.Property_txtTitle,dt.Property_txtDescription,dt.Property_txtImage,dt.Property_txtLink,dt.Property_txtOutlet,dt.Property_txtOutletName,dt.Property_txtBranchCode,dt.Property_txtStatus,dt.Property_dtUpdate,
					dt.Property_intSubmit,dt.Property_intSync},
					dt.Property_txtStatus + "=?", new String[] { String.valueOf(status) },
					null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();
			tNotificationData contact = new tNotificationData();
			if (cursor.getCount() > 0) {
				contact.set_intIndex(cursor.getString(0));
				contact.set_guiID(cursor.getString(1));
				contact.set_tPublishStart(cursor.getString(2));
				contact.set_dtPublishEnd(cursor.getString(3));
				contact.set_bitActive(cursor.getString(4));
				contact.set_txtTitle(cursor.getString(5));
				contact.set_txtDescription(cursor.getString(6));
				contact.set_txtImage(cursor.getString(7));
				contact.set_txtLink(cursor.getString(8));
				contact.set_txtOutlet(cursor.getString(9));
				contact.set_txtOutletName(cursor.getString(10));
				contact.set_txtBranchCode(cursor.getString(11));
				contact.set_txtStatus(cursor.getString(12));
				contact.set_dtUpdate(cursor.getString(13));
				contact.set_intSubmit(cursor.getString(14));
				contact.set_intSync(cursor.getString(15));
				contact.set_intPriority(cursor.getString(16));
				// return contact
			} else {
				contact = null;
			}
			cursor.close();
			return contact;
		}
		
		
		

		
		// Deleting single contact
					public void deleteContactByNotifId(SQLiteDatabase db,String id) {		
						tNotificationData dt = new tNotificationData();
						db.delete(TABLE_CONTACTS, dt.Property_guiID + " = ?",
								new String[] { String.valueOf(id) });
					}
					public void DeleteAllDAta(SQLiteDatabase db) {
						// Drop older table if existed
						db.execSQL("DELETE FROM " + TABLE_CONTACTS);
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
					
					public void updateStatus(SQLiteDatabase db,String dataid) {
						tNotificationData dt=new tNotificationData();
						db.execSQL("Update "+TABLE_CONTACTS+" set  "
								+dt.Property_txtStatus+"=0"
								+" Where " + dt.Property_guiID +"='"+ dataid+"'");
					}
					public void getDataGUi(SQLiteDatabase db,String dataid) {
						tNotificationData dt=new tNotificationData();
						db.execSQL("SELECT * FROM "+TABLE_CONTACTS+" Where " + dt.Property_guiID +"='"+ dataid+"'");
					}
					//count status
					public int getContactsCountStatus(SQLiteDatabase db) {
						tNotificationData dt=new tNotificationData();
						String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtStatus+"=1";
						Cursor cursor = db.rawQuery(countQuery, null);
						int num =cursor.getCount();
						cursor.close();

						// return count
						return num;
					}
					
					
}

