package com.testvideoanalysis.video;

import org.springframework.beans.factory.annotation.Autowired;

import com.testvideoanalysis.path.PathManager;

public class Video {
	
	private String videoName;
	private String videoType;
	private String videoId;
	
	@Autowired
	PathManager pathManager;
	
	public Video() {
	
	}
	
	public Video(String videoName, String videoId) {
		super();
		this.videoId				= videoId;
		String [] splitVideoName	= videoName.split("\\.");
		this.videoType 				= splitVideoName[splitVideoName.length-1];
		this.videoName	 			= splitVideoName[0] + "-" + videoId;
	}

	public String getVideoName() {
		return videoName;
	}
	
	public String getVideoNameWithExtension() {
		return videoName + "." + videoType;
	}
	
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	public String getVideoType() {
		return videoType;
	}
	
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	
	public String getVideoId() {
		return videoId;
	}
	
	public void setVideoid(String videoId) {
		this.videoId = videoId;
	}
	
}
