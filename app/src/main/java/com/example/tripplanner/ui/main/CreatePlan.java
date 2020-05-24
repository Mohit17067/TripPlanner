package com.example.tripplanner.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tripplanner.R;

import java.util.Calendar;

public class CreatePlan extends AppCompatActivity {

    private EditText planDetails;
    private TextView planDate;
    private TextView planTime;
    private Button savePlan;
    private int  year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        planDetails = (EditText) findViewById(R.id.detailsField);
        planDate = (TextView) findViewById(R.id.dateField);
        planTime = (TextView) findViewById(R.id.timeField);
        savePlan = (Button) findViewById(R.id.savePlan);
        planDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                year=cal.get(Calendar.YEAR);
                month=cal.get(Calendar.MONTH);
                day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(CreatePlan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String y = Integer.toString(i);
                        String m = Integer.toString(i1+1);
                        String d = Integer.toString(i2);
                        if (m.length()==1)
                            m = "0"+m;
                        if (d.length()==1)
                            d = "0"+d;
                        planDate.setText(y+"/"+m+"/"+d);
                    }
                },year,month,day);
                d.show();

            }
        });

        planTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreatePlan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String curhr = Integer.toString(selectedHour);
                        String curmn = Integer.toString(selectedMinute);
                        if (curhr.length()==1)
                            curhr = "0"+curhr;
                        if (curmn.length()==1)
                            curmn = "0"+curmn;
                        planTime.setText( curhr+":"+curmn);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        savePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detailSelected = planDetails.getText().toString();
                String dateSelected = planDate.getText().toString();
                String timeSelected = planTime.getText().toString();

                if (detailSelected.equals("") || dateSelected.equals("") || timeSelected.equals("")){
                    Toast toast = Toast.makeText(CreatePlan.this,
                            "Please fill Everything",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else{
                    Plan plan = new Plan();
                    plan.setDetails(detailSelected);
                    plan.setTime(timeSelected);
                    plan.setDate(dateSelected);
                    PlanDatabase.get(CreatePlan.this).addItem(plan);
                    Toast toast = Toast.makeText(CreatePlan.this,
                            "Plan Saved!!",
                            Toast.LENGTH_SHORT);

                    toast.show();
                    finish();
                }
            }
        });
    }

}
