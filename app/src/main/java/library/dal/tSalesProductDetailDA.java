package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tSalesProductDetailData;

public class tSalesProductDetailDA {
	// All Static variables
			// Contacts table name
			private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tSalesProductDetail;

			// Contacts Table Columns names

			public tSalesProductDetailDA(SQLiteDatabase db) {
				tSalesProductDetailData dt=new tSalesProductDetailData();
				String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
						+ dt.Property_intId + " TEXT PRIMARY KEY," 
						+ dt.Property_dtDate + " TEXT NULL,"
						+ dt.Property_intPrice + " TEXT NULL,"
						+ dt.Property_intQty + " TEXT NULL,"
						+ dt.Property_txtCodeProduct + " TEXT NULL,"
						+ dt.Property_txtKeterangan + " TEXT NULL,"
						+ dt.Property_txtNameProduct + " TEXT NULL,"
						+ dt.Property_intTotal + " TEXT NULL,"
						+ dt.Property_txtNoSo + " TEXT NULL,"
						+ dt.Property_intActive + " TEXT NULL,"
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
			public void SaveDatatSalesProductDetailData(SQLiteDatabase db,tSalesProductDetailData data) {
				tSalesProductDetailData dt=new tSalesProductDetailData();
				//ContentValues values = new ContentValues();
				//values.put(dt.Property_intIdMenu, data.get_intIdMenu()); 
				//values.put(dt.Property_txtRoleId, data.get_txtRoleId()); 
				// Inserting Row
				//db.insert(TABLE_CONTACTS, null, values);
				db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intId+","
						+dt.Property_dtDate+","
						+dt.Property_intPrice+","
						+dt.Property_intQty+","
						+dt.Property_txtCodeProduct+","
						+dt.Property_txtKeterangan+","
						+dt.Property_txtNameProduct+","
						+dt.Property_intTotal+","
						+dt.Property_txtNoSo+","
						+dt.Property_intActive+","
						+dt.Property_txtNIK+") "+
					"values('"	+String.valueOf(data.get_intId())+"','"
						+String.valueOf(data.get_dtDate())+"','"
						+String.valueOf(data.get_intPrice())+"','"
						+String.valueOf(data.get_intQty())+"','"
						+String.valueOf(data.get_txtCodeProduct())+"','"
						+String.valueOf(data.get_txtKeterangan())+"','"
						+String.valueOf(data.get_txtNameProduct())+"','"
						+String.valueOf(data.get_intTotal())+"','"
						+String.valueOf(data.get_txtNoSo())+"','"
						+String.valueOf(data.get_intActive())+"','"
						+String.valueOf(data.get_txtNIK())+"')");
				
			}

			// Adding new contact
			public void UpdateInactiveData(SQLiteDatabase db,String id) {
				tSalesProductDetailData dt=new tSalesProductDetailData();
				db.execSQL("Update "+TABLE_CONTACTS+" SET "+dt.Property_intActive+" = 0 " +
					" WHERE "+ dt.Property_intId +" = '" +id+"'");
				
			}
						
			// Getting single contact
			public tSalesProductDetailData getData(SQLiteDatabase db,int id) {
				tSalesProductDetailData dt=new tSalesProductDetailData();
				Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
						dt.Property_dtDate,dt.Property_intPrice, dt.Property_intQty, 
						dt.Property_txtCodeProduct,dt.Property_txtKeterangan ,dt.Property_txtNameProduct,dt.Property_txtNIK,
						dt.Property_intTotal,dt.Property_txtNoSo,dt.Property_intActive}, dt.Property_intId + "=?",
						new String[] { String.valueOf(id) }, null, null, null, null);
				if (cursor != null)
					cursor.moveToFirst();
				tSalesProductDetailData contact = new tSalesProductDetailData(String.valueOf(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
				// return contact
				cursor.close();
				return contact;
			}
			
			// Getting single contact
			public List<tSalesProductDetailData> getDataByNoSO(SQLiteDatabase db,String NoSo) {
				List<tSalesProductDetailData> contactList = null;
				tSalesProductDetailData dt=new tSalesProductDetailData();
				Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
						dt.Property_dtDate,dt.Property_intPrice, dt.Property_intQty, 
						dt.Property_txtCodeProduct,dt.Property_txtKeterangan ,dt.Property_txtNameProduct,dt.Property_txtNIK,dt.Property_intTotal,dt.Property_txtNoSo,
						dt.Property_intActive}, dt.Property_txtNoSo + "=?",
						new String[] { String.valueOf(NoSo) }, null, null, null, null);
				if (cursor != null){
					if (cursor.moveToFirst()) {
						contactList=new ArrayList<tSalesProductDetailData>();
						do {
							tSalesProductDetailData contact = new tSalesProductDetailData();
							contact.set_intId(String.valueOf(cursor.getString(0)));
							contact.set_dtDate(cursor.getString(1));
							contact.set_intPrice(cursor.getString(2));
							contact.set_intQty(cursor.getString(3));
							contact.set_txtCodeProduct(cursor.getString(4));
							contact.set_txtKeterangan(cursor.getString(5));
							contact.set_txtNameProduct(cursor.getString(6));
							contact.set_txtNIK(cursor.getString(7));
							contact.set_intTotal(cursor.getString(8));
							contact.set_txtNoSo(cursor.getString(9));
							contact.set_intActive(cursor.getString(10));
							// Adding contact to list
							contactList.add(contact);
						} while (cursor.moveToNext());
					}
				}
				// return contact
				cursor.close();
				return contactList;
			}
			// Getting All Contacts
			public List<tSalesProductDetailData> getSalesProductDetailByHeaderId(SQLiteDatabase db,String txtNoSO) {
				List<tSalesProductDetailData> contactList = new ArrayList<tSalesProductDetailData>();
				// Select All Query
				tSalesProductDetailData dt=new tSalesProductDetailData();
				String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_txtNoSo+"='"+txtNoSO+"' AND "+dt.Property_intActive+"=1";

				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						tSalesProductDetailData contact = new tSalesProductDetailData();
						contact.set_intId(String.valueOf(cursor.getString(1)));
						contact.set_dtDate(cursor.getString(0));
						contact.set_intPrice(cursor.getString(2));
						contact.set_intQty(cursor.getString(3));
						contact.set_txtCodeProduct(cursor.getString(4));
						contact.set_txtKeterangan(cursor.getString(5));
						contact.set_txtNameProduct(cursor.getString(6));
						contact.set_intTotal(cursor.getString(7));
						contact.set_txtNIK(cursor.getString(8));
						contact.set_txtNoSo(cursor.getString(9));
						contact.set_intActive(cursor.getString(10));
						// Adding contact to list
						contactList.add(contact);
					} while (cursor.moveToNext());
				}
				cursor.close();
				// return contact list
				return contactList;
			}
			// Getting All Contacts
			public List<tSalesProductDetailData> getAllData(SQLiteDatabase db) {
				List<tSalesProductDetailData> contactList = new ArrayList<tSalesProductDetailData>();
				// Select All Query
				tSalesProductDetailData dt=new tSalesProductDetailData();
				String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intActive+"=1";

				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						tSalesProductDetailData contact = new tSalesProductDetailData();
						contact.set_intId(String.valueOf(cursor.getString(1)));
						contact.set_dtDate(cursor.getString(0));
						contact.set_intPrice(cursor.getString(2));
						contact.set_intQty(cursor.getString(3));
						contact.set_txtCodeProduct(cursor.getString(4));
						contact.set_txtKeterangan(cursor.getString(5));
						contact.set_txtKeterangan(cursor.getString(6));
						contact.set_intTotal(cursor.getString(7));
						contact.set_txtNIK(cursor.getString(8));
						contact.set_txtNoSo(cursor.getString(9));
						contact.set_intActive(cursor.getString(10));
						// Adding contact to list
						contactList.add(contact);
					} while (cursor.moveToNext());
				}
				cursor.close();
				// return contact list
				return contactList;
			}
			public void DeleteAllDAta(SQLiteDatabase db) {
				// Drop older table if existed
				db.execSQL("DELETE FROM " + TABLE_CONTACTS);
			}
			// Deleting single contact
			public void deleteContact(SQLiteDatabase db,String id) {		
				tSalesProductDetailData dt = new tSalesProductDetailData();
				db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
						new String[] { String.valueOf(id) });
			}
			public void deleteByNOSO(SQLiteDatabase db,String NOSO) {		
				tSalesProductDetailData dt = new tSalesProductDetailData();
				db.delete(TABLE_CONTACTS, dt.Property_txtNoSo + " = ?",
						new String[] { NOSO });
			}

			// Getting contacts Count
			public Long getContactsCount(SQLiteDatabase db) {
				String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS;
				Cursor cursor = db.rawQuery(countQuery, null);
				long intres=(long) cursor.getCount();
				cursor.close();

				// return count
				return intres;
			}
}
