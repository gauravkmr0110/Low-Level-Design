/*
Design a system where:
    - Users add items to a cart
    - Apply discount coupons
    - System validates and applies discounts
    - Supports multiple coupon types (flat, percentage, conditional)
    - multiple coupons can also be applied sequentially
 */
import java.util.*;

// ----------- entities ------
enum ProductCategory{
    ELECTRONICS,
    CLOTHING,
    GROCERRY,
    HEALTH,
    FURNITURE,
    OTHER
}

// prodcut class
class Product{
    String name;
    ProductCategory productCategory;
    double price ;
    int quantity;

    Product(String name,double price, int quantity,ProductCategory category){
        this.name = name;
        this.productCategory = category;
        this.price = price;
        this.quantity= quantity;
    }

    double getPrice(){
        return price*quantity;
    }

}

// for effcetive pricing we need cartItem

class CartItem{
    Product product;
    double effectivePrice;

    CartItem(Product p){
        this.product = p;
        this.effectivePrice = p.getPrice();
    }

    ProductCategory getCartItemCategory(){
        return product.productCategory;
    }



}

// cart class
class ShoppingCart{
    List<CartItem> itemList;

    public ShoppingCart(){
        this.itemList = new ArrayList<>();
    }

    public void addToCart(Product product){
        itemList.add(new CartItem(product));
    }

    public double getCartValue(){
        double total = 0;
        for(CartItem item:itemList){
           total+=item.effectivePrice;
        }
        return total;
    }

}

// -------- DIFFERENT STRATEGIES FOR COUPONS ------

interface CouponStrategy{
    void applyCoupon(ShoppingCart cart);
}

// FLAT COUPOUN ON CART VALUE, EACH PRODUCT PRICE WILL CHANGE ACCORDINGLY
class FlatDiscountALL implements CouponStrategy{
    private double discount;
    FlatDiscountALL(double discount){
        this.discount=discount;
    }

    @java.lang.Override
    public void applyCoupon(ShoppingCart cart) {
        // contribution of each product in flat discount
        double cartValue = cart.getCartValue();
        if(cartValue==0){
            return ;
        }
        for(CartItem item: cart.itemList){
            double itemDiscount = (item.effectivePrice/cartValue)*discount;
            item.effectivePrice-=itemDiscount;
            item.effectivePrice=Math.max(0,item.effectivePrice);
        }

    }
}

// PERCENTAGE DISCOUNT
class PercentDiscountALL implements CouponStrategy{
    private double discountPercent;
    PercentDiscountALL(double discountPercent){
        this.discountPercent = discountPercent;
    }

    @java.lang.Override
    public void applyCoupon(ShoppingCart cart) {
        for(CartItem item: cart.itemList){
            double itemDiscount = (item.effectivePrice*discountPercent)/100.0;
            item.effectivePrice-=itemDiscount;
            item.effectivePrice=Math.max(0,item.effectivePrice);

        }

    }
}

// DISCOUNT ON CATEGORY can have both flat or percent we will impleemnt percent only
class PercentDiscountCategory implements CouponStrategy{
    private double discountPercent;
    ProductCategory discountCategory;

    PercentDiscountCategory(double discountPercent, ProductCategory discountCategory){
        this.discountPercent = discountPercent;
        this.discountCategory = discountCategory;
    }

    @java.lang.Override
    public void applyCoupon(ShoppingCart cart) {
        for(CartItem item: cart.itemList){

            if(item.getCartItemCategory() == discountCategory) {
                item.effectivePrice -= (discountPercent * item.effectivePrice) / 100.0;
                item.effectivePrice = Math.max(0,item.effectivePrice);
            }
        }

    }
}


public class DiscountCoupon{
    public static void main(String args[]){

        ShoppingCart cart = new ShoppingCart();

        Product p1 = new Product("Laptop",50000,1,ProductCategory.ELECTRONICS);

        Product p2 = new Product("Shirt",2000,3,ProductCategory.CLOTHING);

        cart.addToCart(p1);
        cart.addToCart(p2);

        double cartValue = cart.getCartValue();

        // APPLY COUPONS
        CouponStrategy coupon1 = new PercentDiscountALL(10.0);
        CouponStrategy coupon2 = new PercentDiscountCategory(15,ProductCategory.ELECTRONICS);
        CouponStrategy coupon3 = new FlatDiscountALL(1000);

        coupon1.applyCoupon(cart);
        coupon2.applyCoupon(cart);
        coupon3.applyCoupon(cart);
        System.out.println("Final cart value is: " + cart.getCartValue());

    }
}