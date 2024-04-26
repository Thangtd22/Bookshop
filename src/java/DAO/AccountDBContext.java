/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class AccountDBContext extends DBContext {

    public ArrayList<Account> getAllAccount() throws SQLException {
        ArrayList<Account> account = new ArrayList<>();
        String sql = "select * from Account";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Account a = new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"), rs.getBoolean("active"));
            account.add(a);
        }
        return account;
    }

    public Account getAccountByLogin(String username, String password) throws SQLException {
        String sql = "select * from Account where [user] = ? and pass = ? ";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Account a = new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"), rs.getBoolean("active"));
            return a;
        }
        return null;
    }

    public Account checkAccountExist(String user) throws SQLException {
        String sql = "select * from Account where [user] = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Account a = new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"), rs.getBoolean("active"));
            return a;
        }
        return null;
    }

    public void signUpAccount(String user, String pass) throws SQLException {
        String sql = "INSERT INTO [Account]\n"
                + "           ([user]\n"
                + "           ,[pass]\n"
                + "           ,[isSell]\n"
                + "           ,[isAdmin]\n"
                + "           ,[active])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,0\n"
                + "           ,1)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
        stm.executeUpdate();
    }

    public Account getAccountById(String uID) throws SQLException {
        String sql = "select * from Account where uID = ? ";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, uID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Account a = new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"), rs.getBoolean("active"));
            return a;
        }
        return null;
    }

    public void updateAccount(Account account) throws SQLException {
        String sql = "UPDATE [Account]\n"
                + "   SET [active] = ?\n"
                + " WHERE uID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setBoolean(1, account.getActive());
        stm.setInt(2, account.getuID());
        stm.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        AccountDBContext db = new AccountDBContext();
        ArrayList<Account> account = db.getAllAccount();
        for (Account a : account) {
            System.out.println(a.getActive());
        }
    }
}
