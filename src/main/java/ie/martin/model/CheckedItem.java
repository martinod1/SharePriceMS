package ie.martin.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by martin on 27/04/16.
 */
public class CheckedItem {
    private List<String> checkedItems;

    private List<String> chosenShare;

   @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date startDate;

   @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date endDate;

    public List<String> getChosenShare() {
        return chosenShare;
    }

    public void setChosenShare(List<String> chosenShare) {
        this.chosenShare = chosenShare;
    }

    public List<String> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<String> checkedItems) {
        this.checkedItems = checkedItems;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CheckedItem{" +
                "checkedItems=" + checkedItems +
                ", chosenShare=" + chosenShare +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
