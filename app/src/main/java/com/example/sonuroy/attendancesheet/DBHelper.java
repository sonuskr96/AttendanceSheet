package com.example.sonuroy.attendancesheet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SONU ROY on 30-06-2017.
 */

public class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "attendance.db";
        private static final int DATABASE_VERSION = 5;
        private static final String TAG = "STDAT";
        private static final String DATABASE_CREATE1 = " CREATE TABLE teacher_data" +
                "( t_name text not null, t_pass text primary key);";
        private static final String DATABASE_CREATE2 = " CREATE TABLE student_data " +
                "(stud_name text not null, stud_roll integer not null primary key,ssg integer not null,FOREIGN KEY(ssg) REFERENCES class_data(ssg_id));";
        private static final String DATABASE_CREATE3 = "CREATE TABLE attendance_data " +
                "( _id integer primary key autoincrement, stud_name text not null);";
        private static final String DATABASE_CREATE4 = "CREATE TABLE class_data " +
                "( ssg_id integer primary key, sub_name text not null,sem_group_yr text not null);";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqdb) {
        if(!sqdb.isReadOnly()){
            //enable foreign key
            sqdb.execSQL("PRAGMA foreign_keys=ON;");
        }
        sqdb.execSQL(DATABASE_CREATE1);
        sqdb.execSQL(DATABASE_CREATE2);
        sqdb.execSQL(DATABASE_CREATE3);
        sqdb.execSQL(DATABASE_CREATE4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqdb, int oldVersion, int newVersion) {

        Log.w(TAG,"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        sqdb.execSQL("DROP TABLE IF EXISTS teacher_data");
        sqdb.execSQL("DROP TABLE IF EXISTS student_data" );
        sqdb.execSQL("DROP TABLE IF EXISTS attendance_data");
        sqdb.execSQL("DROP TABLE IF EXISTS class_data");
        onCreate(sqdb);
    }
}
