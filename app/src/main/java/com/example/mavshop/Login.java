package com.example.mavshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText email_text;
    private EditText password_text,new_password,conf_password;
    private Button login_btn,btn_ok,btn_cancel;
    private TextView forget_pass;
    String email_str;
    String newpass,confpass;
    String password_str;

    FireBaseHelper fbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_text = findViewById(R.id.editTextTextEmailAddress);
        password_text = findViewById(R.id.editTextTextPassword);
        forget_pass = findViewById(R.id.forget_pass_text);
        new_password = findViewById(R.id.new_password_text);
        conf_password = findViewById(R.id.conf_password_text);
        fbase = new FireBaseHelper();
        fbase.setupFirebaseDB();
        fbase.getAllData();


        login_btn = findViewById(R.id.button);
        btn_ok=  findViewById(R.id.but1);
        btn_cancel =  findViewById(R.id.but2);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_auth();
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.activity_popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpass = new_password.getText().toString();
                confpass = conf_password.getText().toString();
                if(!newpass.equals(confpass)) {
                    System.out.println("Not same password!");
                }
                else {
                    replaceUserPassword(newpass);
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("I am in cancel");
                popupWindow.dismiss();

            }
        });
            }
        });
    }

    private void pop_up_menu_function(View v) {


    }

    private void replaceUserPassword(String newpass) {
            System.out.println("i am in newpass");
       /* for (int i = 0; i < FireBaseHelper.userList.size(); i++) {
            if (.equals(FireBaseHelper.userList.get(i).emailid)
                    && password_str.equals(FireBaseHelper.userList.get(i).password)) {
                return true;
            }
        }*/
    }

    private void login_auth() {
        email_str = email_text.getText().toString();
        password_str = password_text.getText().toString();


        if (email_str.equals("") && password_str.equals("")) {
            Toast.makeText(Login.this, "Please enter the details!", Toast.LENGTH_LONG).show();
        } else if (email_str.equals("") || password_str.equals("")) {
            Toast.makeText(Login.this, "UserName and Password missing!", Toast.LENGTH_LONG).show();
        }
       /* else if(!email_str.contains("mavs.uta.edu"))
        {
            Toast.makeText(Login.this, "Invalid Email-Id.Please enter valid id!",Toast.LENGTH_LONG).show();
        }*/
        else {
            if (checkifUserExist(email_str, password_str)) {
                Toast.makeText(Login.this, "Login successfull!", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, categories.class);
                startActivity(intent1);
            } else {
                Toast.makeText(Login.this, "User not found!Please Register", Toast.LENGTH_LONG).show();

            }
        }
    }

    public boolean checkifUserExist(String email_str, String password_str) {
        for (int i = 0; i < FireBaseHelper.userList.size(); i++) {
            if (email_str.equals(FireBaseHelper.userList.get(i).emailid)
                    && password_str.equals(FireBaseHelper.userList.get(i).password)) {
                return true;
            }
        }
        return false;



    }


}