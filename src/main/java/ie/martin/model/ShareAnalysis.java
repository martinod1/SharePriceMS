package ie.martin.model;

/**
 * Created by martin on 16/02/16.
 */
public class ShareAnalysis {

    private Share share;

  //  private String name;
  //  private String value;
    private String percentageChange;
    private String changeFrom50DayWorkingAvg;
    private String changeFrom200DayWorkingAvg;
    private String PERatio; //price/earning ratio
    private String PEGRatio; //price/earnings to growth ratio
    private String dividendYield;

    @Override
    public String toString() {
        return "ShareAnalysis{" +
                "share=" + share +
                ", percentageChange='" + percentageChange + '\'' +
                ", changeFrom50DayWorkingAvg='" + changeFrom50DayWorkingAvg + '\'' +
                ", changeFrom200DayWorkingAvg='" + changeFrom200DayWorkingAvg + '\'' +
                ", PERatio='" + PERatio + '\'' +
                ", PEGRatio='" + PEGRatio + '\'' +
                ", dividendYield='" + dividendYield + '\'' +
                '}';
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public String getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(String percentageChange) {
        this.percentageChange = percentageChange;
    }

    public String getChangeFrom50DayWorkingAvg() {
        return changeFrom50DayWorkingAvg;
    }

    public void setChangeFrom50DayWorkingAvg(String changeFrom50DayWorkingAvg) {
        this.changeFrom50DayWorkingAvg = changeFrom50DayWorkingAvg;
    }

    public String getChangeFrom200DayWorkingAvg() {
        return changeFrom200DayWorkingAvg;
    }

    public void setChangeFrom200DayWorkingAvg(String changeFrom200DayWorkingAvg) {
        this.changeFrom200DayWorkingAvg = changeFrom200DayWorkingAvg;
    }

    public String getPERatio() {
        return PERatio;
    }

    public void setPERatio(String PERatio) {
        this.PERatio = PERatio;
    }

    public String getPEGRatio() {
        return PEGRatio;
    }

    public void setPEGRatio(String PEGRatio) {
        this.PEGRatio = PEGRatio;
    }

    public String getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.dividendYield = dividendYield;
    }
}
