package ie.martin.service;

import java.io.IOException;
import java.util.List;

import ie.martin.model.CheckedItem;
import ie.martin.model.ShareAnalysis;
import ie.martin.model.ShareHistoricalData;

public interface ShareService {
	
	public List<ShareAnalysis> getShares() throws IOException;

	public ShareHistoricalData getHistoricalData(CheckedItem check) throws IOException;

}

