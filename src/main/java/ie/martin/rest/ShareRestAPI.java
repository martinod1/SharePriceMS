package ie.martin.rest;

import java.io.IOException;
import java.util.List;

import ie.martin.model.ShareAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.martin.model.Share;
import ie.martin.service.ShareService;

@RestController
public class ShareRestAPI {
	
	@Autowired
	ShareService share;


	
	@RequestMapping(value="get-latest-shares", method=RequestMethod.GET)
	public List<ShareAnalysis> getLatestShares() throws IOException {
		//System.out.println("within Share MS");
		return share.getShares();
	}
	


	
}

