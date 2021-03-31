package com.example.mavshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DataBaseAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataBaseAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataBaseAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataBaseAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public boolean checkifUserExists(String username,String password)
    {
        Cursor mCursor = mDb.rawQuery("SELECT * FROM User"+ " WHERE EMAIL_ID=? AND PASSWORD=?",
                new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                mCursor.close();
                return true;
            }
        }
        return false;

    }

    public void insertUser(String firstName_str, String lastName_str, String emailid_str, String address_str, String phone_str, String password_str, String confirmpassword_str) {
        ContentValues c= new ContentValues();
        c.put("Email_Id",emailid_str);
        c.put("First_Name",firstName_str);
        c.put("Last_Name",lastName_str);
        c.put("Address",address_str);
        c.put("Phone_Number",phone_str);
        c.put("Password",password_str);

        //String str = "INSERT INTO User (Email_Id,First_Name,Last_Name,Address,Phone_Number,Password) VALUES('\"+NameHolder+\"', '\"+NumberHolder+\"');";
        long id = mDb.insert("User",null,c);
      //  System.out.println("****"+id);
    }
}
