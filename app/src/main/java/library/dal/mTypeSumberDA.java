package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mTypeSumberData;

/**
 * Created by ASUS ZE on 10/11/2016.
 */

public class mTypeSumberDA {
    public mTypeSumberDA(SQLiteDatabase db) {
        mTypeSumberData dt = new mTypeSumberData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_uuId + " TEXT PRIMARY KEY,"
                + dt.Property_tipeSumberData + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mTypeSumber;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mTypeSumberData data) {
        mTypeSumberData dt = new mTypeSumberData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_uuId
                + "," + dt.Property_tipeSumberData
                + ") " + "values('"
                + String.valueOf(data.get_uuId()) + "','"
                + String.valueOf(data.get_tipeSumberData()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mTypeSumberData getData(SQLiteDatabase db, int id) {
        mTypeSumberData dt = new mTypeSumberData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_uuId
                        , dt.Property_tipeSumberData},
                dt.Property_uuId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mTypeSumberData contact = new mTypeSumberData();
        if (cursor.getCount() > 0) {
            contact.set_uuId(cursor.getString(0));
            contact.set_tipeSumberData(cursor.getString(1));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }


    // Getting All Contacts
    public List<mTypeSumberData> getAllData(SQLiteDatabase db) {
        List<mTypeSumberData> contactList = new ArrayList<mTypeSumberData>();
        // Select All Query
        mTypeSumberData dt = new mTypeSumberData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mTypeSumberData contact = new mTypeSumberData();
                contact.set_uuId(cursor.getString(0));
                contact.set_tipeSumberData(cursor.getString(1));
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
        mTypeSumberData dt = new mTypeSumberData();
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

    public List<mTypeSumberData> GetRegionByBranch(SQLiteDatabase db, String branchId) {
        List<mTypeSumberData> contactList = null;
        // Select All Query
        mTypeSumberData dt=new mTypeSumberData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_uuId +"='"+branchId+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mTypeSumberData>();
            do {
                mTypeSumberData contact = new mTypeSumberData();
                contact.set_uuId(cursor.getString(0));
                contact.set_tipeSumberData(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
}
