package com.example.lab2.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {

    private BookDao mBookDao;
    private LiveData<List<Book>> mAllBooks;

    BookRepository(Application application) {
        LibDatabase db = LibDatabase.getDatabase(application);
        mBookDao = db.bookDao();
        mAllBooks = mBookDao.getAllItem();
    }
    LiveData<List<Book>> getAllItems() {
        return mAllBooks;
    }
    void insert(Book item) {
        LibDatabase.databaseWriteExecutor.execute(() ->
                mBookDao.addItem(item));
    }

    void deleteAll(){
        LibDatabase.databaseWriteExecutor.execute(()->{
            mBookDao.deleteAllItems();
        });
    }

    void deleteLastbook(){
        LibDatabase.databaseWriteExecutor.execute(()->{
            mBookDao.deleteLastItem();
        });
    }
}

