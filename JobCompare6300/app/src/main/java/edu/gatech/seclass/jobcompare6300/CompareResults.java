package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompareResults extends AppCompatActivity {

    private JobComparatorDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_results);

        Button performAnotherComparison = findViewById(R.id.performComparison);
        Button mainMenu = findViewById(R.id.mainMenu);
        Intent intent = getIntent();
        Long job1Id = intent.getLongExtra("job_1", 0);
        Long job2Id = intent.getLongExtra("job_2", 1);
        float rank1 = intent.getFloatExtra("rank_1", 0);
        float rank2 = intent.getFloatExtra("rank_2", 0);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            Job job1 = db.jobDao().getJobById(job1Id);
            Job job2 = db.jobDao().getJobById(job2Id);
            TextView t1 = findViewById(R.id.title1);
            TextView t2 = findViewById(R.id.title2);
            TextView c1 = findViewById(R.id.company1);
            TextView c2 = findViewById(R.id.company2);
            TextView l1 = findViewById(R.id.location1);
            TextView l2 = findViewById(R.id.location2);
            TextView s1 = findViewById(R.id.salary1);
            TextView s2 = findViewById(R.id.salary2);
            TextView b1 = findViewById(R.id.bonus1);
            TextView b2 = findViewById(R.id.bonus2);
            TextView tf1 = findViewById(R.id.training1);
            TextView tf2 = findViewById(R.id.training2);
            TextView leave1 = findViewById(R.id.leave1);
            TextView leave2 = findViewById(R.id.leave2);
            TextView tele1 = findViewById(R.id.telework1);
            TextView tele2 = findViewById(R.id.telework2);
            TextView _rank1 = findViewById(R.id.rank1);
            TextView _rank2 = findViewById(R.id.rank2);
            t1.setText(job1.getTitle().toString());
            t2.setText(job2.getTitle().toString());
            c1.setText(job1.getCompany().toString());
            c2.setText(job2.getCompany().toString());
            l1.setText(String.valueOf(job1.getLeaveTime()));
            l2.setText(String.valueOf(job2.getLeaveTime()));
            s1.setText(String.valueOf(job1.getYearlySalary()));
            s2.setText(String.valueOf(job2.getYearlySalary()));
            b1.setText(String.valueOf(job1.getYearlyBonus()));
            b2.setText(String.valueOf(job2.getYearlyBonus()));
            tf1.setText(String.valueOf(job1.getTrainingAndDevelopmentFund()));
            tf2.setText(String.valueOf(job2.getTrainingAndDevelopmentFund()));
            leave1.setText(String.valueOf(job1.getLeaveTime()));
            leave2.setText(String.valueOf(job2.getLeaveTime()));
            tele1.setText(String.valueOf(job1.getTeleworkDaysPerWeek()));
            tele2.setText(String.valueOf(job2.getTeleworkDaysPerWeek()));
            _rank1.setText(String.valueOf(rank1));
            _rank2.setText(String.valueOf(rank2));
        });
        performAnotherComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareResults.this, CompareJobs.class);
                startActivity(intent);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareResults.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
