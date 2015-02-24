package com.sky.detector;

import java.util.HashMap;

import com.sky.detector.model.Line;
import com.sky.detector.model.SuspiciousRepository;

public class HackerDetectorImpl implements HackerDetector {
	
	private HashMap<String, SuspiciousRepository> badAttemps = new HashMap<String, SuspiciousRepository>();

	public String parseLine(String logLine) {
		
		Line line = new Line(logLine);
		
		
		return retreiveSuspiciousIp(line);
	}

	private String retreiveSuspiciousIp(Line line) {
		String result = null;
		
		if(isIpNotNull(line)){
			SuspiciousRepository attemp = new SuspiciousRepository(line);
			if(!badAttemps.containsKey(line.getIp())){
				badAttemps.put(line.getIp(), attemp);
			}else{
				SuspiciousRepository storedAttemp = badAttemps.get(line.getIp());
				int numAttemps = incrementAttempt(storedAttemp);
				result = updateRepository(line, result, storedAttemp, numAttemps);
			}
		}
		return result;
	}

	private boolean isIpNotNull(Line line) {
		return null!=line.getIp();
	}

	private int incrementAttempt(SuspiciousRepository storedAttemp) {
		int numAttemps = storedAttemp.getAttemps();
		numAttemps++;
		return numAttemps;
	}

	private String updateRepository(Line line, String result,
			SuspiciousRepository storedAttemp, int numAttemps) {
		if(numAttemps>=5){
			result = clearMemoryAndRetreiveIp(line);
		}else{
			storedAttemp.setAttemps(numAttemps);
			badAttemps.put(line.getIp(), storedAttemp);
		}
		return result;
	}

	private String clearMemoryAndRetreiveIp(Line line) {
		String result;
		result = line.getIp();
		badAttemps.remove(line.getIp());
		return result;
	}
}