package com.example.mavshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt_log;
    private Button bt_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_log = (Button)findViewById(R.id.bt_log);
        bt_reg = (Button)findViewById(R.id.bt_reg);

        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin_page();
            }
        });

        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister_page();
            }
        });
    }

    private void openRegister_page() {
        Intent intent1 = new Intent(this, Register.class);
        startActivity(intent1);
    }

    private void openLogin_page() {
        Intent intent1 = new Intent(this, Login.class);
        startActivity(intent1);
    }
}