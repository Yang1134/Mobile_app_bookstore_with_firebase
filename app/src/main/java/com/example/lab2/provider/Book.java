package com.example.lab2.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "books")
public class Book {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "bookID")
    private int bookID;

    @ColumnInfo(name = "bookName")
    private String bookName;


    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "BOOKID1")
    private String BOOKID1;

    @ColumnInfo(name = "BOOKISBN")
    private String BOOKISBN;

    @ColumnInfo(name = "BOOKDescription")
    private String BOOKDescription;

    public Book(String bookName,  double price, String author, String bookid, String bookisbn, String bookdescription) {
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.BOOKID1 = bookid;
        this.BOOKISBN = bookisbn;
        this.BOOKDescription = bookdescription;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", bookName);
        result.put("price", price);
        result.put("author", author);
        result.put("id", BOOKID1);
        result.put("isbn", BOOKISBN);
        result.put("description", BOOKDescription);

        return result;
    }

    public Book() {

    }

    public Book(String tmpBookName, double tmpPrice) {
    }
    //, String author, String bookid, String bookisbn, String bookdescription

    public String getBookid() {
        return BOOKID1;
    }

    public void setBookid(String bookid) {
        this.BOOKID1 = bookid;
    }

    public String getBookISBN() {
        return BOOKISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.BOOKISBN = bookISBN;
    }

    public String getBookDescription() {
        return BOOKDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.BOOKDescription = bookDescription;
    }

    public String getBOOKID1() {
        return BOOKID1;
    }

    public void setBOOKID1(String BOOKID1) {
        this.BOOKID1 = BOOKID1;
    }

    public String getBOOKISBN() {
        return BOOKISBN;
    }

    public void setBOOKISBN(String BOOKISBN) {
        this.BOOKISBN = BOOKISBN;
    }

    public String getBOOKDescription() {
        return BOOKDescription;
    }

    public void setBOOKDescription(String BOOKDescription) {
        this.BOOKDescription = BOOKDescription;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(@NonNull int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String Author) {
        author = Author;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

