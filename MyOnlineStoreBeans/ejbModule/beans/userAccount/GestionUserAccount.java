package beans.userAccount;

import beans.cart.GestionCart;
import dao.UserAccountDAO;
import entities.Cart;
import entities.UserAccount;
import exception.LoginUsed;
import exception.UnknownAccount;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GestionUserAccount
 */
@Stateless
@LocalBean
public class GestionUserAccount implements GestionUserAccountRemote {

	@EJB
	UserAccountDAO dao;
	
	@EJB
	GestionCart gestCart;
	
    /**
     * Default constructor. 
     */
    public GestionUserAccount() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public UserAccount getAccount(int id) throws UnknownAccount {
    	return dao.getAccount(id);
    }
    
    @Override
    public UserAccount getAccount(String login) throws UnknownAccount {
    	return dao.getAccount(login);
    }
    
    @Override
	public int createAccount(UserAccount account) throws LoginUsed {
		dao.existsLogin(account);
    	/*Cart c = new Cart();
    	gestCart.addCart(c);*/
		dao.addAccount(account);
		/*c.setClient(account);
		gestCart.modifyCart(c);*/
		return account.getId();
	}

    @Override
	public void modifyAccount(UserAccount account) throws UnknownAccount, LoginUsed {
    	getAccount(account.getId());
    	dao.existsLogin(account);
		dao.modifyAccount(account);
	}
	
    @Override
	public void deleteAccount(UserAccount account) throws UnknownAccount {
		getAccount(account.getId());
		dao.deleteAccount(account);
	}
}
