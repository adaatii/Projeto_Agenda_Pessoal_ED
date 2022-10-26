package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

public class A_Register extends AppCompatActivity implements Constants {

    String nome, email, telefone, password, confPassword;
    EditText et_nome;
    EditText et_email;
    EditText et_telefone;
    EditText et_password;
    EditText et_confPassword;
    Button btn_cadastro;
    Button btn_voltar;
    Data dataInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Importantes
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");

        btn_cadastro=findViewById(R.id.btn_cadastrar);
        et_nome=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_telefone=findViewById(R.id.et_phone);
        et_password=findViewById(R.id.et_password);
        et_confPassword=findViewById(R.id.et_confPassword);

    }
    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();

    }

    public void registerUser(View v) {
        nome = et_nome.getText().toString();
        email = et_email.getText().toString();
        telefone = et_telefone.getText().toString();
        password = et_password.getText().toString();
        confPassword = et_confPassword.getText().toString();
        User user = new User();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || password.isEmpty() || confPassword.isEmpty()){
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else{
            if (user.authenticateEmail(email)){
                boolean emailUsed = false;
                for (int i = 0; i < dataInstance.getDataUser().size(); i++) {
                    if (dataInstance.getDataUser().get(i).getEmail().equals(email)){
                        emailUsed = true;
                    }
                }
                if (!emailUsed){
                    if (user.validatePassword(password, confPassword)){
                        user.NewUser(nome,email,telefone,password);
                        dataInstance.getDataUser().add(user);
                        Intent it_aHome = new Intent();
                        it_aHome.putExtra("NewUser", dataInstance);
                        setResult(RESULT_FIRST_USER, it_aHome);
                        finish();
                    }else{
                        //Senhas não conferem
                        alert("Cadastro", "Senha e confirmação de senha não conferem");
                    }
                }else{
                    alert("Cadastro", "Email já possui cadastro");
                }
            }else{
                //Email inválido
                alert("Cadastro", "Email Inválido");
            }
        }
    }

    public void abrirLogin(View v){
        Intent it_aHome = new Intent();
        setResult(RESULT_OK, it_aHome);
        finish();
    }

   public void returnToHome(View v){
       Intent it_aHome = new Intent();
       setResult(RESULT_DESTROY, it_aHome);
       finish();
   }
}