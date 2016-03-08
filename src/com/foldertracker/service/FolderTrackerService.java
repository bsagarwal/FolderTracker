package com.foldertracker.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import com.foldertracker.model.FileFolderStatus;
import com.foldertracker.model.STATUS;
import com.foldertracker.util.FileAppender;

public class FolderTrackerService {
	private List<FileFolderStatus> fileFolderStatusList;
	
	/**
	 * default constructor.
	 */
	public FolderTrackerService() {
		super();
		fileFolderStatusList = new ArrayList<FileFolderStatus>();
	}

	/**
	 * @param monitorFolder folder path.
	 * @throws IOException in case of any IO exception.
	 * @throws InterruptedException in case of interrupt. 
	 */
	public void startTrackingService(String monitorFolder) throws IOException, InterruptedException{
		Path monitor = Paths.get(monitorFolder);
		WatchService watchService = FileSystems.getDefault().newWatchService();
		
		monitor.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, 
				                    StandardWatchEventKinds.ENTRY_MODIFY, 
				                    StandardWatchEventKinds.ENTRY_DELETE);
		
		boolean valid = true;
		boolean flag;
		
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Kind<?> kind = event.kind();
				flag = false;
				FileFolderStatus ffs = null;
				String fileName = event.context().toString();
				
				File file = null;
				
				if(!fileName.startsWith("Track-")){
					file = new File(monitorFolder + event.context().toString());	
				}
				
				if (null != file && StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
					flag = true;
					ffs = new FileFolderStatus(file.getName(), true==file.isFile()?"FILE":"FOLDER", file.getPath(), STATUS.CREATE);
				} else if (null != file && StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
					flag = true;
					ffs = new FileFolderStatus(file.getName(), true==file.isFile()?"FILE":"FOLDER", file.getPath(), STATUS.MODIFY);
				}else if (null != file && StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
					flag = true;
					ffs = new FileFolderStatus(file.getName(), true==file.isFile()?"FILE":"FOLDER", file.getPath(), STATUS.DELETE);
				}

				System.out.println(">>>>>>>>>> " + fileFolderStatusList);
				
				if(flag){
					flag = false;
					fileFolderStatusList.add(ffs);
				}
			}
			
			if( fileFolderStatusList.size() > 0){
				new FileAppender().appendDataIntoFile(monitorFolder, fileFolderStatusList);
				fileFolderStatusList.clear();
			}
			
			valid = watchKey.reset();

		} while (valid);

	}
}
