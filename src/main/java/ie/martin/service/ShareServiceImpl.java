package ie.martin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import ie.martin.model.ShareAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import ie.martin.model.Share;


@Service("stockService")
//@EnableCircuitBreaker
public class ShareServiceImpl  implements  ShareService {

	String key;
	String value;
	String [] values;

	String perChange;
	String change50Day;
	String change200Day;
	String PER;
	String PEG;
	String divYield;


	List<ShareAnalysis> shares;
	
	public ShareServiceImpl() {
		super();
 
	}

	
	public String getFallback()
	{
		System.out.println("Within fallback method");
		return "fallback FALLBACK";
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="getFallback")
	public List<ShareAnalysis> getShares() throws IOException
    {
		//System.out.println("inside stock method");
		
		shares =  new ArrayList<ShareAnalysis>();

    	ApplicationContext appContext = 
    	   new ClassPathXmlApplicationContext();
    	//list of stocks hardcoded in for now
    	String  stocks [] = {"AAPL","GOOG","IBM","MSFT","FB","INTC"};
    	String url = "http://finance.yahoo.com/d/quotes.csv?s=";
    	//the stocks are appended to the above URL with a + between all stocks
    	for(int i=0; i<stocks.length; i++)
    	{
    		url += stocks[i]+"+";
    	}
		// n = name l1 = last share price p2 = change in % m8 =% change from 50 day working avg m6 = 200 day e = earnings/share
		// r = P/E (price/earning) ratio r5 = PEG ratio (price/earnings to growth ratio) y = dividend yield
    	url += "&f=nl1p2m8m6rr5y";
    	//this calls URL and returns generated .csv file
		  Resource resource = appContext.getResource(url);

    	
    	  try{
    		
    		  InputStream is = resource.getInputStream();
              BufferedReader br = new BufferedReader(new InputStreamReader(is));
            	
              String line;
              int j=0;
              while ((line = br.readLine()) != null) {

				  Share s  = new Share();
				  ShareAnalysis sAnal = new ShareAnalysis();
				  //split each line on ,
            	  values=line.split(",");

				//checks if second string in array contains any numbers, if not then the company name contains a , so we must take this into account

				  if(!(values[1].toString().matches(".*\\d+.*")))
				  {
					//  System.out.println("inside if");
					  value = values[2];
					  //set each variable for the ShareAnalysis object
					   perChange = values[3];
					   change50Day = values[4];
					   change200Day = values[5];
					   PER = values[6];
					   PEG = values[7];
					   divYield = values[8];


				  }

            	  else
            	  {
            		  value=values[1];
					  //set each variable for the ShareAnalysis object
					  perChange = values[2];
					  change50Day = values[3];
					  change200Day = values[4];
					  PER = values[5];
					  PEG = values[6];
					  divYield = values[7];
            	  }
            	  //set the key. Taken from position in loop relative to arraylist of stocks
            	  key=stocks[j];
            //	  System.out.println("Key = " + key);
            //	  System.out.println("Value = " + value);
            	  s.setName(key);
            	  s.setValue(value);

				  sAnal.setShare(s);
				  sAnal.setPercentageChange(perChange);
				  sAnal.setChangeFrom50DayWorkingAvg(change50Day);
				  sAnal.setChangeFrom200DayWorkingAvg(change200Day);
				  sAnal.setPERatio(PER);
				  sAnal.setPEGRatio(PEG);
				  sAnal.setDividendYield(divYield);
				//  System.out.println("just before adding to arraylist");
				  shares.add(sAnal);
				//  System.out.println("just after adding to arraylist");
				  System.out.println(sAnal.toString());
				//  System.out.println(line);
                 j++;
           	  } 
              br.close();
            	
        	}catch(IOException e){
        		e.printStackTrace();
        	}
		return shares;
    	  
        	
        }


	
	

}
