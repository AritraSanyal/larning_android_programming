package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHadler extends SQLiteOpenHelper {


    public DatabaseHadler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.DATABASE_TABLE + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.DATABASE_TABLE;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }


    // CURD -- CREATE, UPDATE, READ, DELETE--- REALATED TO CONTACTS NOT THE DB OR TABLE

    // CREATE
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        db.insert(Util.DATABASE_TABLE, null, values);
        db.close();
    }



    //READ
    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT * FROM table_name
        String selectAll = "SELECT * FROM " + Util.DATABASE_TABLE;
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectAll,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNo(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;

    }



    public int updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        return db.update(Util.DATABASE_TABLE, values, Util.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});

    }
    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE, Util.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // GET CONTACT BY ID
    public Contact getContactByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Contact contact = null;
        String selectQuery = "SELECT * FROM " + Util.DATABASE_TABLE + " WHERE " + Util.KEY_ID + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});


        if (cursor != null && cursor.moveToFirst()) {
            contact = new Contact();
            contact.setId(id);
            contact.setName(cursor.getString(1));
            contact.setPhoneNo(cursor.getString(2));
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return contact;


    }




}
