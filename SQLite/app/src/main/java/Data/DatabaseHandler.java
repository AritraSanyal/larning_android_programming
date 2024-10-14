// NEW CODE

package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.DATABASE_TABLE + "("
                    + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                    + Util.KEY_NAME + " TEXT, "
                    + Util.KEY_PHONE_NUMBER + " TEXT" + ")";
            db.execSQL(CREATE_CONTACT_TABLE);
            Log.d("DatabaseHandler", "Table created: " + Util.DATABASE_TABLE);
        } catch (Exception e) {
            Log.e("DatabaseHandler", "Error creating table: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.DATABASE_TABLE;
        db.execSQL(DROP_TABLE);
        onCreate(db); // Recreate table on upgrade
    }

    // Create
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        db.insert(Util.DATABASE_TABLE, null, values);
        db.close();
    }

    // Read
    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.DATABASE_TABLE;
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0)); // Use getInt for efficiency
                contact.setName(cursor.getString(1));
                contact.setPhoneNo(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close(); // Always close cursor to avoid memory leaks
        db.close(); // Close database
        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNo());
        int rowsAffected = db.update(Util.DATABASE_TABLE, values, Util.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close(); // Close database
        return rowsAffected; // Return the number of rows affected
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE, Util.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close(); // Close database
    }

    // Get contact by ID
    public Contact getContactByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contact contact = null;
        String selectQuery = "SELECT * FROM " + Util.DATABASE_TABLE + " WHERE " + Util.KEY_ID + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            contact = new Contact();
            contact.setId(cursor.getInt(0)); // Use getInt for efficiency
            contact.setName(cursor.getString(1));
            contact.setPhoneNo(cursor.getString(2));
        }

        if (cursor != null) {
            cursor.close(); // Close cursor
        }
        db.close(); // Close database

        return contact;
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE, null, null);
        db.close(); // Close database
    }
}










