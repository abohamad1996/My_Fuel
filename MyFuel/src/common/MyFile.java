package common;

import java.io.Serializable;

public class MyFile implements Serializable {
	/**
	 *  this method is for files details and sending and recieving files and save file data
	 */
	private String Description=null;
	private String fileName=null;	
	private int size=0;
	public  byte[] mybytearray;
	
	/**
	 * this method for init the size of files
	 * @param size the size of file
	 */
	public void initArray(int size)
	{
		mybytearray = new byte [size];	
	}
	/**
	 * 
	 * this is the files name
	 * @param fileName this parameter for saving file name
	 */ 
	public MyFile( String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 
	 * 
	 * @return file name
	 */
	public String getFileName() {
		return fileName;
	}
/**
 * 
 * @param fileName set file name
 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 * 
	 * @return file size
	 */
	public int getSize() {
		return size;
	}
/**
 * 
 * @param size set size
 */
	public void setSize(int size) {
		this.size = size;
	}
/**
 * 
 * @return array of byte files
 */
	public byte[] getMybytearray() {
		return mybytearray;
	}
	/**
	 * 
	 * 
	 * @param i  for move in the file
	 * @return the byte in files with place i
	 */
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}
/**
 * 
 * 
 * @param mybytearray save the bytes from files by i 
 */
	public void setMybytearray(byte[] mybytearray) {
		
		for(int i=0;i<mybytearray.length;i++)
		this.mybytearray[i] = mybytearray[i];
	}
/**
 * 
 * @return file Description
 */
	public String getDescription() {
		return Description;
	}
/**
 * 
 * 
 * @param description set file Description
 */
	public void setDescription(String description) {
		Description = description;
	}	
}

