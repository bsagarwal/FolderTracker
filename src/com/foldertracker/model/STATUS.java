package com.foldertracker.model;

public enum STATUS {
	CREATE("CREATE"),MODIFY("MODIFY"),DELETE("DELETE");
	
	String name;
	
	STATUS(String name){
		this.name = name;
	}
	
	String getName(){
		return name;
	}
}
