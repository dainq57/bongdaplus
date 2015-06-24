package com.dtui.models;

public class ResultItem {
	
	private String season;
	private String day;
	private String team1;
	private String team2;
	private String result;
	
	public ResultItem(String season, String day, String team1, String team2, String result){
		this.season = season;
		this.day = day;
		this.team1 = team1;
		this.team2 = team2;
		this.result = result;
	}
	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
