package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private JobComparatorDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button adjust_settings_button = findViewById(R.id.btn_adjust_settings);

        if(adjust_settings_button != null)
            adjust_settings_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AdjustSettings.class);
                    startActivity(intent);
                }
            });

        Button edit_job_offer = findViewById(R.id.btn_edit_current_job);
        if(edit_job_offer != null)
            edit_job_offer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CurrentJob.class);
                    startActivity(intent);
                }
            });
        Button enter_job_offer = findViewById(R.id.btn_enter_job_offers);
        enter_job_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewOffer.class);
                startActivity(intent);
            }
        });

        Button compare_job_offer = findViewById(R.id.btn_compare_offers);
        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Job> jobs = db.jobDao().getAllJobs();
            runOnUiThread(() -> {
                if(jobs.size() < 2)
                    return;
                compare_job_offer.setEnabled(true);
            });
        });
        compare_job_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompareJobs.class);
                startActivity(intent);
            }
        });

    }
}