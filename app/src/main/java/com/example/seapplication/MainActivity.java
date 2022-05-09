package com.example.seapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Sign_in;
    EditText Username;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login_Page");

        Sign_in = findViewById(R.id.login);
        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        Database DB = new Database(MainActivity.this);
        DB.Initializer();
        Sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ACCOUNTS accounts;

                try {

                    String input_username=Username.getText().toString();
                    String input_password=Password.getText().toString();
                    if(input_password.isEmpty() && input_username.isEmpty()){
                        Toast.makeText(MainActivity.this, "Error username or password is missing", Toast.LENGTH_SHORT).show();
                    accounts=new ACCOUNTS("empty","empty");
                    }
                    else {
                        accounts = new ACCOUNTS(input_username, input_password);
                    }

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    accounts = new ACCOUNTS("error","error");

                }

                boolean success = DB.CHECK_IF_ACC_EXISTS(accounts);
                if (success==true){
                    DB.setcur_signed_in(accounts.getUSERNAME());
                    Toast.makeText(MainActivity.this, "successfully signed in", Toast.LENGTH_SHORT).show();
                  openhomepage(v);
                }
                else{
                    Toast.makeText(MainActivity.this, "Error:Check username and password", Toast.LENGTH_SHORT).show();
                }
            }});
    }

    public void openhomepage(View v){
        Intent i= new Intent(this, homepage.class);

        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}