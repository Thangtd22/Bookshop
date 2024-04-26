/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Books;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class BooksDBContext extends DBContext {

    public ArrayList<Books> getAllBooks() throws SQLException {
        ArrayList<Books> books = new ArrayList<>();
        String sql = "select * from Books";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt("bookID"), rs.getString("name"), rs.getString("title"), rs.getInt("genreID"), rs.getString("imageURL"), rs.getString("description"), rs.getDouble("price"), rs.getInt("sell_ID"));
            books.add(b);
        }
        return books;
    }

    public Books getBookByID(int bookID) throws SQLException {
        String sql = "select * from Books where bookID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, bookID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt("bookID"), rs.getString("name"), rs.getString("title"), rs.getInt("genreID"), rs.getString("imageURL"), rs.getString("description"), rs.getDouble("price"), rs.getInt("sell_ID"));
            return b;
        }
        return null;
    }

    public ArrayList<Books> getBooksRelated(int genreID) throws SQLException {
        ArrayList<Books> books = new ArrayList<>();
        String sql = "select * from Books where genreID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, genreID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt("bookID"), rs.getString("name"), rs.getString("title"), rs.getInt("genreID"), rs.getString("imageURL"), rs.getString("description"), rs.getDouble("price"), rs.getInt("sell_ID"));
            books.add(b);
        }
        return books;
    }

    public ArrayList<Books> getBookByName(String name) throws SQLException {
        ArrayList<Books> books = new ArrayList<>();
        String sql = "select * from Books where name like ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt("bookID"), rs.getString("name"), rs.getString("title"), rs.getInt("genreID"), rs.getString("imageURL"), rs.getString("description"), rs.getDouble("price"), rs.getInt("sell_ID"));
            books.add(b);
        }
        return books;
    }

    public ArrayList<Books> getBooksByuID(int uID) throws SQLException {
        ArrayList<Books> books = new ArrayList<>();
        String sql = "select * from Books where sell_ID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, uID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Books b = new Books(rs.getInt("bookID"), rs.getString("name"), rs.getString("title"), rs.getInt("genreID"), rs.getString("imageURL"), rs.getString("description"), rs.getDouble("price"), rs.getInt("sell_ID"));
            books.add(b);
        }
        return books;
    }

    public void addBook(String name, String title, String genreID, String imageURL, String description, String price, int sell_ID) throws SQLException {
        String sql = "insert into Books ([name], [title], [genreID], [imageURL], [description], [price], [sell_ID]) values (?,?,?,?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, title);
        stm.setString(3, genreID);
        stm.setString(4, imageURL);
        stm.setString(5, description);
        stm.setString(6, price);
        stm.setInt(7, sell_ID);
        stm.executeUpdate();
    }

    public void deleteBooks(int bookID) throws SQLException {
        String sql = "delete from Books where bookID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, bookID);
        stm.executeUpdate();
    }

    public void editBook(String name, String title, String genreID, String imageURL, String description, String price, String bookID) throws SQLException {
        String sql = "update Books set [name] = ?, [title]= ?, [genreID]= ?, [imageURL]= ?, [description]= ?, [price]= ? where bookID = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, title);
        stm.setString(3, genreID);
        stm.setString(4, imageURL);
        stm.setString(5, description);
        stm.setString(6, price);
        stm.setString(7, bookID);
        stm.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        BooksDBContext db = new BooksDBContext();
        ArrayList<Books> books = db.getBookByName("a");
        for (Books b : books) {
            System.out.println(b.getName());
        }
        Books d = db.getBookByID(1);
        System.out.println(d.getDescription());
    }
}
