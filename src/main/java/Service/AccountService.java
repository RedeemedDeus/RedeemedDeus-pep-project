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

    
    /*
     * ADD AN ACCOUNT TO THE DATABASE
     * USERNAME MUST NOT BE BLANK, PASSWORD MUST BE AT LEAST 4 CHARACTERS, USERNAME MUST BE UNIQUE(NO DUPLICATES)
     */
    public Account addAccount(Account account){
        //returns null if no account was found(good), returns an account if one was found(bad)
        Account accountFromDb = this.accountDAO.getAccountByUsername(account.getUsername());

        if(account.getUsername() == "") return null;
        else if(account.getPassword().length() < 4) return null;
        else if(accountFromDb != null) return null;

        this.accountDAO.insertAccount(account);
        return this.accountDAO.getAccountByUsername(account.getUsername());
    }

    
}
