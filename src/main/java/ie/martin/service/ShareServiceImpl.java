package ie.martin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ie.martin.model.CheckedItem;
import ie.martin.model.ShareAnalysis;
import ie.martin.model.ShareHistoricalData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
/*
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;*/

import ie.martin.model.Share;


@Service("stockService")
//@EnableCircuitBreaker
public class ShareServiceImpl  implements  ShareService {

	String key;
	String value;
	String[] values;

	String perChange;
	String change50Day;
	String change200Day;
	String PER;
	String PEG;
	String divYield;


	List<ShareAnalysis> shares;

	ArrayList<Double> sharesHistorical;
	ArrayList<LocalDate> dates;

	ArrayList<String >dateList;

	public ShareServiceImpl() {
		super();

	}


	public String getFallback() {
		System.out.println("Within fallback method");
		return "fallback FALLBACK";
	}


	@Override
	//@HystrixCommand(fallbackMethod="getFallback")
	public List<ShareAnalysis> getShares() throws IOException {

		shares = new ArrayList<ShareAnalysis>();

		ApplicationContext appContext =
				new ClassPathXmlApplicationContext();
		//list of stocks hardcoded in for now
		String stocks[] = {"AAPL", "GOOG", "IBM", "MSFT", "FB", "INTC"};
		String url = "http://finance.yahoo.com/d/quotes.csv?s=";
		//the stocks are appended to the above URL with a + between all stocks
		for (int i = 0; i < stocks.length; i++) {
			url += stocks[i] + "+";
		}
		// n = name l1 = last share price p2 = change in % m8 =% change from 50
		// day working avg m6 = 200 day e = earnings/share
		// r = P/E (price/earning) ratio r5 = PEG ratio
		// (price/earnings to growth ratio) y = dividend yield
		url += "&f=nl1p2m8m6rr5y";
		//this calls URL and returns generated .csv file
		Resource resource = appContext.getResource(url);

		try {

			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			int j = 0;
			while ((line = br.readLine()) != null) {

				Share s = new Share();
				ShareAnalysis sAnal = new ShareAnalysis();
				//split each line on ,
				values = line.split(",");
				//checks if second string in array contains any numbers, if not then the
				// company name contains a , so we must take this into account
				if (!(values[1].toString().matches(".*\\d+.*"))) {
					//  System.out.println("inside if");
					value = values[2];
					//set each variable for the ShareAnalysis object
					perChange = values[3];
					change50Day = values[4];
					change200Day = values[5];
					PER = values[6];
					PEG = values[7];
					divYield = values[8];
				} else {
					value = values[1];
					//set each variable for the ShareAnalysis object
					perChange = values[2];
					change50Day = values[3];
					change200Day = values[4];
					PER = values[5];
					PEG = values[6];
					divYield = values[7];
				}
				key = stocks[j];

				s.setName(key);
				s.setValue(value);
					sAnal.setShare(s);
					sAnal.setPercentageChange(perChange);
					sAnal.setChangeFrom50DayWorkingAvg(change50Day);
					sAnal.setChangeFrom200DayWorkingAvg(change200Day);
					sAnal.setPERatio(PER);
					sAnal.setPEGRatio(PEG);
					sAnal.setDividendYield(divYield);
				shares.add(sAnal);

				j++;
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return shares;
	}


	@Override
	public ShareHistoricalData getHistoricalData(CheckedItem check) throws IOException {

		//here we convert the date to the format required for the retrieval of
		//historical data from Yahoo - day-month-year, with no timestamp included
		Date startDate = check.getStartDate();
		Date endDate = check.getEndDate();
		//I have used SimpleDateFormat to specify the format I require
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
		String start = dt1.format(startDate);
		String end = dt1.format(endDate);

		List<String> share = check.getChosenShare();
		List<String> freq = check.getCheckedItems();

		String chosenShare = share.get(0);
		String frequency = freq.get(0);

		String a,b,c,d,e,f;

		//System.out.println(start+end+chosenShare+frequency);

		//dates =new ArrayList<LocalDate>();

		//create new ShareHistoricalData object
		ShareHistoricalData sHistory= new ShareHistoricalData();
		//create new arraylist of sharePrices
		sharesHistorical = new ArrayList<Double>();
		String date;
		//create new arraylist of dates
		dateList = new ArrayList<String>();
		//the name is also set from the users chosen share
		sHistory.setName(chosenShare);


		ApplicationContext appContext =
				new ClassPathXmlApplicationContext();
		//list of stocks hardcoded in for now
		//String stocks[] = {"AAPL", "GOOG", "IBM", "MSFT", "FB", "INTC"};

		//here i format the passed in parameters to fit correctly in the URL
		if(frequency.equalsIgnoreCase("Monthly"))
		{
			frequency="m";
		}
		else if(frequency.equalsIgnoreCase("Weekly"))
		{
			frequency="w";
		}
		else if(frequency.equalsIgnoreCase("Daily"))
		{
			frequency="d";
		}
		String[]values;
		values = start.split("-");
		a=values[0];
		b=values[1];
		c=values[2];

		int a1 = (Integer.parseInt(a)-1);
		a = Integer.toString(a1);




		values=end.split("-");
		d=values[0];
		e=values[1];
		f=values[2];

		int d1 = (Integer.parseInt(d)-1);
		d = Integer.toString(d1);

		System.out.println(a+b+c);
		System.out.println(d+e+f);

		String url = "http://real-chart.finance.yahoo.com/table.csv?s="+chosenShare+"&a="+b+"&b="+a+"&c="+c+"&d="+e+"&e="+d+"&f="+f+"&g="+frequency+"&ignore=.csv";
		//  String url = "http://real-chart.finance.yahoo.com/table.csv?s=AAPL&a=11&b=12&c=2016&d=03&e=29&f=2016&g=d&ignore=.csv";
		System.out.println(url);
		//String url = "http://real-chart.finance.yahoo.com/table.csv?s=AAPL&
		// a=07&b=9&c=2013&d=03&e=19&f=2016&g=d.csv&ignore=.csv";


		Resource resource = appContext.getResource(url);
		try {

			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			boolean first = true;
			while ((line = br.readLine()) != null) {

				//split each line on ,
				values = line.split(",");

				if(first == true)
				{
				}
				else
				{
					date = values[0];
					value = values[1];
					Double val = Double.parseDouble(value);
					sharesHistorical.add((val));
					dateList.add(date);


				}
				first = false;




			}

			sHistory.setHistoricalPrices(sharesHistorical);
			sHistory.setDates(dateList);

			}
				catch(IOException io){
					io.printStackTrace();
				}
				return sHistory;

			}


	}

