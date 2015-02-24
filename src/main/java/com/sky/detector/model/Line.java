package com.sky.detector.model;

public class Line {
	private static final int USER_NAME = 3;
	private static final int SIGNIN = 2;
	private static final int DATE = 1;
	private static final int IP = 0;
	private static final String DELIMITER = ",";
	private static final Object SIGNIN_FAILURE = "SIGNIN_FAILURE";
	private String ip;
	private String date;
	public String getDate() {
		return date;
	}

	private String signin;
	private String userName;
	
	public Line(String logLine) {
		
		fillLine(logLine);
	}
	
	private void fillLine(String logLine) {
		String[] tokens = logLine.split(DELIMITER);
		ip = retreiveIp(tokens);
		date = retreiveDate(tokens);
		signin = retreiveSignin(tokens);
		userName = retreiveUserName(tokens);
	}
	private String retreiveUserName(String[] tokens) {
		return tokens[USER_NAME];
	}
	private String retreiveSignin(String[] tokens) {
		return tokens[SIGNIN];
	}
	private String retreiveDate(String[] tokens) {
		return tokens[DATE];
	}
	private String retreiveIp(String[] tokens) {
		return tokens[IP];
	}
	private String returnIpIfSuspicius(String signin) {
		String result = null;
		if(signin.equals(SIGNIN_FAILURE)){
			result = ip;
		}
		return result;
	}
	public String getIp() {
		return returnIpIfSuspicius(signin);
	}
	
	public String getUserName() {
		return userName;
	}
}