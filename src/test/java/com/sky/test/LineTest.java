package com.sky.test;

import org.junit.Assert;
import org.junit.Test;

import com.sky.detector.model.Line;

public class LineTest {

	private static final String ANY_USER_NAME = "Mauricio.Miralles";
	private static final String ANY_LOG_LINE = "80.238.9.254,133612947,SIGNIN_FAILURE,Mauricio.Miralles";
	private Line line;
	
	private void beginScenario(){
		
		line = new Line(ANY_LOG_LINE);
	}

	@Test
	public void buildLineFromLogLine() {
		beginScenario();
		
		String userName = line.getUserName();
		
		Assert.assertEquals("Should be Mauricio.Miralles", ANY_USER_NAME, userName);
	}
}