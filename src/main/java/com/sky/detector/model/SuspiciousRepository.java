package com.sky.detector.model;

public class SuspiciousRepository {

	private String date;
	private int attemps;

	public SuspiciousRepository(Line line) {
		date = line.getDate();
		attemps++;
	}

	public int getAttemps() {
		return attemps;
	}

	public void setAttemps(int attemps) {
		this.attemps = attemps;
	}

}
