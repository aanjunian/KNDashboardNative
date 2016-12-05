package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tCustomerBasedMobileDetailProductData;

public class tCustomerBasedMobileDetailProductDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_NAME = new clsHardCode().txtTable_tCustomerBasedMobileDetailProduct;
	
	// Contacts Table Columns names
	public tCustomerBasedMobileDetailProductDA(SQLiteDatabase db) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
										+ dt.Property_intTrCustomerIdDetailProduct + " TEXT PRIMARY KEY,"
										+ dt.Property_intTrCustomerIdDetail + " TEXT NULL,"
										+ dt.Property_txtProductBrandCode + " TEXT NULL,"
										+ dt.Property_txtProductBrandName + " TEXT NULL,"
										+ dt.Property_txtProductBrandQty + " TEXT NULL,"
										+ dt.Property_bitActive + " TEXT NULL,"
										+ dt.Property_dtInserted + " TEXT NULL,"
										+ dt.Property_dtUpdated + " TEXT NULL,"
										+ dt.Property_txtInsertedBy + " TEXT NULL,"
										+ dt.Property_txtUpdatedBy + " TEXT NULL" + ")";
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
	public void SaveDatatCustomerBasedMobileDetailProductData(SQLiteDatabase db,tCustomerBasedMobileDetailProductData data) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		db.execSQL("INSERT OR REPLACE into "+TABLE_NAME+" ("+dt.Property_ALL+") "+
				   "values('"	+String.valueOf(data.get_intTrCustomerIdDetailProduct())+"','"
								+String.valueOf(data.get_intTrCustomerIdDetail())+"','"
								+String.valueOf(data.get_txtProductBrandCode())+"','"
								+String.valueOf(data.get_txtProductBrandName())+"','"
								+String.valueOf(data.get_txtProductBrandQty())+"','"
								+String.valueOf(data.get_bitActive())+"','"
								+String.valueOf(data.get_dtInserted())+"','"
								+String.valueOf(data.get_dtUpdated())+"','"
								+String.valueOf(data.get_txtInsertedBy())+"','"
								+String.valueOf(data.get_txtUpdatedBy())+"')"
					);
	}
	
	// Getting single contact
	public tCustomerBasedMobileDetailProductData getData(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String[] tableColumns = new String[] { 
												dt.Property_intTrCustomerIdDetailProduct,
												dt.Property_intTrCustomerIdDetail,
												dt.Property_txtProductBrandCode, 
												dt.Property_txtProductBrandName,
												dt.Property_txtProductBrandQty,
												dt.Property_bitActive,
												dt.Property_dtInserted,
												dt.Property_dtUpdated,
												dt.Property_txtInsertedBy,
												dt.Property_txtUpdatedBy
											};
		String whereClause = dt.Property_intTrCustomerIdDetailProduct + "=?";
		String[] whereArgs = new String[] {
											String.valueOf(id)
										  };
		String groupBy = null;
		String havingBy = null;
		String orderBy = dt.Property_intTrCustomerIdDetailProduct;
						
		Cursor cursor = db.query(TABLE_NAME,
								 tableColumns,
								 whereClause,
								 whereArgs, 
								 groupBy, 
								 havingBy, 
								 orderBy);
		if (cursor != null)
			cursor.moveToFirst();
			tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
			contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
			contact.set_intTrCustomerIdDetail(cursor.getString(1));
			contact.set_txtProductBrandCode(cursor.getString(2));
			contact.set_txtProductBrandName(cursor.getString(3));
			contact.set_txtProductBrandQty(cursor.getString(4));
			contact.set_bitActive(cursor.getString(5));
			contact.set_dtInserted(cursor.getString(6));
			contact.set_dtUpdated(cursor.getString(7));
			contact.set_txtInsertedBy(cursor.getString(8));
			contact.set_txtUpdatedBy(cursor.getString(9));
			// return contact
			cursor.close();
			return contact;
	}

    public List<tCustomerBasedMobileDetailProductData> getAllDataByCustomerDetailId(SQLiteDatabase db,String id) {
        tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
        List<tCustomerBasedMobileDetailProductData> contactList = new ArrayList<tCustomerBasedMobileDetailProductData>();
        String[] tableColumns = new String[] {
                dt.Property_intTrCustomerIdDetailProduct,
                dt.Property_intTrCustomerIdDetail,
                dt.Property_txtProductBrandCode,
                dt.Property_txtProductBrandName,
                dt.Property_txtProductBrandQty,
                dt.Property_bitActive,
                dt.Property_dtInserted,
                dt.Property_dtUpdated,
                dt.Property_txtInsertedBy,
                dt.Property_txtUpdatedBy
        };
        String whereClause = dt.Property_intTrCustomerIdDetail + "=?";
        String[] whereArgs = new String[] {
                String.valueOf(id)
        };
        String groupBy = null;
        String havingBy = null;
        String orderBy = dt.Property_intTrCustomerIdDetailProduct;

        Cursor cursor = db.query(TABLE_NAME,
                tableColumns,
                whereClause,
                whereArgs,
                groupBy,
                havingBy,
                orderBy);

        if (cursor.moveToFirst()) {
            do {
                tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
                contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
                contact.set_intTrCustomerIdDetail(cursor.getString(1));
                contact.set_txtProductBrandCode(cursor.getString(2));
                contact.set_txtProductBrandName(cursor.getString(3));
                contact.set_txtProductBrandQty(cursor.getString(4));
                contact.set_bitActive(cursor.getString(5));
                contact.set_dtInserted(cursor.getString(6));
                contact.set_dtUpdated(cursor.getString(7));
                contact.set_txtInsertedBy(cursor.getString(8));
                contact.set_txtUpdatedBy(cursor.getString(9));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
	
	// Getting All Contacts
	public List<tCustomerBasedMobileDetailProductData> getAllData(SQLiteDatabase db) {
		List<tCustomerBasedMobileDetailProductData> contactList = new ArrayList<tCustomerBasedMobileDetailProductData>();
		// Select All Query
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
				contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
				contact.set_intTrCustomerIdDetail(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
				contact.set_bitActive(cursor.getString(4));
				contact.set_dtInserted(cursor.getString(5));
				contact.set_dtUpdated(cursor.getString(6));
				contact.set_txtInsertedBy(cursor.getString(7));
				contact.set_txtUpdatedBy(cursor.getString(8));
				contactList.add(contact);
				} while (cursor.moveToNext());
		}
			cursor.close();
			// return contact list
			return contactList;
	}
	
	public List<tCustomerBasedMobileDetailProductData> getPushData(SQLiteDatabase db) {
		List<tCustomerBasedMobileDetailProductData> contactList = null;
		// Select All Query
		tCustomerBasedMobileDetailProductData dt=new tCustomerBasedMobileDetailProductData();
		String selectQuery = "SELECT  "+dt.Property_ALL+" FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			contactList=new ArrayList<tCustomerBasedMobileDetailProductData>();
			do {
				tCustomerBasedMobileDetailProductData contact = new tCustomerBasedMobileDetailProductData();
				contact.set_intTrCustomerIdDetailProduct(cursor.getString(0));
				contact.set_intTrCustomerIdDetail(cursor.getString(1));
				contact.set_txtProductBrandCode(cursor.getString(2));
				contact.set_txtProductBrandName(cursor.getString(3));
				contact.set_bitActive(cursor.getString(4));
				contact.set_dtInserted(cursor.getString(5));
				contact.set_dtUpdated(cursor.getString(6));
				contact.set_txtInsertedBy(cursor.getString(7));
				contact.set_txtUpdatedBy(cursor.getString(8));
                contactList.add(contact);
				} while (cursor.moveToNext());
		}
			cursor.close();
			// return contact list
			return contactList;
	}
	
	public void deleteByID(SQLiteDatabase db,String id) {		
		tCustomerBasedMobileDetailProductData dt = new tCustomerBasedMobileDetailProductData();
		String whereClause =dt.Property_intTrCustomerIdDetailProduct + " = ?";
		String[] whereArgs = new String[] {
											String.valueOf(id)
										  };
					
		db.delete(TABLE_NAME, whereClause,whereArgs);
	}

	public void deleteByIDDetail(SQLiteDatabase db,String id) {
		tCustomerBasedMobileDetailProductData dt = new tCustomerBasedMobileDetailProductData();
		String whereClause =dt.Property_intTrCustomerIdDetail + " = ?";
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
	
}
