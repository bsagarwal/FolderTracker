package com.foldertracker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.foldertracker.model.FileFolderStatus;

public class FileAppender {

	public void appendDataIntoFile(String monitorFolder,List<FileFolderStatus> fileFolderStatusList) throws IOException {
		System.out.println(">>>>>>>>> here");
		FileWriter fw = null;
		try {
			Date date = new Date();
			String filename = "Track-" + "16-02-2016" + ".txt";
			File file = new File(monitorFolder + filename);

			if(!file.exists()){
				file.createNewFile();
			}
			
			// the true will append the new data
			fw = new FileWriter(file, true); 
   
			for(FileFolderStatus ffs : fileFolderStatusList){
				
				fw.write(ffs.toString());
			}
			
			fw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(null != fw){
				fw.close();
			}
		}
	}
}
