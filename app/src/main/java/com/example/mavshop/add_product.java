package com.example.mavshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class add_product extends AppCompatActivity {
    ImageView imageview;
    Button button_finish;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    TextView name,price,phone_number,description;
    RadioButton category;
    FireBaseHelper fbase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        button_finish = findViewById(R.id.bt_finish);
        name = findViewById(R.id.prod_title);
        price =findViewById(R.id.prod_price);
        phone_number = findViewById(R.id.prod_pn);
        description = findViewById(R.id.prod_desc);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_menu);
        bottomNavigationView.setSelectedItemId(R.id.add_product);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.categories:
                        startActivity(new Intent(getApplicationContext(), categories.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add_product:
                        return true;
                    case R.id.view_profile:
                        startActivity(new Intent(getApplicationContext(), view_profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        imageview = (ImageView)findViewById(R.id.imageview);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toPayment_Page(); }
        });
    }

    private void toPayment_Page() {
        String prod_name= name.getText().toString();
        String prod_price = price.getText().toString();
        String prod_desc = description.getText().toString();
        String prod_phonenum = phone_number.getText().toString();
        String prod_category = "Misc";
        String flag = "0";
        System.out.println("**"+imageUri);
        Intent intent1 = new Intent(this, Payment.class);
        startActivity(intent1);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(imageUri,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageview.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

}