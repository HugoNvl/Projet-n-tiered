package beans.cart;

import java.util.ArrayList;
import java.util.List;

import beans.article.GestionArticle;
import beans.order.GestionOrder;
import beans.userAccount.GestionUserAccount;
import dao.CartDAO;
import entities.Article;
import entities.Cart;
import entities.Order;
import exception.ArticleAlreadyInCart;
import exception.ArticleNotInCart;
import exception.IsEmptyCart;
import exception.UnknownArticle;
import exception.UnknownCart;
import exception.UnknownProduct;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateful;

/**
 * Session Bean implementation class GestionCart
 */
@Stateful
@LocalBean
public class GestionCart implements GestionCartRemote {

	@EJB
	CartDAO dao;
	
	@EJB
	GestionArticle gestArticle;
	
	@EJB
	GestionUserAccount gestAccount;
	
	@EJB
	GestionOrder gestOrder;
	
    /**
     * Default constructor. 
     */
    public GestionCart() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Cart getCart(int id) throws UnknownCart {
    	return dao.getCart(id);
    }
    
    @Override
    public int addCart(Cart c) {
    	dao.addCart(c);
    	return c.getId();
    }
    
    @Override
    public void modifyCart(Cart c) {
    	dao.modifyCart(c);
    }
    
    @Override
    public void isInCart(Article a, Cart cart) throws ArticleAlreadyInCart, UnknownCart {
    	Cart c = getCart(cart.getId());
    	List<Article> listArticle = c.getArticles();
    	for(Article a2 : listArticle) {
    		if(a2.getProduct().equals(a.getProduct())) throw new ArticleAlreadyInCart();
    	}
    }
    
    @Override
    public Article isNotInCart(Article a, Cart cart) throws ArticleNotInCart, UnknownCart {
    	Cart c = getCart(cart.getId());
    	List<Article> listArticle = c.getArticles();
    	for(Article a2 : listArticle) {
    		if(a2.getProduct().equals(a.getProduct())) return  a2;
    	}
    	throw new ArticleNotInCart();
    }
    

    public void addArticleToCart(Article a, Cart cart) throws ArticleAlreadyInCart, UnknownCart, UnknownArticle {
    	Cart c = getCart(cart.getId());
    	Article a2 = gestArticle.getArticle(a.getId());
    	isInCart(a2, c);
    	List<Article> listArticles = c.getArticles();
    	listArticles.add(a);
    	c.setArticles(listArticles);
    	dao.modifyCart(c);
    }
    
    @Override
    public void removeArticleFromCart(Article a, Cart cart) throws ArticleNotInCart, UnknownCart, UnknownArticle, UnknownProduct {
    	Cart c = getCart(cart.getId());
    	Article a2 = gestArticle.getArticle(a.getId());
    	Article a3 = isNotInCart(a2, c);
    	List<Article> listArticles = c.getArticles();
    	listArticles.remove(a3);
    	c.setArticles(listArticles);
    	dao.modifyCart(c);
    	gestArticle.deleteArticle(a3);
    }
    
    @Override
    public void validateCart(Cart cart) throws IsEmptyCart, UnknownCart {
    	Cart c = getCart(cart.getId());
    	List<Article> listArticles = c.getArticles();
    	if(listArticles.isEmpty()) throw new IsEmptyCart();
    	Order o = new Order();
    	o.setClient(c.getClient());
    	o.setArticles(listArticles);
    	gestOrder.addOrder(o);
    	c.setArticles(new ArrayList<Article>());
    	dao.modifyCart(c);
    }
    
    @Override
    public void emptyCart(Cart cart) throws UnknownCart {
    	Cart c = getCart(cart.getId());
    	c.setArticles(new ArrayList<Article>());
    	dao.modifyCart(c);
    }
    
}
