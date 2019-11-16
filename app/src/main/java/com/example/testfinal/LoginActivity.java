package com.example.testfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testfinal.model.employee;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final String id = "admin";
        final String pass ="7777";

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextid = findViewById(R.id.id_editText);
                final String inputname = editTextid.getText().toString();
                EditText editTextpass = findViewById(R.id.password_editText);
                final String inputpass = editTextpass.getText().toString();

                //employee emm = new employee();
                if(inputname.equals(id) && inputpass.equals(pass)){

                    employee em = new employee(inputname,inputpass);

                    Intent intent = new Intent(LoginActivity.this,InputBioActivity.class);
                    intent.putExtra("id", inputpass);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this,/*String*/"Login Successful", Toast.LENGTH_LONG).show();
                }else{
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login Fail")
                            .setMessage("Please check id or password")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            }
        });



    }
}
