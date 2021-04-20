package com.example.mavshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class categories extends AppCompatActivity {
    View post_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_menu);
        bottomNavigationView.setSelectedItemId(R.id.categories);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.categories:
                        return true;
                    case R.id.add_product:
                        startActivity(new Intent(getApplicationContext(), add_product.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.view_profile:
                        startActivity(new Intent(getApplicationContext(), view_profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        post_item = findViewById(R.id.action_cam);

        post_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_Item();
            }
        });
    }

    private void post_Item() {


    }


    public void OpenFurniture (View view) {
        Intent intent1 = new Intent(this, Furniture.class);
        startActivity(intent1);
    }

    public void OpenElectronics (View view) {
        Intent intent1 = new Intent(this, Electronics.class);
        startActivity(intent1);
    }

    public void OpenKitchenware (View view) {
        Intent intent1 = new Intent(this, Kitchenware.class);
        startActivity(intent1);
    }

    public void OpenOrganizers (View view) {
        Intent intent1 = new Intent(this, Organizers.class);
        startActivity(intent1);
    }

    public void OpenBooks (View view) {
        Intent intent1 = new Intent(this, Books.class);
        startActivity(intent1);
    }

    public void OpenMisc (View view) {
        Intent intent1 = new Intent(this, Miscellaneous.class);
        startActivity(intent1);
    }




}