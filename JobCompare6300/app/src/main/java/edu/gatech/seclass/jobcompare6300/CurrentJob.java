package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CurrentJob extends AppCompatActivity {

    private JobComparatorDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        EditText title = findViewById(R.id.titleEditText);
        EditText company = findViewById(R.id.companyEditText);
        EditText state = findViewById(R.id.stateSpinner);
        EditText city = findViewById(R.id.cityEditText);
        EditText costOfLivingEditText = findViewById(R.id.costOfLivingEditText);
        EditText salaryEditText = findViewById(R.id.salaryEditText);
        EditText bonusEditText = findViewById(R.id.bonusEditText);
        EditText trainingEditText = findViewById(R.id.trainingEditText);
        EditText leaveTimeEditText = findViewById(R.id.leaveTimeEditText);
        EditText teleworkDaysSpinner = findViewById(R.id.teleworkDaysSpinner);
        Button mainMenu = findViewById(R.id.mainMenu);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Job job = db.jobDao().getCurrentJobs();
            runOnUiThread(() -> {
                if(job == null)
                    return;
                title.setText(job.getTitle().toString());
                company.setText(job.getCompany().toString());
                city.setText(job.getCity().toString());
                state.setText(job.getState().toString());
                costOfLivingEditText.setText(String.valueOf(job.getCostOfLivingIndex()));
                salaryEditText.setText(String.valueOf(job.getYearlySalary()));
                bonusEditText.setText(String.valueOf(job.getYearlyBonus()));
                trainingEditText.setText(String.valueOf(job.getTrainingAndDevelopmentFund()));
                leaveTimeEditText.setText(String.valueOf(job.getLeaveTime()));
                teleworkDaysSpinner.setText(String.valueOf(job.getTeleworkDaysPerWeek()));
            });
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentJob.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            void cancelEverything(ViewGroup viewGroup){
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View child = viewGroup.getChildAt(i);
                    if (child instanceof EditText) {
                        ((EditText) child).setText("");
                    } else if (child instanceof ViewGroup) {
                        cancelEverything((ViewGroup) child);
                    }
                }
            }
            @Override
            public void onClick(View v) {
                ViewGroup currentJobViewGroup = findViewById(R.id.current_job);
                cancelEverything(currentJobViewGroup);
            }
        });

        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(() -> {
                    Job job = db.jobDao().getCurrentJobs();
                    boolean jobExist = true;
                    if(job == null){
                         job = new Job();
                         jobExist = false;
                    }
                    job.setTitle(title.getText().toString().trim());
                    job.setCompany(company.getText().toString().trim());
                    job.setState(state.getText().toString().trim());
                    job.setCity(city.getText().toString().trim());
                    String costOfLivingEditText1 = costOfLivingEditText.getText().toString().trim();
                    job.setCostOfLivingIndex(Float.parseFloat(costOfLivingEditText1));
                    job.setYearlySalary(Float.parseFloat(salaryEditText.getText().toString().trim()));
                    job.setYearlyBonus(Float.parseFloat(bonusEditText.getText().toString().trim()));
                    job.setTrainingAndDevelopmentFund(Float.parseFloat(trainingEditText.getText().toString().trim()));
                    job.setLeaveTime(Integer.parseInt(leaveTimeEditText.getText().toString().trim()));
                    job.setTeleworkDaysPerWeek(Integer.parseInt(teleworkDaysSpinner.getText().toString().trim()));
                    job.setCurrentJob(true);
                    if(jobExist){
                        db.jobDao().update(job);
                    }else{
                        db.jobDao().insertJob(job);
                    }
                }).start();
            }
        });
    }

}
