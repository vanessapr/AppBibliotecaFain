package com.vanessapr.appbibliotecafain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vanessapr.appbibliotecafain.utils.MessageDialog;

public class FormSearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtDescriptores;
    private Spinner spOrder;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search);

        txtTitulo = (EditText) findViewById(R.id.txtFormTitulo);
        txtAutor = (EditText) findViewById(R.id.txtFormAutor);
        spOrder = (Spinner) findViewById(R.id.orderby_spinner);
        txtDescriptores = (EditText) findViewById(R.id.txtDescriptores);
        btnSearch = (Button) findViewById(R.id.btnFormSearch);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.field_orderby_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrder.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(validate()) {
            String conditions = "";

            if ( !txtTitulo.getText().toString().trim().equals("") ) {
                conditions +="titulo LIKE '%" + txtTitulo.getText().toString() + "%'"
                        + " OR titulo_revista LIKE '%" + txtTitulo.getText().toString().trim() + "%'";
            }

            if ( !txtAutor.getText().toString().trim().equals("") ) {
                conditions += (conditions.equals(""))? "" : " OR ";
                conditions += "autor_personal LIKE '%" + txtAutor.getText().toString().trim() + "%'";
            }
            /*
            if ( !txtEditorial.getText().toString().trim().equals("") ) {
                conditions += (conditions.equals(""))? "" : " OR ";
                conditions += "editorial LIKE '%" + txtEditorial.getText().toString().trim()  + "%'";
            } */

            if ( !txtDescriptores.getText().toString().trim().equals("") ) {
                conditions += (conditions.equals(""))? "" : " OR ";
                conditions += "descriptores LIKE '%" + txtDescriptores.getText().toString().trim() + "%'";
            }

            Intent  intent= new Intent(FormSearchActivity.this, BooksActivity.class);
            intent.putExtra(BooksActivity.EXTRA_WHERE, conditions);
            intent.putExtra(BooksActivity.EXTRA_ORDERBY, "titulo, autor_personal, editorial");
            startActivity(intent);

        } else {
            // launch message
            MessageDialog message = new MessageDialog(FormSearchActivity.this);
            message.display("Especifique al menos un campo para buscar");
        }
    }
    private boolean validate() {
        if ( txtTitulo.getText().toString().trim().equals("") &&
                txtAutor.getText().toString().trim().equals("") &&
               // txtEditorial.getText().toString().trim().equals("") &&
                txtDescriptores.getText().toString().trim().equals("") ) {
            return false;

        } else {
            return true;
        }
    }

}
