package com.vanessapr.appbibliotecafain;


import android.content.Intent;
import android.os.Bundle;


public class BooksActivity extends BaseActivity {
    private String where, orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        where = intent.getStringExtra(MainActivity.class.getName() + ".where").toString();
        orderBy = intent.getStringExtra(MainActivity.class.getName() + ".orderBy").toString();


    }

}
