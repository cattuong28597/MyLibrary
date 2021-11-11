package model;

public class Book {
    protected String id;
    protected String name;
    protected String kind;
    protected String author;
    protected int quantity;

    public Book() {
    };

    public Book(String name, String kind, String author, int quantity) {
        this.name = name;
        this.kind = kind;
        this.author = author;
        this.quantity = quantity;
    }

    public Book(String id, String name, String kind, String author, int quantity) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.author = author;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
