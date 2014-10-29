package com.vanessapr.appbibliotecafain;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vanessapr.appbibliotecafain.fragments.BooksListFragment;
import com.vanessapr.appbibliotecafain.utils.MessageDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText txtBusqueda;
    private Button btnSeartch, btnSearchAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBusqueda = (EditText) findViewById(R.id.txt_busqueda);
        btnSeartch = (Button) findViewById(R.id.btnSearch);
        btnSearchAdvanced = (Button) findViewById(R.id.btnSearchAdvanced);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSeartch.setOnClickListener(this);
        btnSearchAdvanced.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String consulta = txtBusqueda.getText().toString().trim();

        switch (view.getId()) {
            case R.id.btnSearch:
                    if(!consulta.equals("")) {
                        StringBuilder where = new StringBuilder("autor_personal LIKE '%").append(consulta).append("%' ");
                        where.append("OR autor_institucional LIKE '%").append(consulta).append("%' ");
                        where.append("OR titulo LIKE '%").append(consulta).append("%' ");
                        where.append("OR resumen LIKE '%").append(consulta).append("%' ");
                        where.append("OR descriptores LIKE '%").append(consulta).append("%' ");
                        where.append("OR titulo_revista LIKE '%").append(consulta).append("%'");

                        Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                        intent.putExtra(BooksActivity.EXTRA_WHERE, where.toString());
                        intent.putExtra(BooksActivity.EXTRA_ORDERBY, "titulo, titulo_revista, autor_personal, autor_institucional");
                        startActivity(intent);

                    } else {
                        MessageDialog message = new MessageDialog(MainActivity.this);
                        message.display("Especifique un término para buscar");
                    }t 
                break;

            case R.id.btnSearchAdvanced:
                Intent intent = new Intent(MainActivity.this, FormSearchActivity.class);
                startActivity(intent);
                break;
        }

    }

}
