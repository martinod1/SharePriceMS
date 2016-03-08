package ie.martin.service;

import java.io.IOException;
import java.util.List;

import ie.martin.model.ShareAnalysis;

public interface ShareService {
	
	public List<ShareAnalysis> getShares() throws IOException;

}

