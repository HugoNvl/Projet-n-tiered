package entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
public class Order extends ProductSelection implements Serializable {

	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false) 
	private UserAccount client;

	
	public Order() {
		super();
	}   
	
	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
   
	
	public UserAccount getClient() {
		return client;
	}
	
	public void setClient(UserAccount user) {
		this.client = user;
	}
}
