package Entity;

import java.io.Serializable;

public class maxbuy	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	int max_reb;
	public maxbuy(String id, int max_reb) {
		super();
		this.id = id;
		this.max_reb = max_reb;
	}
	@Override
	public String toString() {
		return "Client ID=" + id + ", Purchase Times:=" + max_reb;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMax_reb() {
		return max_reb;
	}
	public void setMax_reb(int max_reb) {
		this.max_reb = max_reb;
	}
	

	
}
