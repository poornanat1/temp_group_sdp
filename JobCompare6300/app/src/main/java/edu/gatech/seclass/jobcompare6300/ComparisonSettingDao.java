package edu.gatech.seclass.jobcompare6300;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ComparisonSettingDao {
    @Insert
    void insertComparisonSetting(ComparisonSetting comparisonSetting);
    //The query will return null if it is empty
    @Query("SELECT * FROM ComparisonSetting LIMIT 1")
    ComparisonSetting getSetting();
    @Query("DELETE FROM ComparisonSetting")
    void ClearOutExistingSetting();
}