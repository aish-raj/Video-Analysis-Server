package com.testvideoanalysis.video;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testvideoanalysis.path.PathManager;
import com.testvideoanalysis.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	Analyser analyser;
	
	@Autowired
	PathManager pathManager;
	@Autowired 
	VideoRepository videorepository;
	
	public String uploadFile(Video video, MultipartFile file) {
		
		try {   videorepository.save(video);
		      InputStream is = file.getInputStream();
		      Files.copy(is, Paths.get(pathManager.getDownloadedVideos() + video.getVideoNameWithExtension()), StandardCopyOption.REPLACE_EXISTING);
		      updateStatus(video,"Processing");
		      
		      String tags = analyser.getTags(video);
		      updateTags(video,tags);
		      return tags;
		}
		
		catch (IOException ex) {
		      return "Error writing file to output stream.";
		}
	}

	public void updateTags(Video video, String tags) {
		// TODO Auto-generated method stub
		video.setTags(tags);
		videorepository.save(video);
		
	}

	public void updateStatus(Video video, String status) {
		// TODO Auto-generated method stubi
		video.setStatus(status);
		videorepository.save(video);
		
	}
	public String getVideoStatus(String id) {
		// TODO Auto-generated method stubi
		Optional<Video> video = videorepository.findById(id);
		if(video.isPresent())
			return video.get().getStatus();
		return "Video Id Invalid";
	}
	public Optional<Video> getVideo(String id) {
		return videorepository.findById(id);
	}

	
}
