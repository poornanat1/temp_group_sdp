package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CompareJobs extends AppCompatActivity {

    private JobComparatorDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);
        Button mainMenu = findViewById(R.id.main_menu_button);

        db = JobComparatorDatabase.getInstance(getApplicationContext());
        Executor executor = Executors.newSingleThreadExecutor();
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        executor.execute(() -> {
            List<Job> jobs = db.jobDao().getAllJobs();
            AtomicInteger rank = new AtomicInteger(1);
            runOnUiThread(() -> {
                for (Job job : jobs) {

                    TableRow tableRow = new TableRow(this);

                    TextView rankTextView = new TextView(this);
                    rankTextView.setText(String.valueOf(rank.get()));
                    tableRow.addView(rankTextView);

                    TextView titleTextView = new TextView(this);
                    titleTextView.setText(job.getTitle().toString());
                    tableRow.addView(titleTextView);

                    tableLayout.addView(tableRow);
                    rank.getAndIncrement();
                }
            });
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
