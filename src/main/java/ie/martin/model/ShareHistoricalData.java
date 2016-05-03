package ie.martin.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by martin on 18/04/16.
 */
public class ShareHistoricalData {

    private String name;
    private ArrayList<String> dates;
    private ArrayList<Double> historicalPrices;

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(ArrayList<Double> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }
}
