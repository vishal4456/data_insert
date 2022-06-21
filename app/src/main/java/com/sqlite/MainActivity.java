package com.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteHelper db;
    Button insertBtn;
    EditText rnoEdt,nameEdt,perEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new SQLiteHelper(this);
        insertBtn.setOnClickListener(view->
        {
            if(validData())
            {
                Integer rno = Integer.parseInt(rnoEdt.getText().toString());
                String name = nameEdt.getText().toString().trim();
                Float per = Float.parseFloat(perEdt.getText().toString());
                if (db.insertData(rno,name,per))
                {
                    Toast.makeText(this, "Record is stored",Toast.LENGTH_LONG).show();
                    rnoEdt.setText("");
                    nameEdt.setText("");
                    perEdt.setText("");
                    rnoEdt.requestFocus();
                }
                else
                {
                    Toast.makeText(this,"Record is does not save", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private boolean validData()
    {
        String rno = rnoEdt.getText().toString();
        String name = nameEdt.getText().toString();
        String per = perEdt.getText().toString();

        if (rno.isEmpty())
        {
            rnoEdt.setText("");
            rnoEdt.setError("Please enter the rno");
            rnoEdt.requestFocus();
            return false;
        }
        else if (name.trim().isEmpty())
        {
            nameEdt.setText("");
            nameEdt.setError("Please enter the Name");
            nameEdt.requestFocus();
            return false;
        }
        else if (per.isEmpty())
        {
            perEdt.setText("");
            perEdt.setError("Please enter the Percantage");
            perEdt.requestFocus();
            return false;
        }
        return true;
    }
    private void initView()
    {
        insertBtn = findViewById(R.id.insert_Btn);
        rnoEdt = findViewById(R.id.rnoEdt);
        nameEdt = findViewById(R.id.nameEdt);
        perEdt = findViewById(R.id.perEdt);
    }
}