# Name  
Video Analysis API.        

## Description
Video Analysis API is an API built using Springboot and Python. The springboot accepts a video of a person and returns with a JSON response of tags such as gender and emotions. The tags are the output of a python script.     
[**NOTE**: The project is still under development and further tags like age, etc need to be added.]

## Directory Structure
Test-Video-Analysis-API is the root of the project.  
- **src**: contains the springboot project.  
- **python-src** 
   - integrate.py is the main script that returns the JSON tags.

- **json-files**: contains .json files for each tag.  
- **downloaded-videos**: contains downloaded videos sent from client.  
- **pom.xml**: contains the Maven dependencies for springboot.

## Usage
Clone the repository using git clone.  
Open video-analysis-server/Test-Video-Analysis-API/ as an Eclipse/ STS project.  
Run the server. [The Server should be up and running!].
