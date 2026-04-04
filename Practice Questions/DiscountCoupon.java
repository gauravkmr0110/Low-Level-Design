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
enum Itemcategory{
    ELECTRONICS,
    CLOTHING,
    GROCERRY,
    HEALTH,
    FURNITURE,
    OTHER
}
// cart item class
class CartItem{
    String name;
    Itemcategory itemcategory;
    int price ;
}

// cart class

class Cart{
    List<CartItem> items
}


//

public class DiscountCoupon {
    public static void main(String args[]){

    }
}