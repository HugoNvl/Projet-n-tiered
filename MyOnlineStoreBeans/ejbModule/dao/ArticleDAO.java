package dao;

import entities.Article;
import exception.UnknownArticle;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class ArticleDAO
 */
@Stateless
@LocalBean
public class ArticleDAO {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ArticleDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public Article getArticle(String name) throws UnknownArticle {
    	TypedQuery<Article> q = em.createQuery("select a from Article a where a.product.name = '"+ name +"'", Article.class);
		if (q.getResultList().size()>0) return q.getResultList().get(0);
		else throw new UnknownArticle();
    }
    
    public Article getArticle(int id) throws UnknownArticle {
    	Article a = em.find(Article.class, id);
		if (a == null) throw new UnknownArticle();
		return a;
    }
    
    public void addArticle(Article a) {
    	em.persist(a);
    }
    
    public void modifyArticle(Article a) {
    	em.merge(a);
    }
    
    public void deleteArticle(Article a) {
    	em.remove(a);
    }
}
