package com.pnp.galia_app_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText editTextUser;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_button);
        editTextUser = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(view -> openHomepage(view));
    }

    public void openHomepage(View view) {
        //Intent intent = new Intent(this, HomeActivity.class);
        //startActivity(intent);
        if(editTextUser.getText().toString().equals("navigator")) {
            if(editTextPassword.getText().toString().equals("123456")) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }
}