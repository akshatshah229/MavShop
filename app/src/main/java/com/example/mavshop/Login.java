package com.example.mavshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText email_text;
    private EditText password_text;
    private Button login_btn;
    String email_str;
    String password_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_text = findViewById(R.id.editTextTextEmailAddress);
        password_text = findViewById(R.id.editTextTextPassword);


       // System.out.println("*******"+email_str+" "+password_str);
        login_btn = findViewById(R.id.button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_auth();
            }
        });
    }

    private void login_auth() {
        DataBaseAdapter mDbHelper = new DataBaseAdapter(this.getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        email_str = email_text.getText().toString();
        password_str = password_text.getText().toString();

       // System.out.println("*******"+email_str+" "+password_str);

        if(email_str.equals("") && password_str.equals(""))
        {
            Toast.makeText(Login.this, "Please enter the details!",Toast.LENGTH_LONG).show();
        }
        else if(email_str.equals("") || password_str.equals(""))
        {
            Toast.makeText(Login.this, "UserName and Password missing!",Toast.LENGTH_LONG).show();
        }
        else if(!email_str.contains("mavs.uta.edu"))
        {
            Toast.makeText(Login.this, "Invalid Email-Id.Please enter valid id!",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(mDbHelper.checkifUserExists(email_str,password_str))
            {
                Toast.makeText(Login.this, "Login successfull!",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, categories.class);
                startActivity(intent1);
            }
            else
            {
                Toast.makeText(Login.this, "User not found!Please Register",Toast.LENGTH_LONG).show();

            }
        }
    }


}