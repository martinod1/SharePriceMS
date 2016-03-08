package ie.martin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
@EnableCircuitBreaker
public class SharePriceMicroServiceApplication  {


	
	
    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(SharePriceMicroServiceApplication.class, args);
    }

    
    
    
    
}
