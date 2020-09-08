package com.mf4z.books;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class ApiUtil {

    private static final String QUERY_PARAMETER_KEY = "q";
    private static final String KEY = "key";
    private static final String API_KEY = "AIzaSyCanPtwEAyg2KlSOniD_rIUzdPiOvKPxRQ";

    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";

    public static URL buildUrl(String title){
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon().
                appendQueryParameter(QUERY_PARAMETER_KEY,title)
//                .appendQueryParameter(KEY,API_KEY)
                .build();
        try {
                url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJSON(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData){
                return scanner.next();
            }
            else{
                return null;
            }

        }catch (Exception e){

            Log.d("Error",e.toString()    );
            return null;
        }
        finally {
            connection.disconnect();
        }
    }
}
