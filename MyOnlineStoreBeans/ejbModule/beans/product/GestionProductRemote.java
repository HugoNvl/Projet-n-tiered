package beans.product;

import entities.Product;
import exception.ProductAlreadyExists;
import exception.UnknownProduct;
import jakarta.ejb.Remote;

@Remote
public interface GestionProductRemote {

	Product getProduct(String name) throws UnknownProduct;

	Product getProduct(int id) throws UnknownProduct;

	int addProduct(Product p) throws ProductAlreadyExists;

	void modifyProduct(Product p) throws UnknownProduct;

}
