package dao;

import entities.Order;
import exception.UnknownOrder;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class ArticleDAO
 */
@Stateless
@LocalBean
public class OrderDAO {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public OrderDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public Order getOrder(int id) throws UnknownOrder {
    	Order o = em.find(Order.class, id);
    	if(o == null) throw new UnknownOrder();
    	return o;
    }
    
    public void addOrder(Order o) {
    	em.persist(o);
    }
    
    public void modifyOrder(Order o) {
    	em.merge(o);
    }
    
    public void deleteOrder(Order o) {
    	em.remove(o);
    }
    
}
