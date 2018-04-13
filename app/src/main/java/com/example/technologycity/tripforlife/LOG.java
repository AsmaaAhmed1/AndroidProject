package com.example.technologycity.tripforlife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LOG extends AppCompatActivity {

    RegisterDB registerdb;
    EditText   email, pass;
    Button register, login;
    public static final String shValueString="log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shval = getSharedPreferences(shValueString, MODE_PRIVATE);
        String mailVal = shval.getString("email", "&&");
        if (mailVal.equals("&&")) {
            setContentView(R.layout.activity_log);


            registerdb = new RegisterDB(this);

            email = (EditText) findViewById(R.id.email);
            pass = (EditText) findViewById(R.id.pass);
            register = (Button) findViewById(R.id.register);
            login = (Button) findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String umail = email.getText().toString();
                    String password = pass.getText().toString();
                    if (umail.matches("") || password.matches("")) {
                        Toast.makeText(getApplicationContext(), "enter your data", Toast.LENGTH_LONG);
                    } else {
                        boolean check = registerdb.checkLogin(umail, password);
                        if (check == true) {
                            SharedPreferences shval = getSharedPreferences(shValueString, 0);
                            SharedPreferences.Editor shE = shval.edit();
                            shE.putString("email", umail);

                            shE.putString("pass", password);
                            shE.commit();
                            finish();
                            Intent intent = new Intent(LOG.this, MainActivity.class);
                            startActivity(intent);


                            Toast.makeText(getApplicationContext(), "enter your data", Toast.LENGTH_LONG);

                        } else {
                            Toast.makeText(getApplicationContext(), "Not valide ", Toast.LENGTH_LONG);

                        }
                    }

                    email.setText("");
                    pass.setText("");
                }

            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LOG.this, Register.class);
                    startActivity(intent);

                }
            });
        }

        else
        {
            Log.i("hna f allogin", "hna la2et value f alshared pref");

            Intent intent = new Intent(LOG.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }}




