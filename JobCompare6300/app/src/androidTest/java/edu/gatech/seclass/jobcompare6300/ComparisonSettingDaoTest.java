package edu.gatech.seclass.jobcompare6300;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class ComparisonSettingDaoTest {
    private JobComparatorDatabase db;
    private ComparisonSettingDao settingDao;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), JobComparatorDatabase.class)
                .allowMainThreadQueries()
                .build();
        settingDao = db.setting();
    }
    @After
    public void closeDb() {
        db.close();
    }
    @Test
    public void TestInsertAndGetSetting(){
        // Arrange
        ComparisonSetting setting = new ComparisonSetting(1,2,3,4,5);
        //Act
        settingDao.insertComparisonSetting(setting);
        ComparisonSetting settingFromDB = settingDao.getSetting();
        // Assert
        assertNotNull(settingFromDB);
        assertEquals(1,settingFromDB.getYearlySalary());
        assertEquals(2,settingFromDB.getYearlyBonus());
        assertEquals(3,settingFromDB.getTrainingAbdDevelopmentFund());
        assertEquals(4,settingFromDB.getLeaveTime());
        assertEquals(5,settingFromDB.getTeleworkDaysWeek());
    }
    @Test
    public void TestClearOutSetting(){
        // Arrange
        ComparisonSetting setting = new ComparisonSetting(1,2,3,4,5);
        //Act
        settingDao.insertComparisonSetting(setting);
        settingDao.ClearOutExistingSetting();
        ComparisonSetting settingFromDB = settingDao.getSetting();
        // Assert
        assertNull(settingFromDB);
    }
}
