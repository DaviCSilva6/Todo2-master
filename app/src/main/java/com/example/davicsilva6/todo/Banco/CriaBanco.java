package com.example.davicsilva6.todo.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Cria uma classe CriaBanco que herda da classe SQLiteOpenHelper
public class CriaBanco extends SQLiteOpenHelper {

    //Definição das constantes para nomes do banco, tabela e colunas
    private  static final String NOME_DB = "banco.db";
    private static  final  String TABELA = "tarefa";
    private  static  final  String ID = "_id";
    private static final  String NOME ="nome";
    private static final int  VERSAO = 1;

    //Construtor da classe que recebe como parâmetro o contexto
    //Deve ser criado dessa forma pois a classe CriaBanco herda da classe SQLiteOpenHelper
    public CriaBanco(Context context){
        super (context, NOME_DB,null, VERSAO);
    }

    //Anotação JAVA para sobreescrever um método herdado de uma classe
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criar uma String com o conteudo de uma querry(comando em SQL) para criar um  banco de dadoos
        String sql = "CREATE TABLE " + TABELA + " ("
        + ID + " integer primary key autoincrement, "
        + NOME + " text"
        + ")";
     db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Querry para apagar a tabela
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        //Chama o método onCreate novamente
        onCreate(db);

    }
}
