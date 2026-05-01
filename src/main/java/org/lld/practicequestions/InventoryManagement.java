package org.lld.practicequestions;

/*
Problem Statement Inventory Management like Zepto we assume all products of a
order wil be from same warehouse
User -> comes to the system
  - can view products / or by category
  - Add prodcut to cart
  - Place Order -> Invoice
  - Payment / Checkout
 */

/* entities/ Objects
 - User, Product, Cart, Order, Invoice, Payement, Warehouse, inventory
 */

import java.util.*;
// Entities


class InventoryProduct{
    String name;
    String Category;
}

// each ware house has one inventory

class InventoryItem{
    List<InventoryProduct>productList;
    String Category;
    int price;

}

class Inventory{
    HashMap<InventoryProduct,Integer> productCount = new HashMap<>();
    public void addProduct(InventoryProduct p , int quantity){
        productCount.compute(p, (k,v) -> v == null ? quantity : v + quantity);
    }
}
class Warehouse{

}