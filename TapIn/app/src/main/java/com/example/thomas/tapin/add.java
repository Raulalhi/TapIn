package com.example.thomas.tapin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Thomas on 21/04/2017.
 */

public class add extends AppCompatActivity
{
    EditText name;
    EditText email;
    EditText phone;
    EditText job;
    EditText wage;
    CheckBox parttime;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.eName1);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        job = (EditText)findViewById(R.id.job);
        wage = (EditText)findViewById(R.id.wage);
        parttime = (CheckBox)findViewById(R.id.parttime);
        next = (Button) findViewById(R.id.next);

        setTitle("Add Employee");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!String.valueOf(name.getText()).equals("") && !String.valueOf(email.getText()).equals("") && !String.valueOf(phone.getText()).equals("") && !String.valueOf(job.getText()).equals("")
                        && !String.valueOf(wage.getText()).equals(""))
                {
                    String eName = name.getText().toString();
                    String eEmail = email.getText().toString();
                    String ePhone = phone.getText().toString();
                    String eJob = job.getText().toString();
                    String eWage = wage.getText().toString();
                    String cid = getIntent().getExtras().getString("cid");

                    if (!eEmail.contains("@"))
                    {
                        Toast.makeText(add.this, "Email Must Contain an @ Symbol", Toast.LENGTH_SHORT).show();
                    }
                    else if (eWage.length() != 5 || eWage.charAt(2) != '.')
                    {
                        Toast.makeText(add.this, "Wage Must be in Format 'XX.XX'", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(), add2.class);
                        intent.putExtra("name", eName);
                        intent.putExtra("email", eEmail);
                        intent.putExtra("phone", ePhone);
                        intent.putExtra("job", eJob);
                        intent.putExtra("wage", eWage);
                        intent.putExtra("cid", cid);

                        if (parttime.isChecked())
                        {
                            intent.putExtra("parttime", "1");
                        }
                        else
                        {
                            intent.putExtra("parttime", "0");
                        }

                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(add.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

