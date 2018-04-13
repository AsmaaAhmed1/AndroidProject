package com.example.technologycity.tripforlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
        EditText username , email, pass;
        Button register, login;
        RegisterDB registerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerdb=new RegisterDB(Register.this);

        username=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString();
                String umail=email.getText().toString();
                String password=pass.getText().toString();
                registerdb.insertIntoRgister(uname,umail,password);
                Intent intent=new Intent(Register.this,LOG.class);
                startActivity(intent);

            }
        });
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Register.this,LOG.class);
        startActivity(intent);
    }
});

    }
}
