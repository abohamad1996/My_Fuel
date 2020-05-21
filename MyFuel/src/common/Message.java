package common;

import java.io.Serializable;

public class Message implements Serializable {
	int code;
	Object object;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Message(int code, Object object) {
		this.code = code;
		this.object = object;
	}

	public int getCode() {
		return code;
	}

	public Object getObject() {
		return object;
	}
}
