package ie.martin.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ie.martin.model.CheckedItem;
import ie.martin.model.RequestObject;
import ie.martin.model.ShareAnalysis;
import ie.martin.model.ShareHistoricalData;
import ie.martin.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

@RestController
public class ShareRestAPI {
	
	@Autowired
	ShareService share;

	Gson gson = new Gson();


	@HystrixCommand(fallbackMethod = "getCachedShares")
	@RequestMapping(value="get-latest-shares", method=RequestMethod.GET)
	public List<ShareAnalysis> getLatestShares() throws IOException {

		return share.getShares();
	}


	//again here the checked Item is retrieved from the RequestObject
	@RequestMapping(value="get-historical-data", method=RequestMethod.POST)
	public String getHistoricalData(@RequestBody RequestObject request) throws IOException {
		//This checkedItem is passed into the getHistoricalData method in the ShareService class
		CheckedItem item = request.getCheck();
		//This returns a ShareHistoricalData object
		ShareHistoricalData shareHist = share.getHistoricalData(item);

		String json = gson.toJson(shareHist);
		System.out.println(json);

		return json;
	}
	


	
}

