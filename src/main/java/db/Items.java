package db;

public class Items {
    private Long id;
    private String name;
    private int price;
    private int amount;
    private Countries manufacturer;

    public Items() {
    }

    public Items(Long id, String name, int price, int amount, Countries manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.manufacturer = manufacturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Countries getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Countries manufacturer) {
        this.manufacturer = manufacturer;
    }
}
