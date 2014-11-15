package com.vanessapr.appbibliotecafain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vanessapr.appbibliotecafain.models.Libro;
import com.vanessapr.appbibliotecafain.models.LibroAdapter;
import com.vanessapr.appbibliotecafain.models.LibroModel;
import com.vanessapr.appbibliotecafain.utils.MessageDialog;

import java.util.ArrayList;


public class BooksActivity extends BaseActivity  {
    private static final String TAG = "BooksActivity";
    public static final String EXTRA_WHERE = BooksActivity.class.getName() + ".where";
    public static final String EXTRA_ORDERBY = BooksActivity.class.getName() + ".orderBy";
    private ListView lvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Log.d(TAG, "onCreate... ");

        String where = getIntent().getStringExtra(EXTRA_WHERE);
        String orderBy = getIntent().getStringExtra(EXTRA_ORDERBY);

        lvBooks = (ListView) findViewById(R.id.listview_books);

        displayBooksList(where, orderBy);
    }


    public void displayBooksList(String where, String orderBy) {
        LibroModel model = new LibroModel(this);
        ArrayList<Libro> data = model.getLibros(where, orderBy);

        if(data.size() > 0) {
            LibroAdapter adapter = new LibroAdapter(this, data);
            lvBooks.setAdapter(adapter);
            lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Libro libro = (Libro) parent.getItemAtPosition(position);
                    Intent intent = new Intent(BooksActivity.this, BookSingleActivity.class);
                    intent.putExtra(BookSingleActivity.EXTRA_BOOK, libro);
                    startActivity(intent);
                }
            });

        } else {
            MessageDialog message = new MessageDialog(this, true);
            message.display("No hay resultados que mostrar");
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
            public boolean onQueryTextSubmit(String query) {
                StringBuilder where = new StringBuilder();
                where.append("autor_libro LIKE '%").append(query).append("%' ");
                where.append("OR titulo LIKE '%").append(query).append("%' ");
                where.append("OR contenidos LIKE '%").append(query).append("%' ");
                where.append("OR descriptores LIKE '%").append(query).append("%'");

                Intent intent = new Intent(BooksActivity.this, BooksActivity.class);
                intent.putExtra(EXTRA_WHERE, where.toString());
                intent.putExtra(EXTRA_ORDERBY, "titulo, autor_libro");
                startActivity(intent);

                return false;
            }

        });

        return true;
    }

}
