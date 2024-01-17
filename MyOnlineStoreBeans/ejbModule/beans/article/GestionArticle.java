package beans.article;

import beans.cart.GestionCart;
import beans.product.GestionProduct;
import dao.ArticleDAO;
import entities.Article;
import entities.Product;
import exception.SoldOutArticle;
import exception.UnknownArticle;
import exception.UnknownProduct;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionArticle
 */
@Stateless
@LocalBean
public class GestionArticle implements GestionArticleRemote {

	@EJB
	ArticleDAO dao;
	
	@EJB
	GestionProduct gestProduct;
	
	@EJB
	GestionCart gestCart;
	
    /**
     * Default constructor. 
     */
    public GestionArticle() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Article getArticle(String name) throws UnknownArticle {
    	return dao.getArticle(name);
    }
    
    @Override
    public Article getArticle(int id) throws UnknownArticle {
    	return dao.getArticle(id);
    }
    
    @Override
    public int addArticle(Article a) throws SoldOutArticle, UnknownProduct {
    	/*Product p = gestProduct.getProduct(a.getProduct().getId());
    	int quantity = a.getQuantity();
    	if(quantity > p.getStock()) throw new SoldOutArticle();
    	p.setStock(p.getStock() - quantity);
    	gestProduct.modifyProduct(p);*/
    	dao.addArticle(a);
    	return a.getId();
    }
    
    @Override
    public void modifyArticle(Article a) throws UnknownArticle, UnknownProduct, SoldOutArticle {
    	Article a2 = dao.getArticle(a.getId());
    	Product p = gestProduct.getProduct(a.getProduct().getId());
    	int quantity = a.getQuantity();
    	if(quantity > (p.getStock() + a2.getQuantity())) throw new SoldOutArticle();
    	p.setStock(p.getStock() + a2.getQuantity() - quantity);
    	gestProduct.modifyProduct(p);
    	dao.modifyArticle(a);
    }
    
    @Override
    public void deleteArticle(Article a) throws UnknownArticle, UnknownProduct {
    	Article a2 = dao.getArticle(a.getId());
    	Product p = gestProduct.getProduct(a.getProduct().getId());
    	p.setStock(p.getStock() + a2.getQuantity());
    	gestProduct.modifyProduct(p);
    	dao.deleteArticle(a);
    }
}
