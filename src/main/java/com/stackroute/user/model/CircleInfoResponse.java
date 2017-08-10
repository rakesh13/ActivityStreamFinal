package com.stackroute.user.model;

import java.util.List;

//What is this class?
//If you want to fetch all the circles, directly you can call DAO.
public class CircleInfoResponse {

	private List<Circle> circles;

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}
	
}
