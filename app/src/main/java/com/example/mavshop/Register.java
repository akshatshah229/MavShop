
package com.example.mavshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private TextView firstName;
    private TextView lastName;
    private TextView emailid;
    private TextView phone;
    private TextView address;
    private TextView password;
    private TextView confirmpassword;
    private Button sign_up;
    String firstName_str;
    String lastName_str;
    String emailid_str;
    String phone_str;
    String address_str;
    String password_str;
    String confirmpassword_str;
    String sign_up_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.t_fn);
        lastName = findViewById(R.id.t_ln);
        emailid = findViewById(R.id.t_emailid);
        phone = findViewById(R.id.t_pn);
        address = findViewById(R.id.t_add);
        password = findViewById(R.id.t_pass);
        confirmpassword = findViewById(R.id.t_conPass);
        sign_up = findViewById(R.id.bt_signup);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        firstName_str = firstName.getText().toString();
        lastName_str = lastName.getText().toString();
        emailid_str = emailid.getText().toString();
        phone_str = phone.getText().toString();
        address_str = address.getText().toString();
        password_str = password.getText().toString();
        confirmpassword_str = confirmpassword.getText().toString();

        if(firstName_str.equals("")||lastName_str.equals("")||emailid_str.equals("")||phone_str.equals("")||phone_str.equals("")
        ||address_str.equals("")||password_str.equals("")||confirmpassword_str.equals(""))
        {
            Toast.makeText(Register.this, "Some field is missing!",Toast.LENGTH_LONG).show();
        }
        else if(!emailid_str.contains("@mavs.uta.edu"))
        {
            Toast.makeText(Register.this, "Please enter UTA Email-Id only!",Toast.LENGTH_LONG).show();
        }
        else if(!password_str.equals(confirmpassword_str))
        {
            //System.out.println(password_str+"  "+confirmpassword_str);
            Toast.makeText(Register.this, "Passwords do not match!",Toast.LENGTH_LONG).show();
        }
        else if(phone_str.length()!=10)
        {
            Toast.makeText(Register.this, "Please enter valid Phone Number!",Toast.LENGTH_LONG).show();
        }
        else if(password_str.length()<5)
        {
            Toast.makeText(Register.this, "Please enter atleast 5 digit password!",Toast.LENGTH_LONG).show();
        }
        else
        {

            DataBaseAdapter mDbHelper = new DataBaseAdapter(this.getApplicationContext());
            mDbHelper.createDatabase();
            mDbHelper.open();
            mDbHelper.insertUser(firstName_str,lastName_str,emailid_str,address_str,phone_str,password_str,confirmpassword_str);
            Toast.makeText(Register.this, "Register Successful!Please login",Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this, Login.class);
            startActivity(intent1);
        }
    }
}