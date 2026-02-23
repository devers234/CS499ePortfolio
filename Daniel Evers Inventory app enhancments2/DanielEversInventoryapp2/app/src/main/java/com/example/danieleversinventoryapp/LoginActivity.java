package com.example.danieleversinventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class LoginActivity extends Activity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonCreateAccount;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // This links to your XML


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        db = new DBHelper(this);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (db.checkUser(username, password)) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DataGridActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCreateAccount.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
            } else if (db.usernameExists(username)) {
                Toast.makeText(this, "Username already exists.", Toast.LENGTH_SHORT).show();
            } else {
                if (db.registerUser(username, password)) {
                    Toast.makeText(this, "Account created. You can now log in.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error creating account.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}