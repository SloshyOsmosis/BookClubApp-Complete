package com.example.bookclubapp_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookClubDB.db";
    private static final int DATABASE_VERSION = 1;

    //User Table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";

    //Library Table
    private static final String TABLE_LIBRARY = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_GENRE = "book_GENRE";
    private static final String COLUMN_ISBN = "book_isbn";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CreateUserTable = "CREATE TABLE " + TABLE_USERS +
                " (" + COLUMN_USERNAME +
                " TEXT PRIMARY KEY, " +
                COLUMN_USER_PASSWORD + " TEXT)";
        String CreateLibraryTable = "CREATE TABLE " + TABLE_LIBRARY +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_ISBN + " TEXT);";

        sqLiteDatabase.execSQL(CreateUserTable);
        sqLiteDatabase.execSQL(CreateLibraryTable);
    }

    public void addBook(String title, String author, String genre, String ISBN){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_GENRE, genre);
        cv.put(COLUMN_ISBN, ISBN);

        long result = myDB.insert(TABLE_LIBRARY, null, cv);
        if(result == -1){
            Toast.makeText(context, "Could not add book.",Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context,"Book added Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRARY);
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_USER_PASSWORD, password);
        long result = myDB.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkUserName(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from " + TABLE_USERS + " where username=?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from " + TABLE_USERS +  " where username=? and password=?", new String[]{username,password});
        return cursor.getCount() > 0;
    }

    public boolean updatepassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_PASSWORD, password);
        long result = myDB.update(TABLE_USERS, contentValues, COLUMN_USERNAME + "=?", new String[] {username});
        return result != -1;
    }

}
