package entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity
public class Cart extends ProductSelection implements Serializable {

	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@OneToOne(optional = false)
	private UserAccount client;

	
	public Cart() {
		super();
	} 
	
	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}  
	
	
	public UserAccount getClient() {
		return this.client;
	}

	public void setClient(UserAccount client) {
		this.client = client;
	}
   
}
