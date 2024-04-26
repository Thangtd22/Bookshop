/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Context.DBContext;

/**
 *
 * @author DELL
 */
public class Cart extends DBContext {
    Books book;
    int quantity;

    public Cart() {
    }

    public Cart(Books book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
