package com.example.lasha.fruitbasket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.lasha.fruitbasket.R.id.login;
import static com.example.lasha.fruitbasket.R.id.signup;
import static com.example.lasha.fruitbasket.R.id.userName;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        EditText username,password;
        Button loginBut,signupBut;
        MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText) findViewById(R.id.passwordSign);
        signupBut=(Button)findViewById(R.id.signup);
        loginBut=(Button)findViewById(R.id.login);
        loginBut.setOnClickListener(this);
        signupBut.setOnClickListener(this);
        dbHandler=new MyDBHandler(this,null,null,1);
    }

    @Override
    public void onClick(View view){
        if(view.getId()==signup){
            Intent toy = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(toy);
        }
        if(view.getId()==login){
          if(dbHandler.checkUser(username.getText().toString(),password.getText().toString())){
              Intent toy = new Intent(MainActivity.this, ClientActivity.class);
              startActivity(toy);
          }else{
              AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
              alertDialog.setTitle("Alert");
              alertDialog.setMessage("No such user");
              alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                      new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                          }
                      });
              alertDialog.show();
          }

        }

    }
}
