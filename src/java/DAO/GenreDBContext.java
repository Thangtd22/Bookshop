/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Genre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class GenreDBContext extends DBContext {
    public ArrayList<Genre> getAllGenre() throws SQLException{
        ArrayList<Genre> genre = new ArrayList<>();
        String sql = "select * from Genre";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            Genre g = new Genre(rs.getInt("genreID"),rs.getString("genreType"));
            genre.add(g);
        }
        return genre;
    }
    public static void main(String[] args) throws SQLException {
        GenreDBContext db = new GenreDBContext();
        ArrayList<Genre> genre = db.getAllGenre();
        for(Genre g : genre){
            System.out.println(g.getGenreType());
        }
    }
}
