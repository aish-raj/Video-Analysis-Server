package com.testvideoanalysis.video;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testvideoanalysis.path.PathManager;
import com.testvideoanalysis.repository.VideoRepository;

@Service
public class Analyser {
	
	@Autowired
	PathManager pathManager;
	String tags = "";
	@Autowired
	VideoRepository videorepository;
	
	public String getTags(Video video) {
		
		String downloadPath = pathManager.getDownloadedVideos() + video.getVideoNameWithExtension();
		String jsonFilePath = pathManager.getJsonFiles() + video.getVideoName() + ".json";
        String tags = "";
		try {
            
        	System.out.println("Running python script..");
        	String command = "python3 " + pathManager.getPythonSrc() + "integrate.py " + downloadPath;
            System.out.println(command);
           // video.setStatus("Processing");
        	Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            video.setStatus("Finished");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            System.out.println("value is : "+ret);
           
            tags += new String(Files.readAllBytes(Paths.get(jsonFilePath))); 
            
         // video.setTags(tags);
            return tags;
           
        }
        
        
        catch (Exception e) {
            System.out.println("Exception occurred: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        return tags;
    }
	
}
