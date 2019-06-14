package com.testvideoanalysis.video;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testvideoanalysis.path.PathManager;

@Service
public class VideoService {
	
	@Autowired
	Analyser analyser;
	
	@Autowired
	PathManager pathManager;
	
	public String uploadFile(Video video, MultipartFile file) {
		
		try {
		      InputStream is = file.getInputStream();
		      Files.copy(is, Paths.get(pathManager.getDownloadedVideos() + video.getVideoNameWithExtension()), StandardCopyOption.REPLACE_EXISTING);
		      String tags = analyser.getTags(video);
		      return tags;
		}
		
		catch (IOException ex) {
		      return "Error writing file to output stream.";
		}
	}
}
