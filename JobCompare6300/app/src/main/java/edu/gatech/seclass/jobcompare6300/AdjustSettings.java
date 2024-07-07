package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdjustSettings extends AppCompatActivity {
    private ComparisonSettingDao settingDao;
    private ComparisonSetting currentSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adjust_settings);

        // getting the db instance
        JobComparatorDatabase db = JobComparatorDatabase.getInstance(this);
        settingDao = db.setting();

        Button mainMenu = findViewById(R.id.main_menu_button);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdjustSettings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Yearly bonus
        SeekBar seekBarYb = findViewById(R.id.yearly_bonus_seekbar);
        EditText editTextYb = findViewById(R.id.yearly_bonus_weight_value);
        // Yearly salary
        SeekBar seekBarYs = findViewById(R.id.yearly_salary_seekbar);
        EditText editTextYs = findViewById(R.id.yearly_salary_weight_value);
        // training & Dev
        SeekBar seekBarTd = findViewById(R.id.training_dev_fund_seekbar);
        EditText editTextTd = findViewById(R.id.training_dev_fund_weight_value);
        // leave time
        SeekBar seekBarLt = findViewById(R.id.leave_time_seekbar);
        EditText editTextLt = findViewById(R.id.leave_time_weight_value);
        // telework
        SeekBar seekBarTdw = findViewById(R.id.telework_days_seekbar);
        EditText editTextTdw = findViewById(R.id.telework_days_weight_value);
        // cancel button
        Button cancelButton = findViewById(R.id.cancel_button);
        ViewGroup rootView = findViewById(R.id.adjust_comparison_settings_root);
        // save button
        Button saveButton = findViewById(R.id.save_button);

        // load setting from db or if not exist then just set default to 5
        new Thread(() -> {
            currentSetting = settingDao.getSetting();
            runOnUiThread(() -> {
                if (currentSetting != null) {
                    // Populate UI with existing settings
                    seekBarYb.setProgress(currentSetting.getYearlyBonus());
                    editTextYb.setText(String.valueOf(currentSetting.getYearlyBonus()));

                    seekBarYs.setProgress(currentSetting.getYearlySalary());
                    editTextYs.setText(String.valueOf(currentSetting.getYearlySalary()));

                    seekBarTd.setProgress(currentSetting.getTeleworkDaysWeek());
                    editTextTd.setText(String.valueOf(currentSetting.getTeleworkDaysWeek()));

                    seekBarLt.setProgress(currentSetting.getLeaveTime());
                    editTextLt.setText(String.valueOf(currentSetting.getLeaveTime()));

                    seekBarTdw.setProgress(currentSetting.getTrainingAbdDevelopmentFund());
                    editTextTdw.setText(String.valueOf(currentSetting.getTrainingAbdDevelopmentFund()));
                } else {
                    // Set default values to 5
                    seekBarYb.setProgress(5);
                    editTextYb.setText("5");

                    seekBarYs.setProgress(5);
                    editTextYs.setText("5");

                    seekBarTd.setProgress(5);
                    editTextTd.setText("5");

                    seekBarLt.setProgress(5);
                    editTextLt.setText("5");

                    seekBarTdw.setProgress(5);
                    editTextTdw.setText("5");
                }
            });
        }).start();

        seekBarYb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update EditText with SeekBar value
                editTextYb.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Handle end of touch
            }

        });

        seekBarYs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update EditText with SeekBar value
                editTextYs.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Handle end of touch
            }

        });

        seekBarTd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update EditText with SeekBar value
                editTextTd.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Handle end of touch
            }

        });

        seekBarLt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update EditText with SeekBar value
                editTextLt.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Handle end of touch
            }

        });

        seekBarTdw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update EditText with SeekBar value
                editTextTdw.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Handle end of touch
            }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            private List<SeekBar> getAllSeekBars(ViewGroup root) {
                List<SeekBar> seekBars = new ArrayList<>();
                for (int i = 0; i < root.getChildCount(); i++) {
                    View child = root.getChildAt(i);
                    if (child instanceof SeekBar) {
                        seekBars.add((SeekBar) child);
                    }
                    else if (child instanceof ViewGroup) {
                        seekBars.addAll(getAllSeekBars((ViewGroup) child));
                    }
                }
                return seekBars;
            }
            @Override
            public void onClick(View v) {
                List<SeekBar> allSeekBars = getAllSeekBars(rootView);
                int resetValue = 5;
                for (SeekBar seekBar : allSeekBars) {
                    seekBar.setProgress(resetValue);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values from user
                int yearlyBonus= Integer.parseInt(editTextYb.getText().toString());
                int leaveTime = Integer.parseInt(editTextLt.getText().toString());
                int trainingAbdDevelopmentFund = Integer.parseInt(editTextTdw.getText().toString());
                int yearlySalary = Integer.parseInt(editTextYs.getText().toString());
                int teleworkDaysWeek = Integer.parseInt(editTextTd.getText().toString());

                ComparisonSetting setting = new ComparisonSetting(yearlySalary, yearlyBonus, trainingAbdDevelopmentFund, leaveTime, teleworkDaysWeek);

                // save the setting to the db
                new Thread(() -> {
                    if(currentSetting == null){
                        settingDao.insertComparisonSetting(setting);
                    }else{
                        // Clear out since we only want one single setting for all
                        settingDao.ClearOutExistingSetting();

                        settingDao.insertComparisonSetting(setting);
                    }
                    // show the current job is saved
                    runOnUiThread(() -> {
                        Toast.makeText(AdjustSettings.this, "Comparison setting saved successfully!", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });
    }
}
