package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: UserAccount
 *
 */
@Entity
public class UserAccount implements Serializable {
	   
	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE)
	private Cart myCart;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
	private List<Order> myOrders;
	
	private String firstName;
	private String name;
	private String login;
	private String password;

	
	public UserAccount() {
		super();
	}   
	
	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}


	public Cart getCart() {
		return myCart;
	}
	
	public void setCart(Cart cart) {
		this.myCart = cart;
	}
	
	
	public List<Order> getOrders() {
		return myOrders;
	}
	
	public void setOrders(List<Order> order) {
		this.myOrders = order;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
