package com.example.davicsilva6.todo.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//Definição no nome da classe ControlaBanco. Segue o padrão de nomees: classes começam com a primeira letra maiuscula
public class ControlaBanco {
    //Declaração do objeto db do tipo "SQLiteDataBase"

    private SQLiteDatabase db;

    //Declaração do objeto"banco" do tipo "CriaBanco".Objeto privado -> só pode ser acessado pela classe atual
    private CriaBanco banco;

    //Construtor da classe. Recebe como parâmetro o contexto. Publico-> podes ser acessado pela classe atual
    public ControlaBanco(Context context){
        banco = new CriaBanco(context);
    }

    //Método do tipo String(deve ser retornado um valor String)
    //Público -> pode ser aceessado fora da classe
    //Recebe como parmetro uma variavel do tipo String
    public String insereDado(String nome){
    //Declara o objeto "valores" do tipo "ContentValues"
        //Padrão de nome para objeto é começar com a primeira maiúscula
        ContentValues valores;
        //Declara a variavel "resultado" do tipo "long
        //Padrão de nome para objeto é começar com a primeira minúscula
        long resultado;

        //Retorna o valor do metodo getWriteDataBase e adiciona no objeto "db"
        //Diz que o banco está preparado para escrita de novos dados
        db = banco.getWritableDatabase();

        //Instancia o objeto "valores" com o construtor da classe "ContentValues"
        valores = new ContentValues();

        //put é um metodo que recebe como parametro duas Strings
        //Primeira String é nome da coluna no banco de dados
        //Segunda String é o dado que será inserido na linha
        valores.put("nome", nome);

        //Insert é um método que recebe como parâmetro três valores
        //Primeiro valor é uma String com o nome da tabela
        //Segundo valor é uma String para os campos nulos
        //Terceiro valor é um objeto do tipo "ContentValues" com o valor a ser inserido
        //método retorna o valor na variavel resultado
        resultado = db.insert("tarefa",  null, valores);
        //close é um metodo que não recebe nenhum parâmetro
        db.close();

        if (resultado == -1){
            return  "Erro ao inserir dado";
        }
        else{
            return "Dado  Inserido com sucesso";
        }

    }
      //Declarando um método público do tipo cursor que não recebe nenhum parametro
    public Cursor carregaDados(){

        //Objeto do tipo "Cursor"
        Cursor cursor;
        //Um array de String com o nome "campos" que recebe os valores entre as chaves
        String [] campos = {"_id", "nome"};
        //get.ReadableDataBase() é um metodo "banco" que retorna ua valro para o objeto "db"
        //Esse metodo diz que o banco estara disponivel para leitura
        db = banco.getReadableDatabase();
        //querry é um metodo do objeto db que recebe como parametro o que está entre parenteses
        //o valor retornado do metodo querry será inserido no objeto cursor
        cursor = db.query("tarefa", campos,
                null, null,null, null, null);
        //Uma estrutura de decisao quando o cursor for igual nulo
        //método do tipo movetofirst para mover ele para a primeira posição para ser lido primeiro que os outros
        if (cursor !=null){
            cursor.moveToFirst();
        }
        //Metodo do tipo close para encerrar o objeto "db"
        db.close();
        //Metodo que retorna valor para o "cursor"
        return cursor;
    }

    public void deletaDado(int id){
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        db.delete("tarefa",where, null);
        db.close();

    }
    public  Cursor carregaDadoPorId(int id){
        Cursor cursor;
        String[] campos = {"_id", "nome"};
        String where =  "_id = " + id;
        db = banco.getReadableDatabase();
        cursor = db.query("tarefa", campos, where,null, null, null, null);

        if ((cursor!=null)){
            cursor.moveToFirst();

        }
        db.close();
        return cursor;
    }

    public void alteraDado(int id, String nome){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();
        where = "_id=" +id;
        valores = new ContentValues();
        valores.put("nome",nome);

        db.update("tarefa",valores, where, null);
        db.close();
    }

}
