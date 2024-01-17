package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: ProductSelection
 *
 */
@Entity
public class ProductSelection implements Serializable {

	@Id
	@GeneratedValue
	private int Id;
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "productSelection", cascade = CascadeType.REMOVE)
	private List<Article> articles;
	
	
	public ProductSelection() {
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
}
