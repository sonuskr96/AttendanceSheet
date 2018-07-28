package com.example.sonuroy.attendancesheet;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SONU ROY on 30-06-2017.
 */

public class DBAdapter extends Activity {
    private static final String DATABASE_TABLE1 = "teacher_data";
    private static final String DATABASE_TABLE2 = "student_data";
    private static final String DATABASE_TABLE3 = "Attendance_data";
    private static final String DATABASE_TABLE4 = "class_data";

    public static final String KEY_TEACHERNAME = "t_name";
    public static final String KEY_TEACHERPASSWORD = "t_pass";
    public static final String KEY_STUDENT_NAME = "stud_name";
    public static final String KEY_STUDENT_ROLL = "stud_roll";
    public static final String KEY_SSG = "ssg_id";
    public static final String KEY_SUB="sub_name";
    public static final String SEM_GROUP ="sem_group_yr";
    public static final String KEY_FOREIGN="ssg";

    SQLiteDatabase liteDatabase;
    DBHelper dbHelper;
    Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }
    public DBAdapter open() throws SQLException {
        dbHelper = new DBHelper(context);
       liteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        dbHelper.close();
    }

    public long register(String user, String pw) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_TEACHERNAME, user);
        initialValues.put(KEY_TEACHERPASSWORD, pw);

        return liteDatabase.insert(DATABASE_TABLE1, null, initialValues);
    }
    public boolean Login(String username, String password) throws SQLException {
        Cursor mCursor = liteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE1
                + " WHERE t_name=? AND t_pass=?", new String[] { username, password });
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                mCursor.close();
                return true;
            }
        }
        return false;
    }
    public long addCourse(String ssg, String subject, String sem_group){

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SSG, ssg);
        initialValues.put(KEY_SUB, subject);
        initialValues.put(SEM_GROUP,sem_group);
        return liteDatabase.insert(DATABASE_TABLE4, null, initialValues);
    }

    public long addStudent(String studentname, String rollno, String ssg) {
        // TODO Auto-generated method stub
        ContentValues initialValues=new ContentValues();
        initialValues.put(KEY_STUDENT_NAME,studentname);
        initialValues.put(KEY_STUDENT_ROLL,rollno);
        initialValues.put(KEY_FOREIGN,ssg);
        return liteDatabase.insert(DATABASE_TABLE2,null,initialValues);
    }

    public long presentStudent(String studentname){
        ContentValues initialValues=new ContentValues();
        initialValues.put(KEY_STUDENT_NAME,studentname);
        return liteDatabase.insert(DATABASE_TABLE3,null,initialValues);
    }
    public ArrayList<String> getStudentsByClass(String  stud_info) {
        // Log.i("SKR", "GETTING STUDENTS by " +stud_class);

            ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor;
        try {
            cursor = liteDatabase.rawQuery("SELECT s.stud_name FROM student_data s, class_data c WHERE " +
                   " s.ssg = c.ssg_id AND s.ssg ='" + stud_info + "'",null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_NAME)));
                    //arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_ROLL)));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        return arrayList;
    }

    public ArrayList<String> getStudents() {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM student_data", null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_NAME)));
                //arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_ROLL)));
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}


