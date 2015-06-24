package com.dtui.models;

public class VideoItem {

	private String image;
	private String title;
	private String youtubeID;
	
	public VideoItem(String image, String title, String ID){
		this.image = image;
		this.title = title;
		this.youtubeID = ID;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYoutubeID() {
		return youtubeID;
	}
	public void setYoutubeID(String youtubeID) {
		this.youtubeID = youtubeID;
	}
	
	
}
