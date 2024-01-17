package dao;

import java.util.List;

import entities.UserAccount;
import exception.LoginUsed;
import exception.UnknownAccount;
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
public class UserAccountDAO {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserAccountDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public UserAccount getAccount(int id) throws UnknownAccount {
    	UserAccount acc = em.find(UserAccount.class, id);
    	if(acc == null) throw new UnknownAccount();
    	return acc;
    }
    
    public UserAccount getAccount(String login) throws UnknownAccount {
    	TypedQuery<UserAccount> q = em.createQuery("select a from UserAccount a where a.login = '"+ login +"'", UserAccount.class);
		if (q.getResultList().size()>0) return q.getResultList().get(0);
		else throw new UnknownAccount();
    }
    
    public void existsLogin(UserAccount acc) throws LoginUsed {
    	TypedQuery<UserAccount> q = em.createQuery("select a from UserAccount a", UserAccount.class);
		List<UserAccount> listAccount = q.getResultList();
		for(UserAccount u : listAccount) {
			if(u.getLogin().equals(acc.getLogin())) throw new LoginUsed();
		}
    }
    
    public void addAccount(UserAccount account) {
    	em.persist(account);
    }
    
    public void modifyAccount(UserAccount account) {
    	em.merge(account);
    }
    
    public void deleteAccount(UserAccount account) {
    	em.remove(account);
    }

}
