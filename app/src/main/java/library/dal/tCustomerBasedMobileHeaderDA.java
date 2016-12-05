package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tCustomerBasedMobileHeaderData;

public class tCustomerBasedMobileHeaderDA {
	// All Static variables

			// Contacts table name
			private static final String TABLE_NAME = new clsHardCode().txtTable_tCustomerBasedMobileHeader;

			// Contacts Table Columns names

			public tCustomerBasedMobileHeaderDA(SQLiteDatabase db) {
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
						+ dt.Property_intTrCustomerId + " TEXT PRIMARY KEY,"
						+ dt.Property_txtSubmissionId + " TEXT NULL,"
						+ dt.Property_txtSubmissionCode + " TEXT NULL,"
						+ dt.Property_txtBranchCode + " TEXT NULL,"
						+ dt.Property_txtSumberData + " TEXT NULL,"
						+ dt.Property_txtNamaSumberData + " TEXT NULL,"
						+ dt.Property_txtNamaDepan + " TEXT NULL,"
						+ dt.Property_txtTelp + " TEXT NULL,"
						+ dt.Property_txtTelpKantor + " TEXT NULL,"
						+ dt.Property_txtEmail + " TEXT NULL,"
						+ dt.Property_txtPINBBM + " TEXT NULL,"
						+ dt.Property_txtALamat + " TEXT NULL,"
						+ dt.Property_txtUserId + " TEXT NULL,"
						+ dt.Property_intPIC + " TEXT NULL,"
						+ dt.Property_txtDeviceId + " TEXT NULL,"
						+ dt.Property_bitActive + " TEXT NULL,"
						+ dt.Property_dtDate + " TEXT NULL,"
						+ dt.Property_intSubmit + " TEXT NULL,"
						+ dt.Property_intSync + " TEXT NULL" + ")";
				db.execSQL(CREATE_CONTACTS_TABLE);
			}

			// Upgrading database
			public void DropTable(SQLiteDatabase db) {
				// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
				// Create tables again
			}
			
			/**
			 * All CRUD(Create, Read, Update, Delete) Operations
			**/
			
			// Adding new contact
			public void SaveDatatCustomerBasedMobileHeaderData(SQLiteDatabase db,tCustomerBasedMobileHeaderData data) {
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				db.execSQL("INSERT OR REPLACE into "+TABLE_NAME+" ("+dt.Property_ALL+") "+
						   "values('"	+String.valueOf(data.get_intTrCustomerId())+"','"
										+String.valueOf(data.get_txtSubmissionId())+"','"
										+String.valueOf(data.get_txtSubmissionCode())+"','"
										+String.valueOf(data.get_txtBranchCode())+"','"
										+String.valueOf(data.get_txtSumberData())+"','"
										+String.valueOf(data.get_txtNamaSumberData())+"','"
										+String.valueOf(data.get_txtNamaDepan().replace("'", "`"))+"','"
										+String.valueOf(data.get_txtTelp())+"','"
										+String.valueOf(data.get_txtTelpKantor().replace("'", "`"))+"','"
										+String.valueOf(data.get_txtEmail())+"','"
										+String.valueOf(data.get_txtPINBBM())+"','"
										+String.valueOf(data.get_txtALamat())+"','"
										+String.valueOf(data.get_txtUserId())+"','"
										+String.valueOf(data.get_intPIC())+"','"
										+String.valueOf(data.get_txtDeviceId())+"','"
										+String.valueOf(data.get_bitActive())+"','"
										+String.valueOf(data.get_dtDate())+"','"
									    +String.valueOf(data.get_intSubmit())+"','"
										+String.valueOf(data.get_intSync())+"')"
							);
			}
			
			// Getting single contact
			public tCustomerBasedMobileHeaderData getData(SQLiteDatabase db,String id) {
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				String[] tableColumns = new String[] { 
														dt.Property_intTrCustomerId,
														dt.Property_txtSubmissionId,
														dt.Property_txtSubmissionCode,
														dt.Property_txtBranchCode, 
														dt.Property_txtSumberData, 
														dt.Property_txtNamaSumberData,
														dt.Property_txtNamaDepan,
														dt.Property_txtTelp,
														dt.Property_txtTelpKantor,
														dt.Property_txtEmail,
														dt.Property_txtPINBBM,
														dt.Property_txtALamat,
														dt.Property_txtUserId,
														dt.Property_intPIC,
														dt.Property_txtDeviceId,
														dt.Property_bitActive,
														dt.Property_dtDate,
														dt.Property_intSubmit,
														dt.Property_intSync
													};
				String whereClause = dt.Property_intTrCustomerId + "=?";
				String[] whereArgs = new String[] {
													String.valueOf(id)
												  };
				String groupBy = null;
				String havingBy = null;
				String orderBy = dt.Property_intTrCustomerId;
				
				Cursor cursor = db.query(TABLE_NAME,
										tableColumns,
										whereClause,
										whereArgs, 
										groupBy, 
										havingBy, 
										orderBy);
				if (cursor != null)
					cursor.moveToFirst();
				tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
				contact.set_intTrCustomerId(cursor.getString(0));
				contact.set_txtSubmissionId(cursor.getString(1));
				contact.set_txtSubmissionCode(cursor.getString(2));
				contact.set_txtBranchCode(cursor.getString(3));
				contact.set_txtSumberData(cursor.getString(4));
				contact.set_txtNamaSumberData(cursor.getString(5));
				contact.set_txtNamaDepan(cursor.getString(6));
				contact.set_txtTelp(cursor.getString(7));
				contact.set_txtTelpKantor(cursor.getString(8));
				contact.set_txtEmail(cursor.getString(9));
				contact.set_txtPINBBM(cursor.getString(10));
				contact.set_txtALamat(cursor.getString(11));
				contact.set_txtUserId(cursor.getString(12));
				contact.set_intPIC(cursor.getString(13));
				contact.set_txtDeviceId(cursor.getString(14));
				contact.set_bitActive(cursor.getString(15));
				contact.set_dtDate(cursor.getString(16));
				contact.set_intSubmit(cursor.getString(17));
				contact.set_intSync(cursor.getString(18));
				// return contact
				cursor.close();
				return contact;
			}
			
			// Getting All Contacts
			public List<tCustomerBasedMobileHeaderData> getAllData(SQLiteDatabase db) {
				List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
				// Select All Query
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME + " WHERE " + dt.Property_intSubmit + "='1'";

				Cursor cursor = db.rawQuery(selectQuery, null);

				if (cursor.moveToFirst()) {
					do {
						tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
						contact.set_intTrCustomerId(cursor.getString(0));
						contact.set_txtSubmissionId(cursor.getString(1));
						contact.set_txtSubmissionCode(cursor.getString(2));
						contact.set_txtBranchCode(cursor.getString(3));
						contact.set_txtSumberData(cursor.getString(4));
						contact.set_txtNamaSumberData(cursor.getString(5));
						contact.set_txtNamaDepan(cursor.getString(6));
						contact.set_txtTelp(cursor.getString(7));
						contact.set_txtTelpKantor(cursor.getString(8));
						contact.set_txtEmail(cursor.getString(9));
						contact.set_txtPINBBM(cursor.getString(10));
						contact.set_txtALamat(cursor.getString(11));
						contact.set_txtUserId(cursor.getString(12));
						contact.set_intPIC(cursor.getString(13));
						contact.set_txtDeviceId(cursor.getString(14));
						contact.set_bitActive(cursor.getString(15));
						contact.set_dtDate(cursor.getString(16));
						contact.set_intSubmit(cursor.getString(17));
						contact.set_intSync(cursor.getString(18));
						contactList.add(contact);
					} while (cursor.moveToNext());
				}
				cursor.close();
				// return contact list
				return contactList;
			}
			
			public List<tCustomerBasedMobileHeaderData> getPushData(SQLiteDatabase db) {
				List<tCustomerBasedMobileHeaderData> contactList = null;
				// Select All Query
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME+" WHERE "+dt.Property_intSubmit+"='1' AND "+dt.Property_intSync+"='0'";

				Cursor cursor = db.rawQuery(selectQuery, null);

				if (cursor.moveToFirst()) {
					contactList=new ArrayList<tCustomerBasedMobileHeaderData>();
					do {
						tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
						contact.set_intTrCustomerId(cursor.getString(0));
						contact.set_txtSubmissionId(cursor.getString(1));
						contact.set_txtSubmissionCode(cursor.getString(2));
						contact.set_txtBranchCode(cursor.getString(3));
						contact.set_txtSumberData(cursor.getString(4));
						contact.set_txtNamaSumberData(cursor.getString(5));
						contact.set_txtNamaDepan(cursor.getString(6));
						contact.set_txtTelp(cursor.getString(7));
						contact.set_txtTelpKantor(cursor.getString(8));
						contact.set_txtEmail(cursor.getString(9));
						contact.set_txtPINBBM(cursor.getString(10));
						contact.set_txtALamat(cursor.getString(11));
						contact.set_txtUserId(cursor.getString(12));
						contact.set_intPIC(cursor.getString(13));
						contact.set_txtDeviceId(cursor.getString(14));
						contact.set_bitActive(cursor.getString(15));
						contact.set_dtDate(cursor.getString(16));
						contact.set_intSubmit(cursor.getString(17));
						contact.set_intSync(cursor.getString(18));
						contactList.add(contact);
					} while (cursor.moveToNext());
				}
				cursor.close();
				// return contact list
				return contactList;
			}
			
			public List<tCustomerBasedMobileHeaderData> getNewData(SQLiteDatabase db) {
				List<tCustomerBasedMobileHeaderData> contactList = new ArrayList<tCustomerBasedMobileHeaderData>();
				// Select All Query
				tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
				String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME+" WHERE "+dt.Property_intSubmit+"='0'";

				Cursor cursor = db.rawQuery(selectQuery, null);

				if (cursor.moveToFirst()) {
					do {
						tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
						contact.set_intTrCustomerId(cursor.getString(0));
						contact.set_txtSubmissionId(cursor.getString(1));
						contact.set_txtSubmissionCode(cursor.getString(2));
						contact.set_txtBranchCode(cursor.getString(3));
						contact.set_txtSumberData(cursor.getString(4));
						contact.set_txtNamaSumberData(cursor.getString(5));
						contact.set_txtNamaDepan(cursor.getString(6));
						contact.set_txtTelp(cursor.getString(7));
						contact.set_txtTelpKantor(cursor.getString(8));
						contact.set_txtEmail(cursor.getString(9));
						contact.set_txtPINBBM(cursor.getString(10));
						contact.set_txtALamat(cursor.getString(11));
						contact.set_txtUserId(cursor.getString(12));
						contact.set_intPIC(cursor.getString(13));
						contact.set_txtDeviceId(cursor.getString(14));
						contact.set_bitActive(cursor.getString(15));
						contact.set_dtDate(cursor.getString(16));
						contact.set_intSubmit(cursor.getString(17));
						contact.set_intSync(cursor.getString(18));
						contactList.add(contact);
					} while (cursor.moveToNext());
				}
				cursor.close();
				// return contact list
				return contactList;
			}
			
			
			// Deleting single contact
			public void deleteByID(SQLiteDatabase db,String id) {		
				tCustomerBasedMobileHeaderData dt = new tCustomerBasedMobileHeaderData();
				String whereClause =dt.Property_intTrCustomerId + " = ?";
				String[] whereArgs = new String[] {
													String.valueOf(id)
												  };
				
				db.delete(TABLE_NAME, whereClause,whereArgs);
			}

			public void DeleteAllDAta(SQLiteDatabase db) {
				// Drop older table if existed
				db.execSQL("DELETE FROM " + TABLE_NAME);
			}
			// Getting contacts Count
			public int getContactsCount(SQLiteDatabase db) {
				String countQuery = "SELECT * FROM " + TABLE_NAME;
				Cursor cursor = db.rawQuery(countQuery, null);
				int num =cursor.getCount();
				cursor.close();

				// return count
				return num;
			}

	public tCustomerBasedMobileHeaderData getDataByBitActive(SQLiteDatabase db) {
        tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();

		tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME + " WHERE " + dt.Property_bitActive + " = '1'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
            contact.set_intTrCustomerId(cursor.getString(0));
            contact.set_txtSubmissionId(cursor.getString(1));
            contact.set_txtSubmissionCode(cursor.getString(2));
            contact.set_txtBranchCode(cursor.getString(3));
            contact.set_txtSumberData(cursor.getString(4));
            contact.set_txtNamaSumberData(cursor.getString(5));
            contact.set_txtNamaDepan(cursor.getString(6));
            contact.set_txtTelp(cursor.getString(7));
            contact.set_txtTelpKantor(cursor.getString(8));
            contact.set_txtEmail(cursor.getString(9));
            contact.set_txtPINBBM(cursor.getString(10));
            contact.set_txtALamat(cursor.getString(11));
            contact.set_txtUserId(cursor.getString(12));
            contact.set_intPIC(cursor.getString(13));
            contact.set_txtDeviceId(cursor.getString(14));
            contact.set_bitActive(cursor.getString(15));
            contact.set_dtDate(cursor.getString(16));
            contact.set_intSubmit(cursor.getString(17));
            contact.set_intSync(cursor.getString(18));
		}
		cursor.close();
		return contact;
	}

	public List<tCustomerBasedMobileHeaderData> getAllDataByIntSyc(SQLiteDatabase db, String int_sync) {
		List<tCustomerBasedMobileHeaderData> contactList = null;
		// Select All Query
		tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME +" WHERE "+dt.Property_intSync +"='"+int_sync+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCustomerBasedMobileHeaderData>();
			do {
				tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
				contact.set_intTrCustomerId(cursor.getString(0));
				contact.set_txtSubmissionId(cursor.getString(1));
				contact.set_txtSubmissionCode(cursor.getString(2));
				contact.set_txtBranchCode(cursor.getString(3));
				contact.set_txtSumberData(cursor.getString(4));
				contact.set_txtNamaSumberData(cursor.getString(5));
				contact.set_txtNamaDepan(cursor.getString(6));
				contact.set_txtTelp(cursor.getString(7));
				contact.set_txtTelpKantor(cursor.getString(8));
				contact.set_txtEmail(cursor.getString(9));
				contact.set_txtPINBBM(cursor.getString(10));
				contact.set_txtALamat(cursor.getString(11));
				contact.set_txtUserId(cursor.getString(12));
				contact.set_intPIC(cursor.getString(13));
				contact.set_txtDeviceId(cursor.getString(14));
				contact.set_bitActive(cursor.getString(15));
				contact.set_dtDate(cursor.getString(16));
				contact.set_intSubmit(cursor.getString(17));
				contact.set_intSync(cursor.getString(18));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	public List<tCustomerBasedMobileHeaderData> getLastData(SQLiteDatabase db) {
		List<tCustomerBasedMobileHeaderData> contactList = null;
		// Select All Query
		tCustomerBasedMobileHeaderData dt=new tCustomerBasedMobileHeaderData();
		String selectQuery = "SELECT  "+dt.Property_ALL +" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToLast()) {
			contactList=new ArrayList<tCustomerBasedMobileHeaderData>();
			do {
				tCustomerBasedMobileHeaderData contact = new tCustomerBasedMobileHeaderData();
				contact.set_intTrCustomerId(cursor.getString(0));
				contact.set_txtSubmissionId(cursor.getString(1));
				contact.set_txtSubmissionCode(cursor.getString(2));
				contact.set_txtBranchCode(cursor.getString(3));
				contact.set_txtSumberData(cursor.getString(4));
				contact.set_txtNamaSumberData(cursor.getString(5));
				contact.set_txtNamaDepan(cursor.getString(6));
				contact.set_txtTelp(cursor.getString(7));
				contact.set_txtTelpKantor(cursor.getString(8));
				contact.set_txtEmail(cursor.getString(9));
				contact.set_txtPINBBM(cursor.getString(10));
				contact.set_txtALamat(cursor.getString(11));
				contact.set_txtUserId(cursor.getString(12));
				contact.set_intPIC(cursor.getString(13));
				contact.set_txtDeviceId(cursor.getString(14));
				contact.set_bitActive(cursor.getString(15));
				contact.set_dtDate(cursor.getString(16));
				contact.set_intSubmit(cursor.getString(17));
				contact.set_intSync(cursor.getString(18));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

}
