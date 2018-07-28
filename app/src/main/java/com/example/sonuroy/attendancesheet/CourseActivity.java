package com.example.sonuroy.attendancesheet;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

          e1=(EditText)findViewById(R.id.esem_group_id);
          e2=(EditText)findViewById(R.id.esub_id);
          e3=(EditText)findViewById(R.id.essg_id);
        dbAdapter = new DBAdapter(this);
    }
    public void onSubmit(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(e1.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(e2.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(e3.getWindowToken(), 0);

        String sem_grp=e1.getText().toString();
        String subject=e2.getText().toString();
        String ssg=e3.getText().toString();
        try {
        if (sem_grp.length() > 0 && subject.length() > 0 && ssg.length()>0) {
            dbAdapter.open();
            long i = dbAdapter.addCourse( ssg, subject, sem_grp);
            if (i != -1) {
                Toast.makeText(this,"Record added",Toast.LENGTH_LONG).show();
                dbAdapter.close();
                e1.getText().clear();
                e2.getText().clear();
                e3.getText().clear();
                startActivity(new Intent(this,AddStudent.class));
            }else{
                Toast.makeText(this, "Value not Inserted", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "some attribute is Empty", Toast.LENGTH_SHORT).show();
        }
      }catch (SQLException e) {
        Toast.makeText(this, "Some problem occurred"+e, Toast.LENGTH_LONG).show();
       }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
