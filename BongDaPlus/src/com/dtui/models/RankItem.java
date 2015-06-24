package com.dtui.models;

public class RankItem {

	private String season;		// Ten mua giai
	private String rank;		// Xep hang
	private String team;		// Ten doi
	private int icon;			// icon club
	private String numberPlay;	// So tran da
	private String win;			// Thang
	private String draw;		// Hoa
	private String lose;		// Thua
	private String numberGoal;	// So ban thang
	private String rippedNet;	// Thung luoi
	private String subGoal; 	// Hieu so ban thang
	private String point; 		// Diem so

	public RankItem(String season, String rank, int icon, String team, String numberPlay, String win,
			String draw, String lose, String numberGoal, String rippedNet,
			String subGoal, String point) {
		
		this.season = season;
		this.rank = rank;
		this.icon = icon;
		this.team = team;
		this.numberPlay = numberPlay;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
		this.numberGoal = numberGoal;
		this.rippedNet = rippedNet;
		this.subGoal = subGoal;
		this.point = point;
	}

	
	public String getSeason() {
		return season;
	}


	public void setSeason(String season) {
		this.season = season;
	}


	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getNumberPlay() {
		return numberPlay;
	}

	public void setNumberPlay(String numberPlay) {
		this.numberPlay = numberPlay;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public String getLose() {
		return lose;
	}

	public void setLose(String lose) {
		this.lose = lose;
	}

	public String getNumberGoal() {
		return numberGoal;
	}

	public void setNumberGoal(String numberGoal) {
		this.numberGoal = numberGoal;
	}

	public String getRippedNet() {
		return rippedNet;
	}

	public void setRippedNet(String rippedNet) {
		this.rippedNet = rippedNet;
	}

	public String getSubGoal() {
		return subGoal;
	}

	public void setSubGoal(String subGoal) {
		this.subGoal = subGoal;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

}
