package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "jobs", indices = {@Index(value={"isCurrentJob"},unique=false)},primaryKeys = {"title", "company","city","state"})
public class Job {
    // add a handler to handle nonnull for these Pks
    @NonNull
    private String title;
    @NonNull
    private String company;
    @NonNull
    private String city;
    @NonNull
    private String state;
    private float costOfLivingIndex;
    private float yearlyBonus;
    private float yearlySalary;
    private float trainingAndDevelopmentFund;
    private int leaveTime;
    private int teleworkDaysPerWeek;
    private boolean isCurrentJob;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Location getLocation() {
        return new Location(this.state,this.city);
    }

    public void setLocation(Location location) {
        this.city = location.getCity();
        this.state = location.getState();
    }

    public float getCostOfLivingIndex() {
        return costOfLivingIndex;
    }

    public void setCostOfLivingIndex(float costOfLivingIndex) {
        this.costOfLivingIndex = costOfLivingIndex;
    }

    public float getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(float yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public float getTrainingAndDevelopmentFund() {
        return trainingAndDevelopmentFund;
    }

    public void setTrainingAndDevelopmentFund(float trainingAndDevelopmentFund) {
        this.trainingAndDevelopmentFund = trainingAndDevelopmentFund;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getTeleworkDaysPerWeek() {
        return teleworkDaysPerWeek;
    }

    public void setTeleworkDaysPerWeek(int teleworkDaysPerWeek) {
        this.teleworkDaysPerWeek = teleworkDaysPerWeek;
    }

    public float getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(float yearlySalary) {
        this.yearlySalary = yearlySalary;
    }


    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }
}