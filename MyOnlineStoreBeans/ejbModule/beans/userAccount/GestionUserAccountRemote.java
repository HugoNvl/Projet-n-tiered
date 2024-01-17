package beans.userAccount;

import entities.UserAccount;
import exception.LoginUsed;
import exception.UnknownAccount;
import jakarta.ejb.Remote;

@Remote
public interface GestionUserAccountRemote {

	UserAccount getAccount(int id) throws UnknownAccount;

	UserAccount getAccount(String login) throws UnknownAccount;

	int createAccount(UserAccount account) throws LoginUsed;

	void modifyAccount(UserAccount account) throws UnknownAccount, LoginUsed;

	void deleteAccount(UserAccount account) throws UnknownAccount;

}
