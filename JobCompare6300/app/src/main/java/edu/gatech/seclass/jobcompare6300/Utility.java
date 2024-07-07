package edu.gatech.seclass.jobcompare6300;

public class Utility {
    public static float calculateRankOfJob(Job job, ComparisonSetting setting){
        if(job == null || setting == null)
            return 0;
        int lt = setting.getLeaveTime();
        int tele =  setting.getTeleworkDaysWeek();
        int yb = setting.getYearlyBonus();
        int ys = setting.getYearlySalary();
        int td = setting.getTrainingAbdDevelopmentFund();
        int commonDenominator = lt + tele+ yb + ys + td;
        if(commonDenominator == 0)
            return 0;
        float tdf = job.getTrainingAndDevelopmentFund();
        float costOfLiving = job.getCostOfLivingIndex();
        float ays = job.getYearlySalary() * 100/costOfLiving;
        float ayb = job.getYearlyBonus() * 100/costOfLiving;
        float alt = job.getLeaveTime() * ays/260;
        float rwt = job.getTeleworkDaysPerWeek();
        float aysWeighted = ((float) ys/commonDenominator * ays);
        float ybWeighted = ((float) yb/commonDenominator * ayb);
        float tdWeighted = ((float) td/commonDenominator * tdf);
        float ltWeighted = ((float) lt/commonDenominator * alt);
        float teleWeighted = ((float) tele/commonDenominator)*((260 - 52 * rwt) * (ays / 260) / 8);
        float finalRank =  aysWeighted + ybWeighted + tdWeighted + ltWeighted - teleWeighted;
        return finalRank;
    };
}
