import java.util.*;

class Item {
    int id;
    String name;
    double price;
    int stock;

    Item(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class Inventory {
    private Map<Integer, Item> items = new HashMap<>();

    public void addItem(int id, String name, double price, int stock) {
        items.put(id, new Item(id, name, price, stock));
        System.out.println("Item added successfully.");
    }

    public void updateStock(int id, int stock) {
        if (items.containsKey(id)) {
            items.get(id).stock += stock;
            System.out.println("Stock updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void displayItems() {
        System.out.println("\n--- Available Items ---");
        for (Item i : items.values()) {
            System.out.println(i.id + " | " + i.name + " | Price: " + i.price + " | Stock: " + i.stock);
        }
    }

    public Item getItem(int id) {
        return items.get(id);
    }
}

class Billing {
    private List<String> bill = new ArrayList<>();
    private double total = 0;

    public void addToBill(Item item, int qty) {
        if (item.stock >= qty) {
            double cost = qty * item.price;
            item.stock -= qty;
            total += cost;
            bill.add(item.name + " x " + qty + " = " + cost);
            System.out.println("Item added to bill.");
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    public void generateBill() {
        System.out.println("\n===== BILL =====");
        for (String s : bill) {
            System.out.println(s);
        }
        System.out.println("-----------------");
        System.out.println("Total Amount: " + total);
    }
}

public class InventoryBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Billing billing = new Billing();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. Display Items");
            System.out.println("4. Add to Bill");
            System.out.println("5. Generate Bill");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Stock: ");
                    int stock = sc.nextInt();
                    inventory.addItem(id, name, price, stock);
                    break;

                case 2:
                    System.out.print("Enter Item ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Stock to Add: ");
                    int addStock = sc.nextInt();
                    inventory.updateStock(uid, addStock);
                    break;

                case 3:
                    inventory.displayItems();
                    break;

                case 4:
                    System.out.print("Enter Item ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    Item item = inventory.getItem(bid);
                    if (item != null) {
                        billing.addToBill(item, qty);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    billing.generateBill();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
