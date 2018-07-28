package com.example.sonuroy.attendancesheet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText name, pass;
    DBAdapter dbAdapter;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
        setContentView(R.layout.activity_log_in);
        dbAdapter=new DBAdapter(this);

        checkBox=(CheckBox)findViewById(R.id.id_checkBox);
        name=(EditText)findViewById(R.id.edit_text_name);
        pass=(EditText)findViewById(R.id.edit_text_password);
        onChecked();
    }
    public void onChecked() {
        try {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!isChecked) {
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    public void onLogInClicked(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(name.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pass.getWindowToken(), 0);

        String username = name.getText().toString();
        String password = pass.getText().toString();
        try {
            if (username.length() > 0 && password.length() > 0) {
                dbAdapter.open();
                if (dbAdapter.Login(username, password)) {
                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                    dbAdapter.close();
                    name.getText().clear();
                    pass.getText().clear();
                    Intent i_login = new Intent(this,SelectActivity.class);
                    startActivity(i_login);
                    //finish();
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Username or Password is left empty", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
                Toast.makeText(this, "Some problem occurred"+e,Toast.LENGTH_LONG).show();
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
