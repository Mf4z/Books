package com.mf4z.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //Get Book data sent from the BookListActivity
        Book book = getIntent().getParcelableExtra("Book");
    }
}
