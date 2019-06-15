package com.testvideoanalysis.video;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class VideoController {
	
	@Autowired
	private VideoService videoService;

	
	
	@RequestMapping(value = "/drupal/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestParam(value="video") MultipartFile file, @RequestParam(value="id") String videoId) {
	/*	if (file == null)
		{
			System.out.println("File is NULL\n");
			return null;
		}*/
		Optional <Video> checkvideo = videoService.getVideo(videoId);
		if(checkvideo.isPresent())
		{
			return checkvideo.get().getTags();
		}
		
		
		return videoService.uploadFile(new Video(file.getOriginalFilename(), videoId), file);
	}
	@RequestMapping(value = "/drupal/videos/status/{id}", method = RequestMethod.GET)
	public String getVideoStatus(@PathVariable String id)
	{
		return videoService.getVideoStatus(id);
	}
	
	@RequestMapping(value = "/drupal/video/{id}", method = RequestMethod.GET)
	public Optional< Video> getVideo(@PathVariable String id)
	{
		return videoService.getVideo(id);
	}
	
	
}
