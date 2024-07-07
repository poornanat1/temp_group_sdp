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

    @Query("SELECT * FROM jobs WHERE id = :id LIMIT 1")
    Job getJobById(long id);

    @Query("SELECT * FROM jobs WHERE isCurrentJob = 1 LIMIT 1")
    Job getCurrentJobs();

    @Query("SELECT * FROM jobs WHERE title = :title AND state = :state AND city = :city AND company = :company")
    Job getDuplicatedJob(String title, String state, String city, String company);

    @Update
    void update(Job job);
}