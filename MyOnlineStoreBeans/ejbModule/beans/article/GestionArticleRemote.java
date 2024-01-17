package beans.article;

import entities.Article;
import exception.SoldOutArticle;
import exception.UnknownArticle;
import exception.UnknownProduct;
import jakarta.ejb.Remote;

@Remote
public interface GestionArticleRemote {

    Article getArticle(String name) throws UnknownArticle;
    
    Article getArticle(int id) throws UnknownArticle;

	int addArticle(Article a) throws SoldOutArticle, UnknownProduct;

	void modifyArticle(Article a) throws UnknownArticle, UnknownProduct, SoldOutArticle;

	void deleteArticle(Article a) throws UnknownArticle, UnknownProduct;
	
}