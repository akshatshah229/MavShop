package com.example.mavshop;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FireBaseHelper extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference mDatabaseReference;
    long maxId = 0;
    String usersJson = "";
    public static ArrayList<User> userList = new ArrayList<User>();

    int flag=0;
    public void setupFirebaseDB() {
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("users");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.

                // after adding this data we are showing toast message.
                if (snapshot.exists()) {
                    maxId = (snapshot.getChildrenCount());
                    System.out.println("Pranit : RealDBTest : MainActivity : setupFirebaseDB : maxID:" + maxId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                System.out.println("Add data failed");
            }
        });
    }

    public void writeNewUser(String firstName_str, String lastName_str, String emailid_str, String address_str, String phone_str, String password_str, String confirmpassword_str) {
        User user = new User(firstName_str, lastName_str, emailid_str, address_str, phone_str, password_str, confirmpassword_str);
        // System.out.println("Pranit : RealDBTest : MainActivity : writeNewUser : maxID:"+maxId);
        mDatabaseReference.child(String.valueOf(maxId + 1)).setValue(user);
    }

    public void getAllData() {
        mDatabaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()) {
                    System.out.println("Neha : FirebaseTest : FireBaseHelper : checkifUserExists : Error reading data");
                } else {
                    Object object = task.getResult().getValue();
                    usersJson = new Gson().toJson(object);
                    System.out.println("Neha : FirebaseTest : FireBaseHelper : checkifUserExists : usersJson:"+ usersJson);
                    parseData(usersJson);
                }
            }
        });

    }



    private void parseData(String usersJson) {
        try {
            if(!usersJson.isEmpty()) {
                userList.clear();

                //usersJson = usersJson.substring(8,usersJson.length()-2);
                JSONArray jsonArray = new JSONArray(usersJson);
                for(int i=0; i<jsonArray.length();i++) {
                    JSONObject mJsonObject = jsonArray.optJSONObject(i);

                    if(mJsonObject != null) {
                        String firstname = mJsonObject.optString("firstname");
                        String lastname = mJsonObject.optString("lastname");
                        String email_id = mJsonObject.optString("emailid");
                        String address = mJsonObject.optString("address");
                        String phone = mJsonObject.optString("phone");
                        String password = mJsonObject.optString("password");
                        String conf_password = mJsonObject.optString("conf_password");

                        System.out.println("Neha : FirebaseTest : FireBaseHelper : parseData : firstname :"+ firstname);

                        User user = new User(firstname, lastname, email_id, address, phone, password, conf_password);
                        userList.add(user);
                    }
                }
            } else {
                System.out.println("Neha : FirebaseTest : FireBaseHelper : parseData : empty data");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
