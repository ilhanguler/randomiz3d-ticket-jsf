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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.DBConnection;
/**
 *
 * @author ciger
 */
public class SalesDAO {
    
     public List<Sales> findAll() {
        List<Sales> salelist = new ArrayList();

        DBConnection db = new DBConnection();
        Connection c = db.connect();

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from sales");
            while (rs.next()) {
                Sales sale = new Sales(rs.getLong("sale_id"),rs.getLong("user_id"),rs.getDate("purchase_date").toLocalDate(),rs.getLong("product_id"),rs.getInt("quantity"),rs.getDouble("price"));
                salelist.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salelist;
    }

    public void insert(Sales sale) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "insert into sales (user_id, product_id, quantity, price) values (?,?,?,?)";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1, sale.getUserId());
            st.setLong(2, sale.getProductId());
            st.setInt(3, sale.getQuantity());
            st.setDouble(4, sale.getPrice());
            st.executeUpdate();

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Sales sale) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "delete from sales where sale_id=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1, sale.getSaleId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Sales> findSale(Users user) {
        List<Sales> salelist = new ArrayList();
        
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "select from sales where user_id=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1, user.getUserId());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Sales getsale = new Sales();
                salelist.add(getsale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salelist;
    }
}
