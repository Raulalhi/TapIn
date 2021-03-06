package com.example.thomas.tapin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Thomas on 21/04/2017.
 */

public class view extends AppCompatActivity
{
    private ListView listView;
    private EmployeeScroll scroll;
    private ArrayList<Employee> currentEmployees;
    private ProgressBar spinner;
    String cid = "";
    Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        spinner.setVisibility(View.VISIBLE);
        spinner.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        cid = getIntent().getExtras().getString("cid");

        listView = (ListView)findViewById(R.id.employeeList);

        currentDate = new Date();

        setTitle("Schedule for Today: "+new SimpleDateFormat("dd/MM/yyy").format(currentDate));

        currentEmployees = new ArrayList<Employee>();
        getCurrentEmployees();
    }

    public void getCurrentEmployees()
    {
        final ArrayList<String> info = new ArrayList<String>();

        BackgroundTask backgroundTask = new BackgroundTask(new BackgroundTask.AsyncResponse()
        {
            @Override
            public void processFinish(String output)
            {
                if (output.equals("null"))
                {
                    spinner.setVisibility(View.INVISIBLE);

                    AlertDialog alertDialog = new AlertDialog.Builder(view.this).create();
                    alertDialog.setTitle("Sorry");
                    alertDialog.setMessage("There are No Employees Working Today");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent2 = new Intent(getApplicationContext(), EmployerMain.class);
                                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent2);
                                    finish();
                                }
                            });
                    alertDialog.show();
                }
                else
                {
                    String[] tokens = output.split("-");

                    for (String t : tokens)
                    {
                        info.add(t);
                    }

                    for (int i = 0; i < info.size(); i++)
                    {
                        String employee = info.get(i);

                        String[] splitInfo = employee.split(",");

                        Employee n = new Employee(i, splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3]);
                        currentEmployees.add(n);
                    }

                    spinner.setVisibility(View.INVISIBLE);
                    scroll = new EmployeeScroll(getApplicationContext(), currentEmployees, 0);
                    listView.setAdapter(scroll);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(view.this).create();
                            Employee e = currentEmployees.get((int)view.getTag());

                            alertDialog.setTitle(e.getName());

                            if (!e.isCheckedIn())
                            {
                                alertDialog.setMessage("Should Clock in at "+e.getTime1());
                            }
                            else
                            {
                                alertDialog.setMessage("Clocked in at "+e.getTime1()+" and will Clock out at "+e.getTime2());
                            }
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            alertDialog.cancel();
                                        }
                                    });
                            alertDialog.show();
                        }
                    });
                }
            }
        });
        if( BackgroundTask.isNetworkAvailable(view.this))
        {
            backgroundTask.execute("getCheckedIn", cid, new SimpleDateFormat("d").format(currentDate), new SimpleDateFormat("M").format(currentDate));
        }
        else
        {
            finish();
            Toast.makeText(view.this,"No internet connection", Toast.LENGTH_LONG ).show();
        }

    }
}

