/*package stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;

import ie.martin.repository.StockRedisRepository;

@SpringBootApplication
@EnableCircuitBreaker
public class DownloadFile {
	
		String key;
		String value;
		String [] values;
		
//		@Autowired
//		private RedisTemplate<String, String> redisTemplate;
			
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		StockRedisRepository stockRepo = (StockRedisRepository)context.getBean("stockRepo");
//		 @javax.annotation.Resource(name="redisTemplate")
//		 private StockRedisRepository stockRepo;
//		@Autowired
//		private StockRedisRepository stockRepo;
//		 
		
		 
	//	RedisTemplate<String, String> redisTemplate;
		
		//public void getStocks()
	    {
	    //	ApplicationContext appContext = 
	    	   new ClassPathXmlApplicationContext();
	    	//list of stocks hardcoded in for now
	    	String  stocks [] = {"AAPL","GOOG","IBM","MSFT","FB","INTC"};
	    	
	    	String url = "http://finance.yahoo.com/d/quotes.csv?s=";
	    	//the stocks are appended to the above URL with a + between all stocks
	    	for(int i=0; i<stocks.length; i++)
	    	{
	    		url += stocks[i]+"+";
	    	}
	    	url += "&f=nl1";
	    	//this calls URL and returns generated .csv file
	    	Resource resource = context.getResource(url);
	    	
	    	  try{
	         	  InputStream is = resource.getInputStream();
	              BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            	
	              String line;
	              int j=0;
	              while ((line = br.readLine()) != null) {
	            	  //split each line on ,
	            	  values=line.split(",");
	            	  //this if else avoids any errors with Share Names with more than one word
	            	  if(values.length>2)
	            	  {
	            		  int size=values.length;
	            		  value=values[size-1];
	            	  }
	            	  else
	            	  {
	            		  value=values[1];
	            	  }
	            	  //set the key
	            	  key=stocks[j];
	            	  System.out.println("Key = " + key);
	            	
	            	  System.out.println("Value = " + value);
	            	  //add the key and value to redis
	            	  stockRepo.add(key, value);
	            	  
	            	
	                 System.out.println(line);
	                 j++;
	           	  } 
	              br.close();
	            	
	        	}catch(IOException e){
	        		e.printStackTrace();
	        	}
	        	
	        }
	    
	    

	

}
*/