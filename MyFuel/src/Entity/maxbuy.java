package Entity;

import java.io.Serializable;
/**
*This class created in order to help at organizing the information in flies
*and to find the client who buy the most
*Every maxbuy Characterized by id, max_reb
*
*/
public class maxbuy	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	int max_reb;
	/**
	* constructor and initializes object of type max_buy
	*
	* @param id- client ID
	* @param max_reb-number of max times buying
	*/
	public maxbuy(String id, int max_reb) {
		super();
		this.id = id;
		this.max_reb = max_reb;
	}
	/**
	*This method print object of type maxbuy  
	*/


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
