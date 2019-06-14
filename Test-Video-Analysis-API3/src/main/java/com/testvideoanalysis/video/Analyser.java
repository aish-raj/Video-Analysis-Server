package com.testvideoanalysis.video;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testvideoanalysis.path.PathManager;

@Service
public class Analyser {
	
	@Autowired
	PathManager pathManager;
	String tags = "";
	
	public String getTags(Video video) {
		
		String downloadPath = pathManager.getDownloadedVideos() + video.getVideoNameWithExtension();
		String jsonFilePath = pathManager.getJsonFiles() + video.getVideoName() + ".json";
        String tags = "";
		try {
            
        	System.out.println("Running python script..");
        	String command = "python3 " + pathManager.getPythonSrc() + "integrate.py " + downloadPath;
            System.out.println(command);
        	Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
           
            tags += new String(Files.readAllBytes(Paths.get(jsonFilePath))); 
           
        }
        
        
        catch (Exception e) {
            System.out.println("Exception occurred: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        return tags;
    }
	
}
