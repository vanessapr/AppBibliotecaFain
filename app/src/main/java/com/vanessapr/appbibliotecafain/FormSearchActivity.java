package com.vanessapr.appbibliotecafain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormSearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtEditorial;
    private EditText txtDescriptores;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search);

        txtTitulo = (EditText) findViewById(R.id.txtFormTitulo);
        txtAutor = (EditText) findViewById(R.id.txtFormAutor);
        txtEditorial = (EditText) findViewById(R.id.txtFormEditorial);
        txtDescriptores = (EditText) findViewById(R.id.txtDescriptores);
        btnSearch = (Button) findViewById(R.id.btnFormSearch);
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

            if ( !txtEditorial.getText().toString().trim().equals("") ) {
                conditions += (conditions.equals(""))? "" : " OR ";
                conditions += "editorial LIKE '%" + txtEditorial.getText().toString().trim()  + "%'";
            }

            if ( !txtDescriptores.getText().toString().trim().equals("") ) {
                conditions += (conditions.equals(""))? "" : " OR ";
                conditions += "descriptores LIKE '%" + txtDescriptores.getText().toString().trim() + "%'";
            }

            Intent  intent= new Intent(FormSearchActivity.this, BooksActivity.class);
            intent.putExtra(BooksActivity.EXTRA_WHERE, conditions);
            intent.putExtra(BooksActivity.EXTRA_ORDERBY, "titulo, autor_personal, editorial");
            startActivity(intent);

        } else {
            // lunch message
        }
    }
    private boolean validate() {
        if ( txtTitulo.getText().toString().trim().equals("") &&
                txtAutor.getText().toString().trim().equals("") &&
                txtEditorial.getText().toString().trim().equals("") &&
                txtDescriptores.getText().toString().trim().equals("") ) {
            return false;

        } else {
            return true;
        }
    }

}
