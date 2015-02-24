package com.sky.test;

import org.junit.Assert;
import org.junit.Test;

import com.sky.detector.HackerDetector;
import com.sky.detector.HackerDetectorImpl;

public class UseCaseTest {
	
	private static final String ANY_IP = "80.238.9.254";
	private static final String LOG_LINE_OK_VALUE = "80.238.9.179,133612947,SIGNIN_SUCCESS,Dave.Branning";
	private static final String LOG_LINE_KO_VALUE = "80.238.9.254,133612947,SIGNIN_FAILURE,Mauricio.Miralles";
	
	
	private static final String LOG_LINE_KO_VALUE_MAURICIO = "80.238.9.254,133612947,SIGNIN_FAILURE,Mauricio.Miralles";
	private static final String LOG_LINE_KO_VALUE_MANUEL = "80.238.9.254,133612947,SIGNIN_FAILURE,Manuel.Velez";
	private static final String LOG_LINE_KO_VALUE_LUIS = "80.238.9.254,133612947,SIGNIN_FAILURE,Luis.Ayllon";
	private static final String LOG_LINE_KO_VALUE_JORGE = "80.238.9.254,133612947,SIGNIN_FAILURE,Jorge.Mendes";
	private static final String LOG_LINE_KO_VALUE_ISIDRO = "80.238.9.254,133612947,SIGNIN_FAILURE,Isidro.Tomate";
	private HackerDetector hackerDetector;
	private String logLine;
	
	private String logLineMauricio;
	private String logLineManuel;
	private String logLineLuis;
	private String logLineJorge;
	private String logLineIsidro;
	
	private void beginScenarioOk() {
		hackerDetector = new HackerDetectorImpl();
		logLine = LOG_LINE_OK_VALUE;
	}
	
	private void beginScenarioOneLineKo() {
		hackerDetector = new HackerDetectorImpl();
		logLine = LOG_LINE_KO_VALUE;
	}

	private void beginScenarioFiveLinesKo() {
		hackerDetector = new HackerDetectorImpl();
		logLineMauricio = LOG_LINE_KO_VALUE_MAURICIO; 
		logLineManuel = LOG_LINE_KO_VALUE_MANUEL; 
		logLineLuis = LOG_LINE_KO_VALUE_LUIS; 
		logLineJorge = LOG_LINE_KO_VALUE_JORGE; 
		logLineIsidro = LOG_LINE_KO_VALUE_ISIDRO; 
	}

	@Test
	public void readOneLineOk() {
		beginScenarioOk();
		
		String result = hackerDetector.parseLine(logLine);
		
		Assert.assertNull("Should be null", result);
	}
	
	@Test
	public void readOneLineSuspicius() {
		beginScenarioOneLineKo();
		
		String result = hackerDetector.parseLine(logLine);
		
		Assert.assertNull("Should be not null", result);
	}
	
	@Test
	public void readFiveSuspiciusLinesAndRetreiveIp() {
		beginScenarioFiveLinesKo();
		
		hackerDetector.parseLine(logLineMauricio);
		hackerDetector.parseLine(logLineManuel);
		hackerDetector.parseLine(logLineLuis);
		hackerDetector.parseLine(logLineJorge);
		String result = hackerDetector.parseLine(logLineIsidro);
		
		Assert.assertNotNull("Should be not null", result);
		Assert.assertEquals("Should be 80.238.9.254", ANY_IP, result);
	}
}