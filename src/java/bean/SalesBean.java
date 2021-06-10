/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DAccess.SalesDAO;
import entity.Sales;
import entity.Users;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ciger
 */
@Named(value = "salesController")
@SessionScoped
public class SalesBean implements Serializable {

    private List<Sales> saleslist;
    private SalesDAO saleda;
    private Sales singlesale;
    private List<Sales> searchsales;
    private Sales inputsale;

    public SalesBean(List<Sales> saleslist, SalesDAO saleda, Sales singlesale, List<Sales> searchsales, Sales inputsale) {
        this.saleslist = saleslist;
        this.saleda = saleda;
        this.singlesale = singlesale;
        this.searchsales = searchsales;
        this.inputsale = inputsale;
    }

    public SalesBean() {
        this.saleslist = new ArrayList();
        this.saleda = new SalesDAO();
        this.singlesale = new Sales();
        this.inputsale = new Sales();
        this.searchsales = new ArrayList();
    }

    public void delete(Sales sale) {
        this.getSaleda().delete(sale);
    }

    public void insert() {
        this.getSaleda().insert(inputsale); // düzenlenecek
    }

    public void findSales(Users user) {
        this.searchsales = this.getSaleda().findSale(user);
    }

    public List<Sales> getSaleslist() {
        this.saleslist = this.getSaleda().findAll();
        return saleslist;
    }

    public void setSaleslist(List<Sales> saleslist) {
        this.saleslist = saleslist;
    }

    public SalesDAO getSaleda() {
        return saleda;
    }

    public void setSaleda(SalesDAO saleda) {
        this.saleda = saleda;
    }

    public Sales getSinglesale() {
        if(this.singlesale == null){
            singlesale = new Sales();
        }
        return singlesale;
    }

    public void setSinglesale(Sales singlesale) {
        this.singlesale = singlesale;
    }

    public List<Sales> getSearchsales() {
        return searchsales;
    }

    public void setSearchsales(List<Sales> searchsales) {
        this.searchsales = searchsales;
    }

    public Sales getInputsale() {
        if(this.inputsale == null){
            inputsale = new Sales();
        }
        return inputsale;
    }

    public void setInputsale(Sales inputsale) {
        this.inputsale = inputsale;
    }

}
