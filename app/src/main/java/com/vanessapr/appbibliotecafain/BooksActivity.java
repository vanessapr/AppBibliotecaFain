package com.vanessapr.appbibliotecafain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vanessapr.appbibliotecafain.fragments.BookFragment;
import com.vanessapr.appbibliotecafain.fragments.BooksListFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BooksActivity extends BaseActivity implements
        BooksListFragment.onBooksListSelectListener {
    private static final String TAG = "BooksActivity";
    public static final String EXTRA_WHERE = BooksActivity.class.getName() + ".where";
    public static final String EXTRA_ORDERBY = BooksActivity.class.getName() + ".orderBy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Log.i(TAG, "onCreate... ");

        BooksListFragment booksListFragment = (BooksListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_bookslist);

        String where = getIntent().getStringExtra(EXTRA_WHERE);
        String orderBy = getIntent().getStringExtra(EXTRA_ORDERBY);
        booksListFragment.displayBooksList(where, orderBy);

    }


    @Override
    public void onBookSelected(Libro libro) {
        BookFragment bookFragment = (BookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_book);

        Log.i(TAG, "onBookSelected: " + bookFragment);

        if(bookFragment != null && bookFragment.getActivity() != null) {
            bookFragment.displayBookSingle(libro);
            bookFragment.setRetainInstance(true);

        } else {
            Intent intent = new Intent(BooksActivity.this, BookSingleActivity.class);
            intent.putExtra(BookSingleActivity.EXTRA_BOOK, libro);
            startActivity(intent);
         }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_books, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_action_busqueda);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint("Buscar...");
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                String query = arg0;
                StringBuilder where = new StringBuilder();
                where.append("autor_personal LIKE '%").append(query).append("%' ");
                where.append("OR autor_institucional LIKE '%").append(query).append("%' ");
                where.append("OR titulo LIKE '%").append(query).append("%' ");
                where.append("OR resumen LIKE '%").append(query).append("%' ");
                where.append("OR descriptores LIKE '%").append(query).append("%' ");
                where.append("OR titulo_revista LIKE '%").append(query).append("%'");

                Intent intent = new Intent(BooksActivity.this, BooksActivity.class);
                intent.putExtra(EXTRA_WHERE, where.toString());
                intent.putExtra(EXTRA_ORDERBY, "titulo, titulo_revista, autor_personal, autor_institucional");
                startActivity(intent);

                return false;
            }

        });

        return true;
    }
}
