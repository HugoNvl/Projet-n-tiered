package beans.order;

import dao.OrderDAO;
import entities.Order;
import exception.UnknownOrder;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionOrder
 */
@Stateless
@LocalBean
public class GestionOrder implements GestionOrderRemote {

	@EJB
	OrderDAO dao;
	
    /**
     * Default constructor. 
     */
    public GestionOrder() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Order getOrder(int id) throws UnknownOrder {
    	return dao.getOrder(id);
    }
    
    @Override
    public void addOrder(Order o) {
    	dao.addOrder(o);
    }
    
    @Override
    public void modifyOrder(Order o) throws UnknownOrder {
    	getOrder(o.getId());
    	dao.modifyOrder(o);
    }

    @Override
    public void deleteOrder(Order o) throws UnknownOrder {
    	getOrder(o.getId());
    	dao.deleteOrder(o);
    }
}
