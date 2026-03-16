package main.java.com.tracker;

public class Transactions 
{
    double amount;
    String type, name, date, category;

    /*Constructor */

    public Transaction(String type, String name, String date, String category, double amount)
    {
        this.type = type;
        this.name = name;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    /*Source Methods */


    @Override
    public String toString() 
    {
        return "Transaction [amount=" + amount + ", type=" + type + ", name=" + name + ", date=" + date + ", category="
                + category + "]";
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
