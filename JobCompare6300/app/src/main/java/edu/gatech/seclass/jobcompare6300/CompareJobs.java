package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
public class CompareJobs extends AppCompatActivity {

    private JobComparatorDatabase db;

    public ComparisonSetting getSetting() {
        return comparisonSetting;
    }

    public void setSetting(ComparisonSetting setting) {
        this.comparisonSetting = setting;
    }

    private ComparisonSetting comparisonSetting;

    public Job getSelectedJob1Id() {
        return selectedJob1;
    }

    public void setSelectedJob1Id(Job selectedJob1Id) {
        this.selectedJob1 = selectedJob1Id;
    }

    public Job getSelectedJob2ID() {
        return selectedJob2;
    }

    public void setSelectedJob2ID(Job selectedJob2) {
        this.selectedJob2 = selectedJob2;
    }

    private Job selectedJob1;
    private Job selectedJob2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);
        Button mainMenu = findViewById(R.id.main_menu_button);
        Button compare_button = findViewById(R.id.compare_button);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Executor executor = Executors.newSingleThreadExecutor();
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        executor.execute(() -> {
            Map<Float, String> rankedJobList = new TreeMap<>(Comparator.reverseOrder());
            List<Job> jobs = db.jobDao().getAllJobs();
            ComparisonSetting setting = db.settingDao().getSetting();
            setSetting(setting);
            List<Map<String, Long>> jobNamesList= new ArrayList<Map<String,Long>>();
            runOnUiThread(() -> {
                for (Job job : jobs) {
                    String jobName =  job.getTitle().toString().trim();
                    if(job.isCurrentJob())
                        jobName += "  /current job";
                    float rankOfJob = Utility.calculateRankOfJob(job, setting);
                    rankedJobList.put(rankOfJob, jobName);
                    Map<String, Long> jobMap = new HashMap<>();
                    jobMap.put(jobName, job.getId());
                    jobNamesList.add(jobMap);
                }

                for(Map.Entry<Float, String> item : rankedJobList.entrySet()){
                    TableRow tableRow = new TableRow(this);
                    TextView rankTextView = new TextView(this);
                    rankTextView.setText(String.valueOf(item.getKey().intValue()));
                    tableRow.addView(rankTextView);
                    TextView titleTextView = new TextView(this);
                    titleTextView.setText(" " + item.getValue());
                    tableRow.addView(titleTextView);
                    tableLayout.addView(tableRow);
                }

                Spinner spinnerJob1 = findViewById(R.id.job1_spinner);
                Spinner spinnerJob2 = findViewById(R.id.job2_spinner);

                spinnerJob1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        HashMap<String, Long> selectedItem = (HashMap<String, Long> ) parent.getItemAtPosition(position);

                        Collection<Long> job1 = selectedItem.values();;
                        if(job1.iterator().hasNext()){
                            executor.execute(() -> {
                                Job job_1 = db.jobDao().getJobById(job1.iterator().next());
                                setSelectedJob1Id(job_1);
                            });
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                spinnerJob2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        HashMap<String, Long> selectedItem = (HashMap<String, Long> ) parent.getItemAtPosition(position);

                        Collection<Long> job2 = selectedItem.values();
                        if(job2.iterator().hasNext()){
                            executor.execute(() -> {
                                Job job_2 = db.jobDao().getJobById(job2.iterator().next());
                                setSelectedJob2ID(job_2);
                            });
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });


                ArrayAdapter<Map<String, Long>> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobNamesList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJob1.setAdapter(adapter);
                spinnerJob2.setAdapter(adapter);
            });
        });

        compare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareJobs.this, CompareResults.class);
                Spinner job_1 = findViewById(R.id.job1_spinner);
                Spinner job_2 = findViewById(R.id.job1_spinner);
                intent.putExtra("job_1", selectedJob1.getId());
                intent.putExtra("job_2", selectedJob2.getId());
                intent.putExtra("rank_1", Utility.calculateRankOfJob(selectedJob1,comparisonSetting));
                intent.putExtra("rank_2", Utility.calculateRankOfJob(selectedJob2, comparisonSetting));
                startActivity(intent);
            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareJobs.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
