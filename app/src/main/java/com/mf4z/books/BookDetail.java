package com.mf4z.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.mf4z.books.databinding.ActivityBookDetailBinding;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //Get Book data sent from the BookListActivity
        Book book = getIntent().getParcelableExtra("Book");

        //Using Data binding
        ActivityBookDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_book_detail);
        binding.setBook(book);
    }
}
