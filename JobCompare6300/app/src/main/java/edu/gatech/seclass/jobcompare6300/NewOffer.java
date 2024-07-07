package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NewOffer extends AppCompatActivity {

    private JobComparatorDatabase db;
    private Job currentOffer;

    public ComparisonSetting getComparisonSetting() {
        return comparisonSetting;
    }

    public void setComparisonSetting(ComparisonSetting comparisonSetting) {
        this.comparisonSetting = comparisonSetting;
    }

    private ComparisonSetting comparisonSetting;

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    private Job currentJob;

    public Job getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(Job currentOffer) {
        this.currentOffer = currentOffer;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_offer);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Button mainMenu = findViewById(R.id.mainMenuButton);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOffer.this, MainActivity.class);
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
                ViewGroup currentJobViewGroup = findViewById(R.id.new_offer);
                cancelEverything(currentJobViewGroup);
            }
        });

        Button enterNewJobOffer = findViewById(R.id.newJobButton);
        enterNewJobOffer.setOnClickListener(new View.OnClickListener() {
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
                ViewGroup currentJobViewGroup = findViewById(R.id.new_offer);
                cancelEverything(currentJobViewGroup);
            }
        });

        Button compareWithCurrentJob = findViewById(R.id.compareButton);

        compareWithCurrentJob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewGroup currentJobViewGroup = findViewById(R.id.new_offer);
                Intent intent = new Intent(NewOffer.this, CompareResults.class);

                if(currentOffer != null && currentJob != null){
                    intent.putExtra("job_1", currentOffer.getId());
                    intent.putExtra("job_2", currentJob.getId());
                    intent.putExtra("rank_1", Utility.calculateRankOfJob(currentOffer,getComparisonSetting()));
                    intent.putExtra("rank_2", Utility.calculateRankOfJob(currentJob, getComparisonSetting()));
                    startActivity(intent);
                }else{
                    runOnUiThread(() -> {
                        Toast.makeText(NewOffer.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });

        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                new Thread(() -> {
                    Job job = new Job();
                    String titleString = title.getText().toString().trim();
                    String companyString = company.getText().toString().trim();
                    String stateString = state.getText().toString().trim();
                    String cityString = city.getText().toString().trim();
                    String costOfLivingEditText1 = costOfLivingEditText.getText().toString().trim();
                    String yearlySalaryString = salaryEditText.getText().toString().trim();
                    String yearlyBonusString = bonusEditText.getText().toString().trim();
                    String trainingAndDevelopmentFundString = trainingEditText.getText().toString().trim();
                    String leaveTimeString = leaveTimeEditText.getText().toString().trim();
                    String teleworkDaysString = teleworkDaysSpinner.getText().toString().trim();


                    if(titleString.isEmpty() || companyString.isEmpty() || stateString.isEmpty() ||
                            cityString.isEmpty() || costOfLivingEditText1.isEmpty() || yearlySalaryString.isEmpty() ||
                            yearlyBonusString.isEmpty() || trainingAndDevelopmentFundString.isEmpty() ||
                            leaveTimeString.isEmpty() || teleworkDaysString.isEmpty()){
                        runOnUiThread(() -> {
                            new AlertDialog.Builder(NewOffer.this)
                                    .setTitle("Missing Job Information")
                                    .setMessage("Please fill in all the fields..")
                                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                    .show();
                        });
                    }else{
                        float yearlySalary = Float.parseFloat(salaryEditText.getText().toString().trim());
                        float yearlyBonus = Float.parseFloat(bonusEditText.getText().toString().trim());
                        float trainingAndDevelopmentFund = Float.parseFloat(trainingEditText.getText().toString().trim());
                        int leaveTime = Integer.parseInt(leaveTimeEditText.getText().toString().trim());
                        int teleworkDaysPerWeek = Integer.parseInt(teleworkDaysSpinner.getText().toString().trim());

                        job.setTitle(titleString);
                        job.setCompany(companyString);
                        job.setState(stateString);
                        job.setCity(cityString);

                        job.setCostOfLivingIndex(Float.parseFloat(costOfLivingEditText1));

                        job.setYearlySalary(yearlySalary);
                        job.setYearlyBonus(yearlyBonus);
                        job.setTrainingAndDevelopmentFund(trainingAndDevelopmentFund);
                        job.setLeaveTime(leaveTime);
                        job.setTeleworkDaysPerWeek(teleworkDaysPerWeek);

                        job.setCurrentJob(false);
                        Job jobInDB = db.jobDao().getDuplicatedJob(titleString, stateString, cityString, companyString);
                        if(jobInDB == null){
                            db.jobDao().insertJob(job);
                            jobInDB = db.jobDao().getDuplicatedJob(titleString, stateString, cityString, companyString);
                            setCurrentOffer(jobInDB);
                            Job currentJob = db.jobDao().getCurrentJobs();
                            ComparisonSetting comparisonSetting = db.settingDao().getSetting();
                            setCurrentJob(currentJob);
                            setComparisonSetting(comparisonSetting);
                            runOnUiThread(() -> {
                                Toast.makeText(NewOffer.this, "A new job offer saved successfully!", Toast.LENGTH_SHORT).show();
                            });
                        }else{
                            runOnUiThread(() -> {
                                new AlertDialog.Builder(NewOffer.this)
                                        .setTitle("Job Exists")
                                        .setMessage("The job you are trying to add already exists in the database.")
                                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                        .show();
                            });
                        }
                    }
                }).start();

            }
        });
    }
}
