package com.example.sonuroy.attendancesheet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by SONU ROY on 01-07-2017.
 */

public class AttendanceList extends Activity {
    ListView list;
    DBAdapter dbAdapter;
    ArrayAdapter arrayAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_list);

        list = (ListView) findViewById(R.id.listOfStud);
        try {
            dbAdapter = new DBAdapter(this);
            dbAdapter.open();
            arrayAdapter = new CustomAdapter(this,GetClass.getStudentList);
            //Toast.makeText(this, ""+ GetClass.getStudentList.size(), Toast.LENGTH_SHORT).show();
            list.setAdapter(arrayAdapter);
        }catch (Exception e){
            Toast.makeText(this,"error:"+e,Toast.LENGTH_LONG).show();
        }
        dbAdapter.close();
    }
    //submit button
    public void onSaveClicked(View view) {
        try {
            Bundle b = new Bundle();
            b.putStringArrayList("data", CustomAdapter.list);
            intent = new Intent(this, PresentSheet.class);
            intent.putExtras(b);
            dialog();
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    public void dialog(){
        AlertDialog.Builder confermation=new AlertDialog.Builder(this);
        confermation.setCancelable(true);
        confermation.setTitle("Total Students : "+list.getCount());
        confermation.setIcon(R.drawable.attendence);
        confermation.setMessage("Present students : "+CustomAdapter.list.size());
        confermation.setPositiveButton("Conferm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
                finish();
            }
        });
        confermation.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        confermation.show();
    }
}


