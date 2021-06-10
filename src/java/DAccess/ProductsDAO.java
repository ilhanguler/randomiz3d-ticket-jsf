/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAccess;

import entity.Products;
import entity.Sales;
import entity.Users;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.DBConnection;

/**
 *
 * @author ciger
 */
public class ProductsDAO {

    public List<Products> findAll() {
        List<Products> productlist = new ArrayList();
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from products");
            while (rs.next()) {
                Products product = new Products(rs.getDate("event_date").toLocalDate(), rs.getDate("available_date").toLocalDate(),
                        rs.getDate("created_date").toLocalDate(), rs.getDouble("price"), rs.getString("event_name"), rs.getString("event_description"), rs.getLong("product_id"));
                productlist.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productlist;
    }
    
    public void insert(Products product) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "insert into products (event_date, available_date, price, event_name, event_description) values (?,?,?,?,?)";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setDate(1, Date.valueOf(LocalDate.now()));
            st.setDate(2, Date.valueOf(LocalDate.now()));
            st.setDouble(3, product.getPrice());
            st.setString(4, product.getEventName());
            st.setString(5, product.getEventDescription());
            st.executeUpdate();
            
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Products product) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "delete from products where product_id=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1, product.getProductId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Products> findProduct(Products product) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        List<Products> getproduct = new ArrayList();
        String sql = "select from products where (available_date>?) or (event_date=?) or (event_description=?)";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            if(product.getAvailableDate()!=null){
                product.setAvailableDate(LocalDate.MAX);
            }
            if(product.getEventDate()!=null){
                product.setEventDate(LocalDate.MAX);
            }
            if(product.getEventDescription()!=null){
                product.setEventDescription("");
            }
            st.setDate(1, Date.valueOf(product.getAvailableDate()));
            st.setDate(2, Date.valueOf(product.getEventDate()));
            st.setString(3, product.getEventDescription());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products tmp = new Products();
                getproduct.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getproduct;
    }
}
