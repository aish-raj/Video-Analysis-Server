package com.testvideoanalysis.video;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestParam(value="video") MultipartFile file, @RequestParam(value="id") String videoId) {
		if (file == null)
		{
			System.out.println("File is NULL\n");
			return null;
		}
		
		return videoService.uploadFile(new Video(file.getOriginalFilename(), videoId), file);
	}
	
}
