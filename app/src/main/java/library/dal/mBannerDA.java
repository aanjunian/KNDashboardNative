package library.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mBannerData;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mBannerDA {
    public mBannerDA(SQLiteDatabase db) {
        mBannerData dt = new mBannerData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_uuId + " TEXT PRIMARY KEY,"
                + dt.Property_IntBannerID + " TEXT NULL,"
                + dt.Property_TxtFileName + " TEXT NULL,"
                + dt.Property_TxtName + " TEXT NULL,"
                + dt.Property_TxtPathActual + " TEXT NULL,"
                + dt.Property_TxtPathThumbnail + " TEXT NULL,"
                + dt.Property_txtDesc + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mBanner;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mBannerData data) {
        mBannerData dt = new mBannerData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_uuId
                + "," + dt.Property_IntBannerID
                + ","+ dt.Property_TxtFileName
                + ","+ dt.Property_TxtName
                + ","+ dt.Property_TxtPathActual
                + ","+ dt.Property_TxtPathThumbnail
                + ","+ dt.Property_txtDesc
                + ") " + "values('"
                + String.valueOf(data.get_uuId()) + "','"
                + String.valueOf(data.get_IntBannerID()) + "','"
                + String.valueOf(data.get_TxtFileName()) + "','"
                + String.valueOf(data.get_TxtName()) + "','"
                + String.valueOf(data.get_TxtPathActual()) + "','"
                + String.valueOf(data.get_TxtPathThumbnail()) + "','"
                + String.valueOf(data.get_txtDesc()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mBannerData getData(SQLiteDatabase db, int id) {
        mBannerData dt = new mBannerData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_uuId
                        , dt.Property_IntBannerID
                        , dt.Property_TxtFileName
                        , dt.Property_TxtName
                        , dt.Property_TxtPathActual
                        , dt.Property_TxtPathThumbnail
                        , dt.Property_txtDesc},
                dt.Property_uuId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mBannerData contact = new mBannerData();
        if (cursor.getCount() > 0) {
            contact.set_uuId(cursor.getString(0));
            contact.set_IntBannerID(cursor.getString(1));
            contact.set_TxtFileName(cursor.getString(2));
            contact.set_TxtName(cursor.getString(3));
            contact.set_TxtPathActual(cursor.getString(4));
            contact.set_TxtPathThumbnail(cursor.getString(5));
            contact.set_txtDesc(cursor.getString(6));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }


    // Getting All Contacts
    public List<mBannerData> getAllData(SQLiteDatabase db) {
        List<mBannerData> contactList = new ArrayList<mBannerData>();
        // Select All Query
        mBannerData dt = new mBannerData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mBannerData contact = new mBannerData();
                contact.set_uuId(cursor.getString(0));
                contact.set_IntBannerID(cursor.getString(1));
                contact.set_TxtFileName(cursor.getString(2));
                contact.set_TxtName(cursor.getString(3));
                contact.set_TxtPathActual(cursor.getString(4));
                contact.set_TxtPathThumbnail(cursor.getString(5));
                contact.set_txtDesc(cursor.getString(6));
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
        mBannerData dt = new mBannerData();
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

    public List<mBannerData> GetRegionByBranch(SQLiteDatabase db, String bannerId) {
        List<mBannerData> contactList = null;
        // Select All Query
        mBannerData dt=new mBannerData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_IntBannerID +"='"+bannerId+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mBannerData>();
            do {
                mBannerData contact = new mBannerData();
                contact.set_uuId(cursor.getString(0));
                contact.set_IntBannerID(cursor.getString(1));
                contact.set_TxtFileName(cursor.getString(2));
                contact.set_TxtName(cursor.getString(3));
                contact.set_TxtPathActual(cursor.getString(4));
                contact.set_TxtPathThumbnail(cursor.getString(5));
                contact.set_txtDesc(cursor.getString(6));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<mBannerData> GetBranchByRegion(SQLiteDatabase db, String name) {
        List<mBannerData> contactList = null;
        // Select All Query
        mBannerData dt=new mBannerData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_TxtName +"='"+name+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<mBannerData>();
            do {
                mBannerData contact = new mBannerData();
                contact.set_uuId(cursor.getString(0));
                contact.set_IntBannerID(cursor.getString(1));
                contact.set_TxtFileName(cursor.getString(2));
                contact.set_TxtName(cursor.getString(3));
                contact.set_TxtPathActual(cursor.getString(4));
                contact.set_TxtPathThumbnail(cursor.getString(5));
                contact.set_txtDesc(cursor.getString(6));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }
}
