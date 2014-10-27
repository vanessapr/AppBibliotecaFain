package com.vanessapr.appbibliotecafain;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private EditText txtBusqueda;
    private Button btnSeartch, btnSearchAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBusqueda = (EditText) findViewById(R.id.txt_busqueda);
        btnSeartch = (Button) findViewById(R.id.btnSearch);
        btnSearchAdvanced = (Button) findViewById(R.id.btnSearchAdvanced);
        btnSeartch.setOnClickListener(this);
        btnSearchAdvanced.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                                
                break;

            case R.id.btnSearchAdvanced:
                break;
        }

    }
}
