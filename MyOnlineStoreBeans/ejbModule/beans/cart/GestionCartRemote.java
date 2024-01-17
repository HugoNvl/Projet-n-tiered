package beans.cart;

import entities.Article;
import entities.Cart;
import exception.ArticleAlreadyInCart;
import exception.ArticleNotInCart;
import exception.IsEmptyCart;
import exception.UnknownArticle;
import exception.UnknownCart;
import exception.UnknownProduct;
import jakarta.ejb.Remote;

@Remote
public interface GestionCartRemote {

	Cart getCart(int id) throws UnknownCart;
	
	int addCart(Cart c);

	void isInCart(Article a, Cart cart) throws ArticleAlreadyInCart, UnknownCart;

	Article isNotInCart(Article a, Cart cart) throws ArticleNotInCart, UnknownCart;

	void removeArticleFromCart(Article a, Cart cart)
			throws ArticleNotInCart, UnknownCart, UnknownArticle, UnknownProduct;

	void validateCart(Cart cart) throws IsEmptyCart, UnknownCart;

	void emptyCart(Cart cart) throws UnknownCart;

	void modifyCart(Cart c);

}
