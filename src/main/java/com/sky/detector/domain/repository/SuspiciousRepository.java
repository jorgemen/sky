package com.sky.detector.domain.repository;

import com.sky.detector.domain.model.Line;

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
