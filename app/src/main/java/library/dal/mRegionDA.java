package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mRegionData;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mRegionDA {
    public mRegionDA(SQLiteDatabase db) {
        mRegionData dt = new mRegionData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_uuId + " TEXT PRIMARY KEY,"
                + dt.Property_txtRegionID + " TEXT NULL,"
                + dt.Property_txtRegionName + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mRegion;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mRegionData data) {
        mRegionData dt = new mRegionData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_uuId
                + "," + dt.Property_txtRegionID
                + ","+ dt.Property_txtRegionName
                + ") " + "values('"
                + String.valueOf(data.get_uuId()) + "','"
                + String.valueOf(data.get_txtRegionID()) + "','"
                + String.valueOf(data.get_txtRegionName()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mRegionData getData(SQLiteDatabase db, int id) {
        mRegionData dt = new mRegionData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_uuId
                        , dt.Property_txtRegionID
                        , dt.Property_txtRegionName},
                dt.Property_uuId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mRegionData contact = new mRegionData();
        if (cursor.getCount() > 0) {
            contact.set_uuId(cursor.getString(0));
            contact.set_txtRegionID(cursor.getString(1));
            contact.set_txtRegionName(cursor.getString(2));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }


    // Getting All Contacts
    public List<mRegionData> getAllData(SQLiteDatabase db) {
        List<mRegionData> contactList = new ArrayList<mRegionData>();
        // Select All Query
        mRegionData dt = new mRegionData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mRegionData contact = new mRegionData();
                contact.set_uuId(cursor.getString(0));
                contact.set_txtRegionID(cursor.getString(1));
                contact.set_txtRegionName(cursor.getString(2));
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
        mRegionData dt = new mRegionData();
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

    public List<mRegionData> GetRegionByBranch(SQLiteDatabase db, String branchId) {
        List<mRegionData> contactList = null;
        // Select All Query
        mRegionData dt=new mRegionData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_uuId +"='"+branchId+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mRegionData>();
            do {
                mRegionData contact = new mRegionData();
                contact.set_uuId(cursor.getString(0));
                contact.set_txtRegionID(cursor.getString(1));
                contact.set_txtRegionName(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
}
