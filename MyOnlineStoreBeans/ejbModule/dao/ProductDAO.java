package dao;

import entities.Product;
import exception.ProductAlreadyExists;
import exception.UnknownProduct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class ArticleDAO
 */
@Stateless
@LocalBean
public class ProductDAO {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ProductDAO() {
        // TODO Auto-generated constructor stub
    }

    public Product getProduct(String name) throws UnknownProduct {
		// TODO Auto-generated method stub
		TypedQuery<Product> q = em.createQuery("select p from Product p where p.name = '"+ name +"'", Product.class);
		if (q.getResultList().size()>0) return q.getResultList().get(0);
		else throw new UnknownProduct();
	}
    
    public Product getProduct(int id) throws UnknownProduct {
		// TODO Auto-generated method stub
		Product p = em.find(Product.class, id);
		if (p == null) throw new UnknownProduct();
		return p;
	}
    
    public void existProduct(String name) throws ProductAlreadyExists {
		// TODO Auto-generated method stub
		TypedQuery<Product> q = em.createQuery("select p from Product p where p.name = '"+ name +"'", Product.class);
		if (q.getResultList().size()>0) throw new ProductAlreadyExists();
	}
    
    public void existProduct(int id) throws ProductAlreadyExists {
		// TODO Auto-generated method stub
		Product p = em.find(Product.class, id);
		if (p != null) throw new ProductAlreadyExists();
	}
    
    public void addProduct(Product p) {
    	em.persist(p);
    }
    
    public void modifyProduct(Product p) {
    	em.merge(p);
    }
}
