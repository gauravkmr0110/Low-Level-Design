
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


// Entities
class InventoryProduct{
    int id;
    String name;
    String Category;
    int price;

    InventoryProduct(int id, String name, String Category, int price){
        this.id = id;
        this.name = name;
        this.Category = Category;
        this.price = price;
    }
}

class Warehouse{
    private HashMap<InventoryProduct,Integer> warehouseInventory = new HashMap<>();
    private int warehouseId;
    private String name;
    private int pincode;

    public Warehouse(int warehouseId, String name, int pincode) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.pincode = pincode;
    }

    

    public HashMap<InventoryProduct,Integer> getWarehouseInventory(){
        return warehouseInventory;
    }
    public int getPincode(){
        return pincode;
    }

    public int getProductCount(InventoryProduct product){
        if(warehouseInventory.containsKey(product)){
            return warehouseInventory.get(product);
        }
        return 0;
    }

    public void addProduct(InventoryProduct product, int quantity){
        warehouseInventory.compute(product, (key,value) -> value==null ? quantity : value+quantity);
    }

    public boolean consumeProduct(InventoryProduct product, int quantity){
        if(!warehouseInventory.containsKey(product) || warehouseInventory.get(product) < quantity){
            System.out.println("ERROR: Required quantity not present");
            return false;
        }
        warehouseInventory.compute(product, (key,value) -> value>quantity ? value-quantity : 0);
        return true;
    }

}

// central inventory service to manage all warehouses
class InventoryService{
    private static InventoryService inventoryService;
    private static List<Warehouse> warehouseList;
    private static HashMap<InventoryProduct,Integer> centralInventory = new HashMap<>();
    private static List<Order>orderList;

    private InventoryService(){

    }

    public static InventoryService getInventoryServiceObj(){
        if(inventoryService==null){
            inventoryService = new InventoryService();
            warehouseList = new ArrayList<>();
            centralInventory = new HashMap<>();
            orderList = new ArrayList<>();
        }
        return inventoryService;
    }
    public void addWarehouse(Warehouse warehouse){
        warehouseList.add(warehouse);
    }

    public void removeWarehouse(Warehouse wh){
        warehouseList.remove(wh);

        HashMap<InventoryProduct,Integer> whInventory = wh.getWarehouseInventory();

        for(InventoryProduct p: whInventory.keySet()){
            int count = whInventory.get(p);
            centralInventory.compute(p,(key,value) -> value > count ? value-count : 0 );
        }

    }

    // add product and remove product to warehouse trogh central service
    public void addProductToWarehouse(Warehouse warehouse, InventoryProduct product, int quantity){
        warehouse.addProduct(product, quantity);
        centralInventory.compute(product, (key,value) -> value==null ? quantity : value+quantity);
    }

    public void consumeProductFromWarehouse(Warehouse warehouse, InventoryProduct product, int quantity){
        boolean possible = warehouse.consumeProduct(product, quantity);
        if(possible == false){
            return;
        }
        centralInventory.compute(product, (key,value) -> value<quantity ? value : value-quantity);
    }

    // Order and selection of warehouse for each thing 

    public boolean createOrder(Order order){
        List<OrderItem> products = order.orderItems;

        for(OrderItem item : products){
            if(centralInventory.get(item.product)<item.quantity){
                System.out.println("product " + item.product.name+ " is out of stock");
                return false;
            }
        }

        for(OrderItem item: products){
            placeProductOrder(item.product, item.quantity, order.deliveryPincode);
        }

        System.out.println("Order created successfully");

        return true;




    }

    // each item of order could be from diff warehouse
    public void placeProductOrder(InventoryProduct product, int quantity, int deliveryPincode){
        List<Warehouse> warehouses = new ArrayList<>(warehouseList);
        Collections.sort(warehouses, (a,b) -> Integer.compare(Math.abs(a.getPincode()-deliveryPincode),Math.abs(b.getPincode()-deliveryPincode)));

        for(Warehouse wh:warehouses){
            int warehouseQuantity= wh.getProductCount(product);
            if(quantity > warehouseQuantity){
                quantity-=warehouseQuantity;
                consumeProductFromWarehouse(wh, product, warehouseQuantity);
            }
            else{
            
                consumeProductFromWarehouse(wh, product, quantity);
                quantity = 0;
                break;
            }
        }
    }


}


// now order 
class OrderItem{
    InventoryProduct product;
    int quantity;

    public OrderItem(InventoryProduct product , int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    
}

class Order{
    List<OrderItem> orderItems;
    String orderId;;
    int deliveryPincode;

    public Order(String orderId, int deliveryPincode) {
        this.orderId = orderId;
        this.deliveryPincode = deliveryPincode;
        this.orderItems = new ArrayList<>();
    }
    

}

public class InventoryManagement {
    public static void main(String[] args) {
        InventoryService service = InventoryService.getInventoryServiceObj();

        // 1. Setup Warehouses
        Warehouse wh1 = new Warehouse(1, "Delhi WH", 110001);
        Warehouse wh2 = new Warehouse(2, "Mumbai WH", 400001);
        Warehouse wh3 = new Warehouse(3, "Bengaluru WH", 560001);

        service.addWarehouse(wh1);
        service.addWarehouse(wh2);
        service.addWarehouse(wh3);

        // 2. Setup Products
        InventoryProduct mouse = new InventoryProduct(101, "Wireless Mouse", "ELECTRONICS", 1200);
        InventoryProduct keyboard = new InventoryProduct(102, "Mechanical Keyboard", "ELECTRONICS", 4500);

        // 3. Populate Stock
        service.addProductToWarehouse(wh1, mouse, 10);     // Delhi has 10 Mice
        service.addProductToWarehouse(wh2, mouse, 20);     // Mumbai has 20 Mice
        service.addProductToWarehouse(wh3, keyboard, 5);   // Bengaluru has 5 Keyboards

        System.out.println("--- Test 1: Nearest Single Warehouse Allocation ---");
        // Customer at 400005 (near Mumbai) wants 5 mice. Should take all 5 from Mumbai (wh2).
        Order order1 = new Order("ORD-001", 400005);
        order1.orderItems.add(new OrderItem(mouse, 5));
        service.createOrder(order1);
        System.out.println("Remaining Mumbai Mice: " + wh2.getProductCount(mouse)); // Expected: 15

        System.out.println("\n--- Test 2: Stock Allocation Splitting across Warehouses ---");
        // Customer at 110002 (near Delhi) wants 18 mice. 
        // Delhi only has 10 left. It should take 10 from Delhi (wh1) and 8 from Mumbai (wh2).
        Order order2 = new Order("ORD-002", 110002);
        order2.orderItems.add(new OrderItem(mouse, 18));
        service.createOrder(order2);
        System.out.println("Remaining Delhi Mice: " + wh1.getProductCount(mouse));  // Expected: 0
        System.out.println("Remaining Mumbai Mice: " + wh2.getProductCount(mouse)); // Expected: 7

        System.out.println("\n--- Test 3: Multi-Item Order and Fast-Failing Check ---");
        // Customer wants 2 mice and 10 keyboards. 
        // System has enough mice (7), but NOT enough keyboards (only 5 exist globally). Should fail cleanly without altering stock.
        Order order3 = new Order("ORD-003", 560002);
        order3.orderItems.add(new OrderItem(mouse, 2));
        order3.orderItems.add(new OrderItem(keyboard, 10)); 
        
        boolean isCreated = service.createOrder(order3);
        System.out.println("Order status: " + (isCreated ? "SUCCESS" : "FAILED"));
        System.out.println("Verification - Mumbai Mice count still: " + wh2.getProductCount(mouse)); // Should remain 7
    }
}