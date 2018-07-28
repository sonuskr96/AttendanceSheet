package com.example.sonuroy.attendancesheet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name,pass;
    CheckBox checkbox;
    DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            dbAdapter = new DBAdapter(this);
            name = (EditText) findViewById(R.id.edit_text_name);
            pass = (EditText) findViewById(R.id.edit_text_password);
             checkbox = (CheckBox) findViewById(R.id.checkBox);
            //to hide and show password through check box ;
            onChecked();
    }
    public void onChecked() {
        try {
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!isChecked) {
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }


    public void onRegisterClicked(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(name.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pass.getWindowToken(), 0);

            String getname = name.getText().toString();
            String getpass = pass.getText().toString();
        try {
            if (getname.length() > 0 && getpass.length() > 0) {
              dbAdapter.open();
             long i = dbAdapter.register( getname, getpass);
                if (i != -1) {
                    Toast.makeText(this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                    name.getText().clear();
                    pass.getText().clear();
                    dbAdapter.close();
                }else{
                    Toast.makeText(this, "Please Enter a valid ID", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this,"Enter name and password",Toast.LENGTH_SHORT).show();
            }
         } catch (SQLException e) {
            Toast.makeText(MainActivity.this, "Some problem occurred"+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem i) {
        try {
            int id = i.getItemId();
            switch (id) {
                case R.id.logitem:
                    Intent intent = new Intent(this, LogInActivity.class);
                    startActivity(intent);
                    break;
                case R.id.admin_m:
                    //for next page
                    startActivity(new Intent(this, CourseActivity.class));
                    break;
                case R.id.about_m:
                    startActivity(new Intent(this, ViewActivity.class));

            }
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
        return true;
    }
    @Override
    public void onBackPressed() {
       exitAlert();
    }
    public void exitAlert(){
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
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }
}
