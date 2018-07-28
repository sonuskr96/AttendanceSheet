 package com.example.sonuroy.attendancesheet;

 import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    EditText txtStudentname, txtRoll,txtssg;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        dbAdapter = new DBAdapter(this);
        txtStudentname = (EditText) findViewById(R.id.name);
        txtRoll = (EditText) findViewById(R.id.roll);
        txtssg = (EditText) findViewById(R.id.sssg);

    }
    public void onAddStudentClicked(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtStudentname.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(txtRoll.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(txtssg.getWindowToken(), 0);

        try {

            String studentname = txtStudentname.getText().toString();
            String rollno = txtRoll.getText().toString();
            String ssg = txtRoll.getText().toString();

            dbAdapter.open();
            long i=dbAdapter.addStudent(studentname,rollno,ssg);
            if (studentname.length() > 0 && rollno.length() > 0 && ssg.length()>0) {
                if (i != -1) {
                    Toast.makeText(this,"student added",Toast.LENGTH_LONG).show();
                    dbAdapter.close();
                    txtStudentname.getText().clear();
                    txtRoll.getText().clear();
                    txtssg.getText().clear();
                }else{
                    Toast.makeText(this, "Value not Inserted", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "some attribute is Empty", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            Toast.makeText(AddStudent.this, "Some problem occurred"+e, Toast.LENGTH_LONG).show();
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