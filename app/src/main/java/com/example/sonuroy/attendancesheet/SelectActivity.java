package com.example.sonuroy.attendancesheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        b1 = (Button) findViewById(R.id.button2);
     }

    public void onattendenceClicked(View view) {
        try {
            startActivity(new Intent(this, GetClass.class));
           // finish();
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}

