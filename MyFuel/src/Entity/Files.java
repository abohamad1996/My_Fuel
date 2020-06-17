package Entity;

import java.io.Serializable;

public class Files implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String filename;
	String path;
	
	
	
	@Override
	public String toString() {
		return "Files [id=" + id + ", filename=" + filename + ", path=" + path + "]";
	}
	public Files(int id, String filename, String path) {
		super();
		this.id = id;
		this.filename = filename;
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
