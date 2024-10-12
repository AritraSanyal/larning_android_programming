package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
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

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.DATABASE_TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";


        // execute the sql query
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_QUERY = "DROP TABLE IF EXISTS " + Util.DATABASE_NAME;
        db.execSQL(DROP_QUERY);
        onCreate(db);

    }


    // CURD -- CREATE, UPDATE, READ, DELETE

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        db.insert(Util.DATABASE_NAME,null,values);
        db.close();
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();

        //select * form table_name
        String selectAll = "SELECT * FROM " + Util.DATABASE_TABLE_NAME;
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectAll,null);
        //
        if (cursor.moveToFirst()){

            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNo(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());

        }

        return contactList;

    }


    public int updateContact(@NonNull Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        // 1 for success and 0 for zero.
        return db.update(Util.DATABASE_TABLE_NAME, values, Util.KEY_ID + "?=", new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE_NAME, Util.KEY_ID + "?=", new String[]{String.valueOf(id)});
        db.close();
    }
}
