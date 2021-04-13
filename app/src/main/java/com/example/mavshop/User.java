package com.example.mavshop;

public class User {

   public String firstname;
   public String lastname;
   public String emailid;
   public String address;
   public String phone;
   public String password;
   public String conf_password;

    User(){}
    User(String firstName_str, String lastName_str, String emailid_str, String address_str, String phone_str, String password_str, String confirmpassword_str)
    {
         firstname=firstName_str;
         lastname=lastName_str;
         emailid=emailid_str;
         address=address_str;
         phone=phone_str;
         password=password_str;
         conf_password=confirmpassword_str;
    }
}
