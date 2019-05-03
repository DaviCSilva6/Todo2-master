package com.example.davicsilva6.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davicsilva6.todo.Banco.ControlaBanco;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void insere(View view){
        ControlaBanco crud = new ControlaBanco(getBaseContext());
        EditText edNome = findViewById(R.id.txtNome);
        TextView tvResultado = findViewById(R.id.tvResultado);

        String nome = edNome.getText().toString();

        if (nome.equals("")){
            tvResultado.setText("Campo não pode ser vazio");
        }
        else {
            String resultado = crud.insereDado(nome);
            tvResultado.setText(resultado);

            Intent intent = new Intent(this, ListaActivity.class);
            startActivity(intent);
        }


    }

}
