package com.example.davicsilva6.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.davicsilva6.todo.Banco.ControlaBanco;

public class AtualizaActivity extends AppCompatActivity {

    private  String codigo;
    private EditText etNomeAtualiza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza);

        codigo = this.getIntent().getStringExtra("codigoId");

        ControlaBanco crud = new ControlaBanco(getBaseContext());

        etNomeAtualiza = findViewById(R.id.edTarefaAtualiza);
        Cursor cursor = crud.carregaDadoPorId(Integer.parseInt(codigo));
        etNomeAtualiza.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
    }
    public void atualiza(View view){
        ControlaBanco crud = new ControlaBanco(getBaseContext());
        crud.alteraDado(Integer.parseInt(codigo), etNomeAtualiza.getText().toString());
        Intent intent = new Intent(AtualizaActivity.this, ListaActivity.class);
        startActivity(intent);
        finish();

    }
}
