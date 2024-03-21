package com.example.bookclubapp_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button btnLogin, goToReg, forgot;
    EditText etUserName, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        etUserName = findViewById(R.id.userName);
        etPass = findViewById(R.id.password);
        goToReg = findViewById(R.id.goToRegister);
        forgot = findViewById(R.id.forgPassword);

        //Starts registration activity.
        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.logButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Checks if the user is registered to the database, if a matching user is found they will be directed to the ActivityUser class.
                boolean isLogged = dbHelper.checkUser(etUserName.getText().toString().trim(), etPass.getText().toString().trim());
                if(isLogged){
                    Intent intent = new Intent(MainActivity.this, ActivityUser.class);
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityForgotPass.class);
                startActivity(intent);
            }
        });
    }
}