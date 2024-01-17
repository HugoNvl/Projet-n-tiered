package beans.order;

import entities.Order;
import exception.UnknownOrder;
import jakarta.ejb.Remote;

@Remote
public interface GestionOrderRemote {

	Order getOrder(int id) throws UnknownOrder;

	void addOrder(Order o);

	void modifyOrder(Order o) throws UnknownOrder;

	void deleteOrder(Order o) throws UnknownOrder;


}
