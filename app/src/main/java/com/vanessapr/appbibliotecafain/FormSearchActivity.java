package com.vanessapr.appbibliotecafain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vanessapr.appbibliotecafain.utils.MessageDialog;

public class FormSearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtDescriptores;
    private CheckBox chLibros;
    private CheckBox chRevistas;
    private CheckBox chCDs;
    private CheckBox chOtros;
    private Spinner spinnerOrder;
    private Button btnSearch;
    private String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search);

        txtTitulo = (EditText) findViewById(R.id.txtFormTitulo);
        txtAutor = (EditText) findViewById(R.id.txtFormAutor);
        spinnerOrder = (Spinner) findViewById(R.id.orderby_spinner);
        txtDescriptores = (EditText) findViewById(R.id.txtDescriptores);
        chLibros = (CheckBox) findViewById(R.id.chFormBooks);
        chRevistas = (CheckBox) findViewById(R.id.chFormReview);
        chCDs = (CheckBox) findViewById(R.id.chFormCD);
        chOtros = (CheckBox) findViewById(R.id.chFormHomeworks);
        btnSearch = (Button) findViewById(R.id.btnFormSearch);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.field_orderby_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setOnClickListener(this);
        spinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0: orderBy = "autor_libro"; break;
                    case 1: orderBy = "titulo"; break;
                    case 2: orderBy = "fecha"; break;
                }
                //orderBy = (String) parent.getItemAtPosition(pos);
                //Toast.makeText(getBaseContext(), orderBy, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(validate()) {
            StringBuilder conditions = new StringBuilder();

            if ( !txtTitulo.getText().toString().trim().equals("") ) {
                conditions.append("( titulo LIKE '%")
                        .append(txtTitulo.getText().toString().trim()).append("%'");
            }

            if ( !txtAutor.getText().toString().trim().equals("") ) {
                conditions.append((conditions.toString().equals(""))? "( " : " OR ");
                conditions.append("autor_libro LIKE '%")
                        .append(txtAutor.getText().toString().trim()).append("%'");
            }

            if ( !txtDescriptores.getText().toString().trim().equals("") ) {
                conditions.append((conditions.equals(""))? "( " : " OR ");
                conditions.append("descriptores LIKE '%")
                        .append(txtDescriptores.getText().toString().trim()).append("'%");
            }

            conditions.append( (conditions.toString().equals(""))? "": " )");
            StringBuilder condAux = new StringBuilder();

            if(chLibros.isChecked()) {
                condAux.append("( codigo NOT LIKE 'T%' AND codigo NOT LIKE 'IB%' AND codigo != '' ");
            }

            if(chRevistas.isChecked()) {
                condAux.append((condAux.toString().equals(""))? "( ": " OR ");
                condAux.append("descriptores LIKE 'REVISTA'");
            }

            if(chCDs.isChecked()) {
                condAux.append((condAux.toString().equals(""))? "( ": " OR ");
                condAux.append("url_pdf != ''");
            }

            if(chOtros.isChecked()) {
                condAux.append((condAux.toString().equals(""))? "( ": " OR ");
                condAux.append("codigo LIKE 'T%' OR codigo LIKE 'IB%'");
            }

            condAux.append((condAux.toString().equals(""))? "": " )");

            if(!condAux.toString().equals("")) {
                conditions.append((conditions.toString().equals(""))? "": " AND " );
                conditions.append(condAux.toString());
            }

            Intent  intent= new Intent(FormSearchActivity.this, BooksActivity.class);
            intent.putExtra(BooksActivity.EXTRA_WHERE, conditions.toString());
            intent.putExtra(BooksActivity.EXTRA_ORDERBY, orderBy);
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
                txtDescriptores.getText().toString().trim().equals("") &&
                !chLibros.isChecked() && !chRevistas.isChecked() &&
                !chCDs.isChecked() && !chOtros.isChecked() ) {
            return false;

        } else {
            return true;
        }
    }

}
