package com.dtui.models;

public class CalendarItem {
	
	private String Season;
	private String DayFight;
	private String TimeFight;
	private String TeamOneFight;
	private String TeamTwoFight;
	
	public CalendarItem(String season, String day, String time, String team1, String team2){
		this.Season = season;
		this.DayFight = day;
		this.TimeFight = time;
		this.TeamOneFight = team1;
		this.TeamTwoFight = team2;
	}
	
	
	public String getSeason() {
		return Season;
	}



	public void setSeason(String season) {
		Season = season;
	}



	public String getDayFight() {
		return DayFight;
	}


	public void setDayFight(String dayFight) {
		DayFight = dayFight;
	}


	public String getTimeFight() {
		return TimeFight;
	}
	
	public void setTimeFight(String timeFight) {
		TimeFight = timeFight;
	}
	
	public String getTeamOneFight() {
		return TeamOneFight;
	}
	
	public void setTeamOneFight(String teamOneFight) {
		TeamOneFight = teamOneFight;
	}
	
	public String getTeamTwoFight() {
		return TeamTwoFight;
	}
	
	public void setTeamTwoFight(String teamTwoFight) {
		TeamTwoFight = teamTwoFight;
	}
		
}