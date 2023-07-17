package com.example.lab2.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("select * from books")
    LiveData<List<Book>> getAllItem();

    @Query("select * from books where bookName=:name")
    List<Book> getItem(String name);




    @Insert
    void addItem(Book item);

    @Query("delete from books where price= :name")
    void deleteItem(String name);

    @Query("delete FROM books")
    void deleteAllItems();

    @Query("DELETE FROM books WHERE bookID = (SELECT max(bookID) FROM books)")
    void deleteLastItem();


}

