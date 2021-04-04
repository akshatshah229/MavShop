package com.example.mavshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
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