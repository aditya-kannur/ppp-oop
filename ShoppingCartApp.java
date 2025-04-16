import java.util.*;

// Abstraction
abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Encapsulation 
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Abstract method
    public abstract double applyDiscount();
}

// Inheritance - Clothing class
class Clothing extends Product {
    public Clothing(String name, double price) {
        super(name, price);
    }

    @Override
    public double applyDiscount() {
        return price * 0.9; 
    }
}

// Inheritance 
class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price);
    }

    // Polymorphism (overriding)
    @Override
    public double applyDiscount() {
        return price * 0.95; 
    }
}

// CartItem class
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.applyDiscount() * quantity;
    }

    public void displayItem() {
        System.out.printf("- %s x%d = $%.2f%n", product.getName(), quantity, getTotalPrice());
    }
}

// ShoppingCart class
class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void displayCart() {
        System.out.println("\n Shopping Cart:");
        for (CartItem item : items) {
            item.displayItem();
        }
        System.out.printf("Total: $%.2f%n", getTotal());
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void checkout() {
        System.out.println("\n Checking out...");
        System.out.printf("Total amount to pay: $%.2f%n", getTotal());
        items.clear();
        System.out.println("Cart is now empty.");
    }
}

// Main application
public class ShoppingCartApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ShoppingCart cart = new ShoppingCart();

            System.out.println("=== Welcome to the Online Shopping Cart ===");

            while (true) {
            System.out.println("\nChoose a product type:");
            System.out.println("1. Clothing");
            System.out.println("2. Electronics");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                case 2:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();

                    Product product;
                    if (choice == 1)
                        product = new Clothing(name, price);
                    else
                        product = new Electronics(name, price);

                    cart.addItem(product, qty);
                    System.out.println("✔ Item added to cart.");
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    cart.displayCart();
                    cart.checkout();
                    break;

                case 5:
                    System.out.println("👋 Thank you for shopping!");
                                break;

                            default:
                                System.out.println("❌ Invalid choice.");
                                break;
                        
                    
                }
            }
        }
    }
}
