/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DAccess.UsersDAO;
import entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ciger
 */
@Named(value = "usersController")
@SessionScoped
public class UsersBean implements Serializable {

    private List<Users> userlist;
    private UsersDAO userda;
    private Users singleuser;

    // return statementlar düzenlenecek
    public String login() {
        Users tmp = findforlogin();
        if (this.singleuser.getUsername().equals(tmp.getUsername()) && this.singleuser.getPassword().equals(tmp.getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.singleuser);
            return "/secret/secret?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı kullanici adi ve sifre"));
            return "/login";
        }
    }

    // return statementlar düzenlenecek
    public String signup() {
        if (!checkuser(singleuser)) {
            insertforsignup();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.singleuser);
            return "/secret/secret?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı adı kullanımda"));
            return "/login";
        }
    }

    public UsersBean(List<Users> userlist, UsersDAO userda, Users singleuser) {
        this.userlist = userlist;
        this.userda = userda;
        this.singleuser = singleuser;
    }

    public UsersBean() {
        this.userlist = new ArrayList();
        this.userda = new UsersDAO();
        this.singleuser = new Users();
    }

    public void insert() {
        this.getUserda().insert(this.getSingleuser());
    }

    public void delete(Users user) {
        this.getUserda().delete(user);
    }

    public Users finduser(Users user) {
        this.singleuser = this.getUserda().findUser(user);
        return singleuser;
    }

    public boolean checkuser(Users user) {
        return this.getUserda().checkUser(user);
    }

    public Users findforlogin() {
        Users tmp = this.getUserda().loginUser(singleuser);
        return tmp;
    }

    public void insertforsignup() {
        this.getUserda().insert(singleuser);
    }

    public List<Users> getUserlist() {
        this.userlist = this.getUserda().findAll();
        return userlist;
    }

    public UsersDAO getUserda() {
        return userda;
    }

    public void setUserda(UsersDAO userda) {
        this.userda = userda;
    }

    public Users getSingleuser() {
        if (this.singleuser == null) {
            this.singleuser = new Users();
        }
        return singleuser;
    }

    public void setSingleuser(Users singleuser) {
        this.singleuser = singleuser;
    }

    public void setUserlist(List<Users> userlist) {
        this.userlist = userlist;
    }

}
