package com.student.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sande on 10/1/2016.
 */

public class MyStudentdatabase extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE_NAME = "students";
    public static final String STUDENT_COLUMN_ID = "id";
    public static final String STUDENT_FIRSTNAME = "firstname";
    public static final String STUDENT_LASTNAME = "lastname";
    public static final String STUDENT_USERNAME = "username";
    public static final String STUDENT_PASSWORD = "password";
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "create table students (id integer primary key," +
            "firstname text not null, lastname text not null, username text not null, password text not null)";

    public MyStudentdatabase(Context context) {
        super(context, "StudentDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS "+TABLE_CREATE;
        db.execSQL(query);
        this.onCreate(db);

    }

    public void insertStudent(Students student)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_FIRSTNAME, student.getFname());
        contentValues.put(STUDENT_LASTNAME, student.getLname());
        contentValues.put(STUDENT_USERNAME, student.getUname().toLowerCase());
        contentValues.put(STUDENT_PASSWORD, student.getPass());
        db.insert(STUDENT_TABLE_NAME, null, contentValues);
        db.close();
    }

    public String searchString(String uname)
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select username, password from students where username = '"+uname+"'", null);
        //Cursor cursor = db.query(STUDENT_TABLE_NAME, null, " username=?", new String[]{uname}, null, null, null);

        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(STUDENT_PASSWORD));
        cursor.close();
        return password;
    }

    public String getFullname(String uname)
    {

        db = this.getReadableDatabase();
        Cursor cursor = db.query(STUDENT_TABLE_NAME, null," username=?", new String[]{uname}, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        String fname = cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_FIRSTNAME));
        String lname = cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_LASTNAME));
        return fname +" "+lname;
    }


    public ArrayList<Students> getAllStudents()
    {
        db = this.getReadableDatabase();
        ArrayList<Students> allstudents = new ArrayList<Students>();
        Cursor cursor = db.query(STUDENT_TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext())
        {
            Students students = new Students();
            students.setFname(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_FIRSTNAME)));
            students.setLname(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_LASTNAME)));
            students.setUname(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_USERNAME)));
            students.setPass(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_PASSWORD)));

            allstudents.add(students);
        }

        return allstudents;
    }
}
