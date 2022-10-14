package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.R;

public class A_Register extends AppCompatActivity {

    String nome, email, telefone, password;
    EditText et_nome;
    EditText et_email;
    EditText et_telefone;
    EditText et_password;
    Button btn_cadastro;
    Button btn_voltar;
    Data dataInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Importantes

        dataInstance = getIntent().getExtras().getParcelable("Data");

        btn_cadastro=findViewById(R.id.btn_cadastrar);
        et_nome=findViewById(R.id.et_nome);
        et_email=findViewById(R.id.et_email);
        et_telefone=findViewById(R.id.et_telefone);
        et_password=findViewById(R.id.et_password);


    }

    public void cadastrar(View v) {
        nome = et_nome.getText().toString();
        email = et_email.getText().toString();
        telefone = et_telefone.getText().toString();
        password = et_password.getText().toString();

        dataInstance.getDataUser().add(new User(nome,email,telefone,password));

        int posicao = dataInstance.getDataUser().size()-1;


    }

    public void listar(View v){


    }

    public void abrirLogin(View v){
        Intent it_telaLogin = new Intent(this, A_Home.class);
        startActivity(it_telaLogin);
    }


}