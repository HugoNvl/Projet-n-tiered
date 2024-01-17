package beans.product;

import dao.ProductDAO;
import entities.Product;
import exception.ProductAlreadyExists;
import exception.UnknownProduct;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionProduct
 */
@Stateless
@LocalBean
public class GestionProduct implements GestionProductRemote {

	@EJB
	ProductDAO dao;
	
    /**
     * Default constructor. 
     */
    public GestionProduct() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Product getProduct(String name) throws UnknownProduct {
    	return dao.getProduct(name);
    }
    
    @Override
    public Product getProduct(int id) throws UnknownProduct {
    	return dao.getProduct(id);
    }

    @Override
    public int addProduct(Product p) throws ProductAlreadyExists {
    	dao.existProduct(p.getName());
    	if(p.getStock() < 0) p.setStock(0);
    	dao.addProduct(p);
    	return p.getId();
    }
    
    @Override
    public void modifyProduct(Product p) throws UnknownProduct {
    	getProduct(p.getId());
    	dao.modifyProduct(p);
    }
}
