package edu.gatech.seclass.jobcompare6300;

import androidx.room.Entity;

@Entity(tableName = "ComparisonSetting",primaryKeys = {"trainingAbdDevelopmentFund"})
public class ComparisonSetting {
    public ComparisonSetting(int yearlySalary, int yearlyBonus, int trainingAbdDevelopmentFund, int leaveTime, int teleworkDaysWeek) {
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.trainingAbdDevelopmentFund = trainingAbdDevelopmentFund;
        this.leaveTime = leaveTime;
        this.teleworkDaysWeek = teleworkDaysWeek;
    }

    private int yearlySalary;
    private int yearlyBonus ;
    private int trainingAbdDevelopmentFund;
    private int leaveTime;
    private int teleworkDaysWeek;

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public int getYearlyBonus() {
        return this.yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getTrainingAbdDevelopmentFund() {
        return trainingAbdDevelopmentFund;
    }

    public void setTrainingAbdDevelopmentFund(int trainingAbdDevelopmentFund) {
        this.trainingAbdDevelopmentFund = trainingAbdDevelopmentFund;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getTeleworkDaysWeek() {
        return teleworkDaysWeek;
    }

    public void setTeleworkDaysWeek(int teleworkDaysWeek) {
        this.teleworkDaysWeek = teleworkDaysWeek;
    }
}