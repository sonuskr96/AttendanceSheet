package com.example.sonuroy.attendancesheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PresentSheet extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_sheet);
        try {

            ArrayList<String> list;
            list = getIntent().getStringArrayListExtra("data");
            listView = (ListView) findViewById(R.id.presentlist);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list);
            listView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
    /*public void exitAlert() {
        try {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);//this will resist to click a dialog button;
            alert.setTitle("choose your option");
            alert.setIcon(R.drawable.attendence);
            alert.setMessage("Do you wanna exit");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    PresentSheet.super.onBackPressed();
                    finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.show();
        } catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_LONG).show();
        }
    }*/
}
