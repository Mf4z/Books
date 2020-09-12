package com.mf4z.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);

        } catch (Exception e) {
            Log.d("Error",e.getMessage());
        }
    }

    public class BooksQueryTask extends AsyncTask<URL,Void,String>{
        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJSON(searchURL);
            } catch (IOException e) {
                Log.d("Error",e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvResult = (TextView)findViewById(R.id.tvResponse);
            TextView tvError = (TextView) findViewById(R.id.textView_error);
            mProgressBar.setVisibility(View.INVISIBLE);
            if (result == null){
              tvError.setVisibility(View.VISIBLE);
              tvResult.setVisibility(View.INVISIBLE);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);
                tvResult.setVisibility(View.VISIBLE);
            }

            ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
            String resultString = "";
            for (Book book: books) {
                resultString += book.title + "\n" + book.publishedDate + "\n\n";
            }

            tvResult.setText(resultString);
            Log.i("Show result",resultString);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE );
        }
    }


}
