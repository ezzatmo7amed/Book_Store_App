package Book.beans;


import Book.classes.JDBCCLASS;
import Book.classes.ProductCart;
import Book.classes.Products;

import java.io.Serializable;

import java.util.ArrayList;

public class StoreBean implements Serializable {
    
    private ArrayList<Products> productList = new ArrayList<Products>();
    private ArrayList<ProductCart> productCartList = new ArrayList<ProductCart>(); 
    public StoreBean() {
    }

    public void setProductCartList(ArrayList<ProductCart> productCartList) {
        this.productCartList = productCartList;
    }

    public ArrayList<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductList(ArrayList<Products> productList) {
        this.productList = productList;
    }

    public ArrayList<Products> getProductList() {
        
        this.productList = JDBCCLASS.getAllProducts(); 
        return productList;
    }
    public Object addproducttocart(Products Product) {
        
            this.productCartList.add(new ProductCart(Product,1));
      
        return null;
    }

    public Object addProductToCart(Products Product) {
        // Add event code here...
          int index = productExistInCart(Product);
          
          if(index == -1)
              this.productCartList.add(new ProductCart(Product,1));
          else{
              //increament quantity
              int newQuantity = this.productCartList.get(index).getQuantity() + 1;
                                this.getProductCartList().get(index).setQuantity(newQuantity); 
              
          } 
        return null;
    }
   
    public int productExistInCart(Products product) {
        
        for(int i=0; i < productCartList.size();i++) {
            if(productCartList.get(i).getProduct().getBook_Id() == product.getBook_Id());
            return i; 
            
        }
        return -1;
    }
    public double sumCart() {
         double sum=0;
             for(ProductCart item:productCartList) {
                 sum +=item.getProduct().getBook_Price() *item.getQuantity();
             } 
             return Math.round(sum);
    }


    public Object removeProduct(ProductCart product) {
        // Add event code here...
        
        this.productCartList.remove(product);
        return null;
    }
}
