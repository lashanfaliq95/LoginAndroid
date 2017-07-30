package com.example.lasha.fruitbasket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements  View.OnClickListener{
    EditText userName,password,confirmpassword;
    MyDBHandler dbHandler;
    Button createaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userName=(EditText)findViewById(R.id.userName);
        password=(EditText)findViewById(R.id.passwordSign);
        confirmpassword=(EditText)findViewById(R.id.confirmPassword);
        dbHandler=new MyDBHandler(this,null,null,1);
        createaccount=(Button)findViewById(R.id.createAccount);
        createaccount.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(password.getText().toString().equals(confirmpassword.getText().toString())) {
            Users user = new Users(userName.getText().toString(), password.getText().toString());
            dbHandler.addUser(user);
            Intent toy = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(toy);
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Make sure the passwords match");
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
