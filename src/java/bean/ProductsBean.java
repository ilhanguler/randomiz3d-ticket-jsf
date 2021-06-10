/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import DAccess.ProductsDAO;
import entity.Products;
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
@Named(value = "productsController")
@SessionScoped
public class ProductsBean implements Serializable {

    private List<Products> productlist;
    private ProductsDAO productda;
    private Products singleproduct;
    private List<Products> searchproducts;
    private Products inputproduct;

    public ProductsBean(List<Products> productlist, ProductsDAO productda, Products singleproduct, List<Products> searchproducts, Products inputproduct) {
        this.productlist = productlist;
        this.productda = productda;
        this.singleproduct = singleproduct;
        this.searchproducts = searchproducts;
        this.inputproduct = inputproduct;
    }

    public ProductsBean() {
        this.productlist = new ArrayList();
        this.productda = new ProductsDAO();
        this.singleproduct = new Products();
        this.searchproducts = new ArrayList();
        this.inputproduct = new Products();
    }

    public void findProducts() {
        this.searchproducts = this.getProductda().findProduct(inputproduct);
    }

    public String insert() {
        this.getProductda().insert(inputproduct);
        return "register";
    }

    public void delete(Products product) {
        this.getProductda().delete(product);
    }

    public List<Products> getProductlist() {
        this.productlist = this.getProductda().findAll();
        return productlist;
    }

    public void setProductlist(List<Products> productlist) {
        this.productlist = productlist;
    }

    public ProductsDAO getProductda() {
        return productda;
    }

    public void setProductda(ProductsDAO productda) {
        this.productda = productda;
    }

    public Products getSingleproduct() {
        return singleproduct;
    }

    public void setSingleproduct(Products singleproduct) {
        this.singleproduct = singleproduct;
    }

    public List<Products> getSearchproducts() {
        this.searchproducts = this.getProductda().findProduct(inputproduct);
        return searchproducts;
    }

    public void setSearchproducts(List<Products> searchproducts) {
        this.searchproducts = searchproducts;
    }

    public Products getInputproduct() {
        return inputproduct;
    }

    public void setInputproduct(Products inputproduct) {
        this.inputproduct = inputproduct;
    }

}
