package com.example.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name;
    EditText email;
    EditText number;
    EditText des;
    EditText deg;
    EditText skill;
    EditText id;
    Button but1,but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.e1);
        email = (EditText) findViewById(R.id.e2);
        number = (EditText) findViewById(R.id.e3);
        des = (EditText) findViewById(R.id.e4);
        deg = (EditText) findViewById(R.id.e5);
        skill = (EditText) findViewById(R.id.e6);
        id = (EditText) findViewById(R.id.e7);
        but1 = (Button) findViewById(R.id.b1);
        but2 = (Button) findViewById(R.id.b2);
        AddData();
        viewAll();

    }
    public void AddData() {
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd=id.getText().toString();
                String nameE=name.getText().toString();
                String emailL=email.getText().toString();
                String nNumber=number.getText().toString();
                String deEs=des.getText().toString();
                String deEg=deg.getText().toString();
                String skills=skill.getText().toString();
                boolean isInserted = myDb.insertData(idd,nameE,emailL,nNumber,deEs,deEg,skills);

                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "INSERTED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this, "INSERTION FAILED", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void viewAll()
    {
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDb.getData();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"ERROR, DATA DOESN'T EXIST",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                    buffer.append("Number :"+res.getString(3)+"\n");
                    buffer.append("Description :"+res.getString(4)+"\n");
                    buffer.append("Degree :"+res.getString(5)+"\n");
                    buffer.append("Skills :"+res.getString(6)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("DATA: ");
                builder.setMessage(buffer.toString());
                builder.show();
            } });
            }
}