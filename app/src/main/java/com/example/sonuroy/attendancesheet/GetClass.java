package com.example.sonuroy.attendancesheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class GetClass extends AppCompatActivity {
    EditText essg;
    DBAdapter dbAdapter;
    static ArrayList<String> getStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_class);

        essg=(EditText)findViewById(R.id.getsclass);

        dbAdapter=new DBAdapter(this);

    }
    public void onClassbtnClicked(View view) {
        String stud_list = essg.getText().toString();
         try {
            dbAdapter.open();
            getStudentList=new ArrayList<>();
            //getStudentList=dbAdapter.getStudentsByClass(stud_list);
             getStudentList=dbAdapter.getStudents();
            dbAdapter.close();
            //Toast.makeText(this, ""+getStudentList.size(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, AttendanceList.class);
            startActivity(i);
             finish();
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
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
