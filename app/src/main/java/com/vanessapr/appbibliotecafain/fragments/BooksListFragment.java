package com.vanessapr.appbibliotecafain.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vanessapr.appbibliotecafain.BooksActivity;
import com.vanessapr.appbibliotecafain.R;
import com.vanessapr.appbibliotecafain.models.Libro;
import com.vanessapr.appbibliotecafain.models.LibroAdapter;
import com.vanessapr.appbibliotecafain.models.LibroModel;
import com.vanessapr.appbibliotecafain.utils.MessageDialog;

import java.util.ArrayList;

/**
 * Created by Milagros on 27/10/2014.
 */
public class BooksListFragment extends Fragment {
    private static final String TAG = "BooksListFragment";
    private ArrayList<Libro> data;
    private ListView lvBooks;
    private onBooksListSelectListener mCallback;

    public BooksListFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart....");

        Bundle args = getArguments();
        if(args != null) {
            String where = args.getString(BooksActivity.EXTRA_WHERE);
            String orderBy = args.getString(BooksActivity.EXTRA_ORDERBY);
            displayBooksList(where, orderBy);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView...");
        View view = inflater.inflate(R.layout.fragment_bookslist, container, false);
        lvBooks = (ListView) view.findViewById(R.id.listview_books);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach...");
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onBooksListSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onBooksListSelectListener");
        }
    }

    // Container Activity must implement this interface
    public interface onBooksListSelectListener {
        public void onBookSelected(Libro libro);
    }

    public void displayBooksList(String where, String orderBy) {
        LibroModel model = new LibroModel(getActivity());
        data = model.getLibros(where, orderBy);

        if(data.size() > 0) {
            LibroAdapter adapter = new LibroAdapter(getActivity(), data);
            lvBooks.setAdapter(adapter);

        } else {
            MessageDialog message = new MessageDialog(getActivity(), true);
            message.display("No hay resultados que mostrar");
        }

        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Notify the parent activity of selected item
                mCallback.onBookSelected(data.get(position));
            }
        });
    }

}
