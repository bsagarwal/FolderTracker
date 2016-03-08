package com.foldertracker.main;

import java.io.IOException;

import com.foldertracker.service.FolderTrackerService;

public class Main {

	public static void main(String[] args) {
		String monitorFolder = "D:/Workspace/Temp/Testt";
		
		FolderTrackerService fts = new FolderTrackerService();
		
		try {
			fts.startTrackingService(monitorFolder);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally{
			
		}
	}
}
