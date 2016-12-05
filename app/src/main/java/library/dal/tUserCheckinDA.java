package library.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tUserCheckinData;

/**
 * Created by ASUS ZE on 20/09/2016.
 */
public class tUserCheckinDA {

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tCheckinUser;

    // Contacts Table Columns names

    public tUserCheckinDA(SQLiteDatabase db) {
        tUserCheckinData dt=new tUserCheckinData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_UuId + " TEXT PRIMARY KEY,"
                + dt.Property_txtUserID + " TEXT NULL,"
                + dt.Property_txtUserName + " TEXT NULL,"
                + dt.Property_txtGType + " TEXT NULL,"
                + dt.Property_dtCheckin + " TEXT NULL,"
                + dt.Property_dtCheckOut + " TEXT NULL,"
                + dt.Property_txtRegionName + " TEXT NULL,"
                + dt.Property_txtTypeOutlet + " TEXT NULL,"
                + dt.Property_txtOutletId + " TEXT NULL,"
                + dt.Property_txtOutletName + " TEXT NULL,"
                + dt.Property_txtBranchName + " TEXT NULL,"
                + dt.Property_txtLong + " TEXT NULL,"
                + dt.Property_txtLat + " TEXT NULL,"
                + dt.Property_txtAcc + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_intFlag + " TEXT NULL,"
                + dt.Property_TxtAlamat + " TEXT NULL,"
                + dt.Property_TxtNamaPropinsi + " TEXT NULL,"
                + dt.Property_TxtNamaKabKota + " TEXT NULL)";
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
    public void SaveDatatUserCheckinData(SQLiteDatabase db,tUserCheckinData data) {

        tUserCheckinData dt=new tUserCheckinData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_UuId, String.valueOf(data.get_UuId()));
        cv.put(dt.Property_txtUserID, String.valueOf(data.get_txtUserID()));
        cv.put(dt.Property_txtUserName, String.valueOf(data.get_txtUserName()));
        cv.put(dt.Property_txtGType, String.valueOf(data.get_txtGType()));
        cv.put(dt.Property_dtCheckin, String.valueOf(data.getDtCheckin()));
        cv.put(dt.Property_dtCheckOut, String.valueOf(data.getDtCheckOut()));
        cv.put(dt.Property_txtRegionName, String.valueOf(data.get_txtRegionName()));
        cv.put(dt.Property_txtTypeOutlet, String.valueOf(data.get_txtTypeOutlet()));
        cv.put(dt.Property_txtOutletId, String.valueOf(data.get_txtOutletId()));
        cv.put(dt.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
        cv.put(dt.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
        cv.put(dt.Property_txtLong, String.valueOf(data.get_txtLong()));
        cv.put(dt.Property_txtLat, String.valueOf(data.get_txtLat()));
        cv.put(dt.Property_txtAcc, String.valueOf(data.get_txtAcc()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
        cv.put(dt.Property_intFlag, String.valueOf(data.get_intFlag()));
        cv.put(dt.Property_TxtAlamat, String.valueOf(data.get_TxtAlamat()));
        cv.put(dt.Property_TxtNamaPropinsi, String.valueOf(data.get_TxtNamaPropinsi()));
        cv.put(dt.Property_TxtNamaKabKota, String.valueOf(data.get_TxtNamaKabKota()));
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
        tUserCheckinData dt=new tUserCheckinData();
        db.execSQL("UPDATE "+TABLE_CONTACTS+" SET "+dt.Property_intSubmit+"=1 WHERE "+dt.Property_UuId+"="+IdData);
    }
    // Getting single contact
    public tUserCheckinData getData(SQLiteDatabase db,int id) {
        tUserCheckinData dt=new tUserCheckinData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_UuId
                        ,dt.Property_txtUserID
                        ,dt.Property_txtUserName
                        ,dt.Property_txtGType
                        ,dt.Property_dtCheckin
                        ,dt.Property_dtCheckOut
                        ,dt.Property_txtRegionName
                        ,dt.Property_txtTypeOutlet
                        ,dt.Property_txtOutletId
                        ,dt.Property_txtOutletName
                        ,dt.Property_txtBranchName
                        ,dt.Property_txtLong
                        ,dt.Property_txtLat
                        ,dt.Property_txtAcc
                        ,dt.Property_intSubmit
                        ,dt.Property_intSync
                        ,dt.Property_intFlag
                        ,dt.Property_TxtAlamat
                        ,dt.Property_TxtNamaPropinsi
                        ,dt.Property_TxtNamaKabKota}, dt.Property_UuId + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        tUserCheckinData contact = new tUserCheckinData();
        contact.set_UuId(cursor.getString(0));
        contact.set_txtUserID(cursor.getString(1));
        contact.set_txtUserName(cursor.getString(2));
        contact.set_txtGType(cursor.getString(3));
        contact.setDtCheckin(cursor.getString(4));
        contact.setDtCheckOut(cursor.getString(5));
        contact.set_txtRegionName(cursor.getString(6));
        contact.set_txtTypeOutlet(cursor.getString(7));
        contact.set_txtOutletId(cursor.getString(8));
        contact.set_txtOutletName(cursor.getString(9));
        contact.set_txtBranchName(cursor.getString(10));
        contact.set_txtLong(cursor.getString(11));
        contact.set_txtLat(cursor.getString(12));
        contact.set_txtAcc(cursor.getString(13));
        contact.set_intSubmit(cursor.getString(14));
        contact.set_intSync(cursor.getString(15));
        contact.set_intFlag(cursor.getString(16));
        contact.set_TxtAlamat(cursor.getString(17));
        contact.set_TxtNamaPropinsi(cursor.getString(18));
        contact.set_TxtNamaKabKota(cursor.getString(19));
        // return contact
        cursor.close();
        return contact;
    }

//    public tAbsenUserData getDataCheckInByOutletAndBranch(SQLiteDatabase db,String txtBranchCode, String txtOutletCode) {
//        // Select All Query
//        tAbsenUserData dt=new tAbsenUserData();
//        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_dtDateCheckOut+" in ('null','') AND "+dt.Property_txtBranchCode+"='"+txtBranchCode+"' AND "+dt.Property_txtOutletCode+"='"+txtOutletCode+"'";
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        tAbsenUserData contact = null;
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                contact=new tAbsenUserData();
//                contact.set_intId(cursor.getString(0));
//                contact.set_dtDateCheckIn(cursor.getString(1));
//                contact.set_intSubmit(cursor.getString(2));
//                contact.set_intSync(cursor.getString(3));
//                contact.set_txtAbsen(cursor.getString(4));
//                contact.set_txtAccuracy(cursor.getString(5));
//                contact.set_txtBranchCode(cursor.getString(6));
//                contact.set_txtBranchName(cursor.getString(7));
//                contact.set_txtLatitude(cursor.getString(8));
//                contact.set_txtLongitude(cursor.getString(9));
//                contact.set_txtOutletCode(cursor.getString(10));
//                contact.set_txtOutletName(cursor.getString(11));
//                contact.set_txtDeviceId(cursor.getString(12));
//                contact.set_txtUserId(cursor.getString(13));
//                contact.set_dtDateCheckOut(cursor.getString(14));
////					contact.set_txtImg1(cursor.getString(15));
////					contact.set_txtImg2(cursor.getString(16));
//                // Adding contact to list
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        // return contact list
//        return contact;
//    }

    public tUserCheckinData getDataCheckInActive(SQLiteDatabase db) {
        // Select All Query
        tUserCheckinData dt=new tUserCheckinData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" Where "+dt.Property_dtCheckOut+" in ('null','') ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        tUserCheckinData contact = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                contact.set_UuId(cursor.getString(0));
                contact.set_txtUserID(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_txtGType(cursor.getString(3));
                contact.setDtCheckin(cursor.getString(4));
                contact.setDtCheckOut(cursor.getString(5));
                contact.set_txtRegionName(cursor.getString(6));
                contact.set_txtTypeOutlet(cursor.getString(7));
                contact.set_txtOutletId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchName(cursor.getString(10));
                contact.set_txtLong(cursor.getString(11));
                contact.set_txtLat(cursor.getString(12));
                contact.set_txtAcc(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contact.set_intSync(cursor.getString(15));
                contact.set_intFlag(cursor.getString(16));
                contact.set_TxtAlamat(cursor.getString(17));
                contact.set_TxtNamaPropinsi(cursor.getString(18));
                contact.set_TxtNamaKabKota(cursor.getString(19));

                // Adding contact to list

            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contact;
    }

    // Getting All Contacts
    public List<tUserCheckinData> getAllData(SQLiteDatabase db) {
        List<tUserCheckinData> contactList = new ArrayList<tUserCheckinData>();
        // Select All Query
        tUserCheckinData dt=new tUserCheckinData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tUserCheckinData contact = new tUserCheckinData();
                contact.set_UuId(cursor.getString(0));
                contact.set_txtUserID(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_txtGType(cursor.getString(3));
                contact.setDtCheckin(cursor.getString(4));
                contact.setDtCheckOut(cursor.getString(5));
                contact.set_txtRegionName(cursor.getString(6));
                contact.set_txtTypeOutlet(cursor.getString(7));
                contact.set_txtOutletId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchName(cursor.getString(10));
                contact.set_txtLong(cursor.getString(11));
                contact.set_txtLat(cursor.getString(12));
                contact.set_txtAcc(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contact.set_intSync(cursor.getString(15));
                contact.set_intFlag(cursor.getString(16));
                contact.set_TxtAlamat(cursor.getString(17));
                contact.set_TxtNamaPropinsi(cursor.getString(18));
                contact.set_TxtNamaKabKota(cursor.getString(19));

                // Adding contact to list

                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<tUserCheckinData> getAllDataToPushData(SQLiteDatabase db) {
        List<tUserCheckinData> contactList = null;
        // Select All Query
        tUserCheckinData dt=new tUserCheckinData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_intSync+"=0 And "+dt.Property_intSubmit+"=1";
//                " And "+dt.Property_intFlag+"=0";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tUserCheckinData>();
            do {
                tUserCheckinData contact = new tUserCheckinData();

                contact.set_UuId(cursor.getString(0));
                contact.set_txtUserID(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_txtGType(cursor.getString(3));
                contact.setDtCheckin(cursor.getString(4));
                contact.setDtCheckOut(cursor.getString(5));
                contact.set_txtRegionName(cursor.getString(6));
                contact.set_txtTypeOutlet(cursor.getString(7));
                contact.set_txtOutletId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchName(cursor.getString(10));
                contact.set_txtLong(cursor.getString(11));
                contact.set_txtLat(cursor.getString(12));
                contact.set_txtAcc(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contact.set_intSync(cursor.getString(15));
                contact.set_intFlag(cursor.getString(16));
                contact.set_TxtAlamat(cursor.getString(17));
                contact.set_TxtNamaPropinsi(cursor.getString(18));
                contact.set_TxtNamaKabKota(cursor.getString(19));


                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<tUserCheckinData> getAllDataActive(SQLiteDatabase db) {
        List<tUserCheckinData> contactList = null;
        // Select All Query
        tUserCheckinData dt=new tUserCheckinData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE " + dt.Property_intSubmit+"=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tUserCheckinData>();
            do {
                tUserCheckinData contact = new tUserCheckinData();

                contact.set_UuId(cursor.getString(0));
                contact.set_txtUserID(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_txtGType(cursor.getString(3));
                contact.setDtCheckin(cursor.getString(4));
                contact.setDtCheckOut(cursor.getString(5));
                contact.set_txtRegionName(cursor.getString(6));
                contact.set_txtTypeOutlet(cursor.getString(7));
                contact.set_txtOutletId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchName(cursor.getString(10));
                contact.set_txtLong(cursor.getString(11));
                contact.set_txtLat(cursor.getString(12));
                contact.set_txtAcc(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contact.set_intSync(cursor.getString(15));
                contact.set_intFlag(cursor.getString(16));
                contact.set_TxtAlamat(cursor.getString(17));
                contact.set_TxtNamaPropinsi(cursor.getString(18));
                contact.set_TxtNamaKabKota(cursor.getString(19));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<tUserCheckinData> getAllDataByOutletCode(SQLiteDatabase db,String OutletCode) {
        List<tUserCheckinData> contactList = null;
        // Select All Query
        tUserCheckinData dt=new tUserCheckinData();
        String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtOutletName+"='"+OutletCode+"' AND "+dt.Property_intSubmit+"=1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<tUserCheckinData>();
            do {
                tUserCheckinData contact = new tUserCheckinData();

                contact.set_UuId(cursor.getString(0));
                contact.set_txtUserID(cursor.getString(1));
                contact.set_txtUserName(cursor.getString(2));
                contact.set_txtGType(cursor.getString(3));
                contact.setDtCheckin(cursor.getString(4));
                contact.setDtCheckOut(cursor.getString(5));
                contact.set_txtRegionName(cursor.getString(6));
                contact.set_txtTypeOutlet(cursor.getString(7));
                contact.set_txtOutletId(cursor.getString(8));
                contact.set_txtOutletName(cursor.getString(9));
                contact.set_txtBranchName(cursor.getString(10));
                contact.set_txtLong(cursor.getString(11));
                contact.set_txtLat(cursor.getString(12));
                contact.set_txtAcc(cursor.getString(13));
                contact.set_intSubmit(cursor.getString(14));
                contact.set_intSync(cursor.getString(15));
                contact.set_intFlag(cursor.getString(16));
                contact.set_TxtAlamat(cursor.getString(17));
                contact.set_TxtNamaPropinsi(cursor.getString(18));
                contact.set_TxtNamaKabKota(cursor.getString(19));

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
        tUserCheckinData dt = new tUserCheckinData();
        db.delete(TABLE_CONTACTS, dt.Property_UuId + " = ?",
                new String[] { String.valueOf(id) });
    }

    public void DeleteAllDAta(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }
    // Getting contacts Count
    public int getContactsCountSubmit(SQLiteDatabase db) {
        tUserCheckinData dt = new tUserCheckinData();
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
