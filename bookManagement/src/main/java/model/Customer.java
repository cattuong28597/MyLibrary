package model;

public class Customer {
    protected int id;
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected String mail;
    protected String nameBook;
    protected String idBook;
    protected int amount;

    public Customer() {
    }

    public Customer(String name, String address, String phoneNumber, String mail) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public Customer(int id, String name, String address, String phoneNumber, String mail) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public Customer(int id,String name, String address, String phoneNumber, String mail, String nameBook,int amount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.nameBook = nameBook;
        this.amount = amount;
    }

    public Customer(String name, String address, String phoneNumber, String mail, String idBook,int amount) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.idBook = idBook;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
