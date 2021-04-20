package com.example.mavshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class view_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_menu);
        bottomNavigationView.setSelectedItemId(R.id.view_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.categories:
                        startActivity(new Intent(getApplicationContext(), categories.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_product:
                        startActivity(new Intent(getApplicationContext(), add_product.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.view_profile:
                        return true;
                }
                return false;
            }
        });
    }
}