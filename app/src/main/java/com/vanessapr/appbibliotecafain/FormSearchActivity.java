package com.vanessapr.appbibliotecafain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vanessapr.appbibliotecafain.utils.MessageDialog;

public class FormSearchActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String TAG = "FormSearchActivity";
    private EditText txtTitulo, txtAutor, txtDescriptores;
    private Spinner spinnerType, spinnerOrder;
    private CheckBox chAll;
    private Button btnSearch;
    private String orderBy, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search);

        txtTitulo = (EditText) findViewById(R.id.txtFormTitulo);
        txtAutor = (EditText) findViewById(R.id.txtFormAutor);
        spinnerOrder = (Spinner) findViewById(R.id.sp_orderby);
        spinnerType = (Spinner) findViewById(R.id.sp_type_books);
        txtDescriptores = (EditText) findViewById(R.id.txtDescriptores);
        chAll = (CheckBox) findViewById(R.id.chAll);
        btnSearch = (Button) findViewById(R.id.btnFormSearch);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.field_orderby_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this,
                R.array.field_typeBooks_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setOnClickListener(this);
        spinnerOrder.setOnItemSelectedListener(this);
        spinnerType.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        StringBuilder conditions = new StringBuilder("");

        if(!chAll.isChecked()) {
            if(validate()) {
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
                    conditions.append((conditions.toString().equals(""))? "( " : " OR ");
                    conditions.append("descriptores LIKE '%")
                            .append(txtDescriptores.getText().toString().trim()).append("%'");
                }

                if(!conditions.toString().equals("")) {
                    conditions.append(" ) AND ( ").append(type).append(" )");
                } else {
                    conditions.append("(").append(type).append(")");
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

        } else {
            conditions.append("(").append(type).append(")");

            Intent  intent= new Intent(FormSearchActivity.this, BooksActivity.class);
            intent.putExtra(BooksActivity.EXTRA_WHERE, conditions.toString());
            intent.putExtra(BooksActivity.EXTRA_ORDERBY, orderBy);
            startActivity(intent);
        }


    }
    private boolean validate() {
        return !(txtTitulo.getText().toString().trim().equals("") &&
                txtAutor.getText().toString().trim().equals("") &&
                txtDescriptores.getText().toString().trim().equals(""));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if( parent.getId() == R.id.sp_orderby) {
            switch (pos) {
                case 0: orderBy = "autor_libro"; break;
                case 1: orderBy = "titulo"; break;
                case 2: orderBy = "fecha DESC"; break;
            }
            //orderBy = (String) parent.getItemAtPosition(pos);
            //Toast.makeText(getBaseContext(), orderBy, Toast.LENGTH_LONG).show();
        } else if(parent.getId() == R.id.sp_type_books) {
            switch (pos) {
                case 0: type ="tipo=1"; break;
                case 1: type ="tipo=2"; break;
                case 2: type ="tipo=3"; break;
                case 3: type= "tipo=4"; break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
