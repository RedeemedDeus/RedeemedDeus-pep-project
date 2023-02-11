package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    /*
     * INSERTS A NEW ACCOUNT INTO THE DATABASE
     * PARAM: account The account to be inserted into the databse
     * RETURN: The new account if it was persisted, null otherwise
     */
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here, account_id is an auto_increment field in the account table in sql
            String sql = "INSERT INTO account(username,password) VALUES(?,?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
            preparedStatement.executeUpdate();
            
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_author_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    
    /*
     * CHECKS FOR A SPECIFIC ACCOUNT BY ITS USERNAME
     * PARAM: THE TARGET USERNAME
     * RETURN: THE ACCOUNT MATCHING THE USERNAME IF FOUND, NULL OTHERWISE
     */
    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE username = ?;";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /*
     * CHECKS FOR A SPECIFIC ACCOUNT BY ITS account_id
     * PARAM: THE TARGET ACCOUNT ID
     * RETURN: THE ACCOUNT MATCHING THE ACCOUNT ID IF FOUND, NULL OTHERWISE
     */
    public Account getAccountById(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE account_id = ?;";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }


    /*
     * VALIDATES A USER LOGIN USING A PASSWORD AND USERNAME PROVIDED
     * PARAM: USERNAME and PASSWORD of the person trying to login
     * RETURN: ACCOUNT of the person trying to login, NULL if the action was unssuccessful
     */
    public Account getLoginAccount(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
