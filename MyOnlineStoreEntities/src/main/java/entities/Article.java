package entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Article
 *
 */
@Entity
public class Article implements Serializable {
   
	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private ProductSelection productSelection;
	
	@ManyToOne(optional = false)
	private Product product;
	
	private int quantity;
	
	
	public Article() {
		super();
	}   
	
	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public ProductSelection getProductSelection() {
		return productSelection;
	}
	
	public void setProductSelection(ProductSelection selection) {
		this.productSelection = selection;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
   
}
