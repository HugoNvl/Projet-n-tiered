import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.article.GestionArticleRemote;
import beans.cart.GestionCartRemote;
import beans.product.GestionProductRemote;
import beans.userAccount.GestionUserAccountRemote;
import entities.Article;
import entities.Cart;
import entities.Product;
import entities.UserAccount;

public class Main {

	Context context;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.test5();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.SECURITY_PRINCIPAL,"user");
	    jndiProperties.put(Context.SECURITY_CREDENTIALS, "user");
	    jndiProperties.put("jboss.naming.client.ejb.context", true);
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void test1() {
		
	    try {
			
			GestionProductRemote gestionProduct = (GestionProductRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionProduct!beans.product.GestionProductRemote");
			
			Product p = new Product();
			
			p.setName("Ordinateur");
			p.setStock(5);
			
			p.setId(gestionProduct.addProduct(p));
			
			System.out.println(p.getId());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void test2() {
		
	    try {
			
			GestionProductRemote gestionProduct = (GestionProductRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionProduct!beans.product.GestionProductRemote");
			
			Product p = new Product();
			
			p.setName("Clavier");
			p.setStock(2);
			
			p.setId(gestionProduct.addProduct(p));
			
			System.out.println(p.getId());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void test3() {
		
	    try {
			
			GestionUserAccountRemote gestionAccount = (GestionUserAccountRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionUserAccount!beans.userAccount.GestionUserAccountRemote");
			
			UserAccount u = new UserAccount();
			
			u.setFirstName("Hugo");
			u.setName("Nouvellon");
			u.setLogin("login");
			u.setPassword("password");
			
			u.setId(gestionAccount.createAccount(u));
			
			System.out.println(u.getId());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void test4() {
		
	    try {
			
			GestionUserAccountRemote gestionAccount = (GestionUserAccountRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionUserAccount!beans.userAccount.GestionUserAccountRemote");
			GestionCartRemote gestionCart = (GestionCartRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionCart!beans.cart.GestionCartRemote");
			
			Cart c = new Cart();
			UserAccount u = gestionAccount.getAccount(1);
			
			c.setClient(u);
			
			c.setId(gestionCart.addCart(c));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void test5() {
		
	    try {
			
			GestionArticleRemote gestionArticle = (GestionArticleRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionArticle!beans.article.GestionArticleRemote");
			GestionProductRemote gestionProduct = (GestionProductRemote)context.lookup("MyOnlineStore/MyOnlineStoreBeans/GestionProduct!beans.product.GestionProductRemote");
			
			Product p = gestionProduct.getProduct("Clavier");
			
			Article a = new Article();
			
			a.setProduct(p);
			a.setQuantity(2);
			
			a.setId(gestionArticle.addArticle(a));
			
			System.out.println(a.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}