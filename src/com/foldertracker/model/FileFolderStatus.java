package com.foldertracker.model;

import java.util.Date;

/**
 * Model to hold details about file or folder.
 *  
 * @author bibhuti_agarwal
 * @version 1.0
 */
public class FileFolderStatus {
	/**
	 * holds name of file or folder.
	 */
	private String name;
	
	/**
	 * holds type as folder or file.
	 */
	private String type;
	
	/**
	 * holds dates of creation/modification/deletion.
	 */
	private Date date;
	
	/**
	 * holds path of file/folder.
	 */
	private String path;

	/**
	 * state can be CREATE|MODIFY|DELETE
	 */
	private String state;
	
	/**
	 * @param name name of file or folder.
	 * @param type type as folder or file.
	 * @param date dates of creation/modification/deletion.
	 * @param path path of file/folder.
	 */
	public FileFolderStatus(String name, String type, String path,STATUS status) {
		super();
		this.name = name;
		this.type = type;
		this.date = new Date();
		this.path = path;
		this.state = status.getName();
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileFolderStatus [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", date=");
		builder.append(date);
		builder.append(", path=");
		builder.append(path);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
}
