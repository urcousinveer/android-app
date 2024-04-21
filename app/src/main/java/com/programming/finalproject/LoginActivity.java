package com.programming.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView txtSignUplink;
    EditText txtUsername, txtPassword;
    Button btnLogin;
    DBHelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        txtSignUplink = findViewById(R.id.txtSignUplink);

        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txtUsername.getText().toString();
                String pass = txtPassword.getText().toString();

                String name = DB.getName(user);


                if(user.equals("") || pass.equals(""))
                    //if any of the boxes are empty, gives a message
                    Toast.makeText(LoginActivity.this, "Please check username/password", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(user, pass);
                    if (checkUserPass == true) {
                        Toast.makeText(LoginActivity.this, "Login in successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                        intent.putExtra("message", name);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password. Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        txtSignUplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.itSignOut:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.itAbout:
                Intent intent1 = new Intent(this, AboutUsActivity.class);
                startActivity(intent1);
                return true;

            case R.id.itContact2:
                Intent intent2 = new Intent(this, ContactActivity.class);
                startActivity(intent2);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}