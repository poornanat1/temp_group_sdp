package edu.gatech.seclass.jobcompare6300;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JobDao {
    @Insert
    void insertJob(Job job);

    @Query("SELECT * FROM jobs")
    List<Job> getAllJobs();

    @Query("SELECT * FROM jobs WHERE isCurrentJob = 1 LIMIT 1")
    Job getCurrentJobs();

    @Query("DELETE FROM jobs WHERE title = :title and company = :company and city = :city and state = :state")
    void deleteJobByCompositePK(String title, String company, String city, String state);

    @Query("SELECT * FROM jobs WHERE title = :title and company = :company and city = :city and state = :state")
    Job getJobByCompositePK(String title, String company, String city, String state);

    @Update
    void update(Job job);
}