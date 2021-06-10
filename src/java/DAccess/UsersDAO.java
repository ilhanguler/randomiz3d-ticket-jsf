/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAccess;

import entity.Users;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.DBConnection;

/**
 *
 * @author ciger
 */
public class UsersDAO {

    public List<Users> findAll() {
        List<Users> userlist = new ArrayList();

        DBConnection db = new DBConnection();
        Connection c = db.connect();

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            while (rs.next()) {
                LocalDate tmpdate;
                if((rs.getDate("birthdate"))==null){
                    tmpdate=null;
                }else{tmpdate = rs.getDate("birthdate").toLocalDate();}
                Users user = new Users(rs.getLong("user_id"), rs.getString("username"), rs.getString("password"), 
                        rs.getInt("admin_privilige"), rs.getString("firstname"), rs.getString("lastname"), tmpdate);
                userlist.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userlist;
    }

    public void insert(Users user) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "insert into users (username, password, firstname, lastname) values (?,?,?,?)";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFirstname());
            st.setString(4, user.getLastname());
            //st.setDate(5, Date.valueOf(user.getBirthdate()));
            st.executeUpdate();

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Users user) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        String sql = "delete from users where user_id=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1, user.getUserId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Users findUser(Users user) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        Users getuser = new Users();
        String sql = "select from users where user_id=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setLong(1,user.getUserId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                getuser = new Users(rs.getLong("user_id"), rs.getString("username"), rs.getString("password"), rs.getInt("admin_privilige"), rs.getString("firstname"), rs.getString("lastname"), rs.getDate("birthdate").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getuser;
    }
    
    public Users loginUser(Users user) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        Users getuser = new Users();
        String sql = "select from users where username=? and password=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1,user.getUsername());
            st.setString(2, user.getPassword());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                getuser = new Users(rs.getLong("user_id"), rs.getString("username"), rs.getString("password"), rs.getInt("admin_privilige"), rs.getString("firstname"), rs.getString("lastname"), rs.getDate("birthdate").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getuser;
    }
    
    public boolean checkUser(Users user) {
        DBConnection db = new DBConnection();
        Connection c = db.connect();
        Users getuser = new Users();
        boolean check = false;
        String sql = "select from users where username=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1,user.getUsername());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
