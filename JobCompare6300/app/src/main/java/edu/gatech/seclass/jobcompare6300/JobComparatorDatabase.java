package edu.gatech.seclass.jobcompare6300;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Database(entities = {Job.class, ComparisonSetting.class}, version = 3,exportSchema = false)
public abstract class JobComparatorDatabase extends RoomDatabase {

    public abstract JobDao jobDao();
    public abstract ComparisonSettingDao setting();
    private static volatile JobComparatorDatabase DB;

    static JobComparatorDatabase getInstance(Context context) {
        if (DB == null) {
            synchronized (JobComparatorDatabase.class) {
                if (DB == null) {
                    DB = Room.databaseBuilder(context.getApplicationContext(),
                                    JobComparatorDatabase.class, "JobComparatorDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return DB;
    }
}