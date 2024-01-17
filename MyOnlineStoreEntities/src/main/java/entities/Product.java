package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
public class Product implements Serializable {
  
	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Article> articles;
	
	private String name;
	private int stock;
	
	
	public Product() {
		super();
	}   
	
	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}


	public List<Article> getArticles() {
		return articles;
	}
	
	public void setArticles(List<Article> list_articles) {
		this.articles = list_articles;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
   
}
