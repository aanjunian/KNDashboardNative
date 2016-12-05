package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mBranchData;

/**
 * Created by ASUS ZE on 20/09/2016.
 */
public class mBranchDA {

    public mBranchDA(SQLiteDatabase db) {
        mBranchData dt = new mBranchData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_uuId + " TEXT PRIMARY KEY,"
                + dt.Property_txtBranchID + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_txtRegion + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mBranch;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mBranchData data) {
        mBranchData dt = new mBranchData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_uuId
                + "," + dt.Property_txtBranchID
                + ","+ dt.Property_txtBranchName
                + ","+ dt.Property_txtRegion
                + ") " + "values('"
                + String.valueOf(data.get_uuId()) + "','"
                + String.valueOf(data.get_txtBranchID()) + "','"
                + String.valueOf(data.get_txtBranchName()) + "','"
                + String.valueOf(data.get_txtRegion()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mBranchData getData(SQLiteDatabase db, int id) {
        mBranchData dt = new mBranchData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_uuId
                        , dt.Property_txtBranchID
                        , dt.Property_txtBranchName
                        , dt.Property_txtRegion},
                dt.Property_uuId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mBranchData contact = new mBranchData();
        if (cursor.getCount() > 0) {
            contact.set_uuId(cursor.getString(0));
            contact.set_txtBranchID(cursor.getString(1));
            contact.set_txtBranchName(cursor.getString(2));
            contact.set_txtRegion(cursor.getString(3));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }


    // Getting All Contacts
    public List<mBranchData> getAllData(SQLiteDatabase db) {
        List<mBranchData> contactList = new ArrayList<mBranchData>();
        // Select All Query
        mBranchData dt = new mBranchData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mBranchData contact = new mBranchData();
                contact.set_uuId(cursor.getString(0));
                contact.set_txtBranchID(cursor.getString(1));
                contact.set_txtBranchName(cursor.getString(2));
                contact.set_txtRegion(cursor.getString(3));
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
        mBranchData dt = new mBranchData();
        db.delete(TABLE_CONTACTS, dt.Property_uuId + " = ?",
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

    public List<mBranchData> GetRegionByBranch(SQLiteDatabase db, String branchId) {
        List<mBranchData> contactList = null;
        // Select All Query
        mBranchData dt=new mBranchData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtBranchID +"='"+branchId+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mBranchData>();
            do {
                mBranchData contact = new mBranchData();
                contact.set_uuId(cursor.getString(0));
                contact.set_txtBranchID(cursor.getString(1));
                contact.set_txtBranchName(cursor.getString(2));
                contact.set_txtRegion(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<mBranchData> GetBranchByRegion(SQLiteDatabase db, String region) {
        List<mBranchData> contactList = null;
        // Select All Query
        mBranchData dt=new mBranchData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtRegion +"='"+region+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mBranchData>();
            do {
                mBranchData contact = new mBranchData();
                contact.set_uuId(cursor.getString(0));
                contact.set_txtBranchID(cursor.getString(1));
                contact.set_txtBranchName(cursor.getString(2));
                contact.set_txtRegion(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
}
