package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    //DEFAULT CONSTRUCTOR (NO PARAM)
    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    //ONE PARAM CONSTRUCTOR
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    
    //ADD AN ACCOUNT TO THE DATABASE
    public Account addAccount(Account account){
        Account accountFromDb = this.accountDAO.getAccountByUsername(account.getUsername());

        if(accountFromDb != null) return null;

        this.accountDAO.insertAccount(account);
        return this.accountDAO.getAccountByUsername(account.getUsername());
    }

    
}
