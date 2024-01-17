package dao;

import entities.Cart;
import exception.UnknownCart;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class ArticleDAO
 */
@Stateless
@LocalBean
public class CartDAO {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CartDAO() {
        // TODO Auto-generated constructor stub
    }

    public Cart getCart(int id) throws UnknownCart {
    	Cart c = em.find(Cart.class, id);
    	if(c == null) throw new UnknownCart();
    	return c;
    }
    
    public void addCart(Cart c) {
    	em.persist(c);
    }
    
    public void modifyCart(Cart c) {
    	em.merge(c);
    }
    
    public void deleteCart(Cart c) {
    	em.remove(c);
    }
}
