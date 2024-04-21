package com.programming.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText txtName, txtDOB, txtUsernameSign, txtPasswordSign, txtRePasswordSign;
    Button btnSignUp;
    RadioButton rdoMale, rdoFemale, rdoNonBi;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtName = findViewById(R.id.txtName);
        txtDOB = findViewById(R.id.txtDOB);

        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);
        rdoNonBi = findViewById(R.id.rdoNonBi);

        txtUsernameSign = findViewById(R.id.txtUsernameSign);
        txtPasswordSign = findViewById(R.id.txtPasswordSign);
        txtRePasswordSign = findViewById(R.id.txtRePasswordSign);

        DB = new DBHelper(this);

        btnSignUp = findViewById(R.id.btnLogin);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Activate to display user's name on search screen.
                //--1--String str = txtName.getText().toString();

                String user = txtUsernameSign.getText().toString();
                String pass = txtPasswordSign.getText().toString();
                String repass = txtRePasswordSign.getText().toString();
                String name = txtName.getText().toString();
                //String dob = txtDOB.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                    //if any of the boxes are empty, gives a message
                    Toast.makeText(SignUpActivity.this, "Please enter all fields with *", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)){
                        //if password don't match
                        Boolean checkUser = DB.usernameExists(user);

                        if(checkUser == false){
                            Boolean insert = DB.insertData(user, pass, name);

                            if(insert == true){
                                Toast.makeText(SignUpActivity.this, "Registered successfully!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                        else
                            Toast.makeText(SignUpActivity.this, "User already exists. Please try logging in.", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.itHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.itAboutUs:
                Intent intent1 = new Intent(this, AboutUsActivity.class);
                startActivity(intent1);
                return true;

            case R.id.itContact:
                Intent intent2 = new Intent(this, ContactActivity.class);
                startActivity(intent2);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}