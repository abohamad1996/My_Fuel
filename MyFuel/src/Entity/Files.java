package Entity;

import java.io.Serializable;
/**
*This class represents the report files
*Every file characterized by id, filename, path, status
*
*/
public class Files implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String filename;
	String path;
	String status;
	/**
	*This method print object of type file
	*
	*/
	@Override
	public String toString() {
		return "Files [id=" + id + ", filename=" + filename + ", path=" + path + ", status=" + status + "]";
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	* constructor and initializes file 
	*
	* @param id file id
	* @param filename file name
	* @param path file path to were it stored
	* @param status file status
	*/
	public Files(int id, String filename, String path, String status) {
		super();
		this.id = id;
		this.filename = filename;
		this.path = path;
		this.status = status;
	}
	
	
	
	
	

}
